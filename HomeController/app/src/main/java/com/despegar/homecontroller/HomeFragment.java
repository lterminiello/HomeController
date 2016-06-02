package com.despegar.homecontroller;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment implements listenerFragmen{

    private RaspberryServiceTask raspberryServiceTask;
    private HomeFragment yo;
    private ImageButton imgLamp;
    private EditText editIp;



    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        imgLamp = (ImageButton) v.findViewById(R.id.imgLamp);
        yo = this;

        editIp = (EditText) v.findViewById(R.id.editIp);
        imgLamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                raspberryServiceTask =  new RaspberryServiceTask(yo);
                raspberryServiceTask.execute("http://"+editIp.getText().toString()+":8080/home?state=0");
            }
        });
        callAsynchronousTask();
        return v;
    }

    @Override
    public void onFinish(String status) {
        raspberryServiceTask.cancel(true);
        updateStatusServer(status);
        Toast t = Toast.makeText(getContext(),status,Toast.LENGTH_LONG);
        t.show();
    }

    @Override
    public void updateStatusServer(String status) {
        if(status != null && status.equals("\"on\"")){
            imgLamp.setImageResource(R.drawable.on_lightbulb);
        }else{
            imgLamp.setImageResource(R.drawable.off_lightbulb);
        }
    }

    public void callAsynchronousTask() {
        //TODO falta destruir el hilo cuando se rota la pantalla porque sino rompe todo ESTO SE VA A DESCONTROLAR!!!!!

        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            RaspberryStatusTask raspberryStatusTask = new RaspberryStatusTask(yo);
                            raspberryStatusTask.execute("http://"+editIp.getText().toString()+":8080/home/status");
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 5000); //execute in every 50000 ms
    }


}
