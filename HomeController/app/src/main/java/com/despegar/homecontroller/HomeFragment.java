package com.despegar.homecontroller;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.despegar.homecontroller.com.despegar.homecontroller.model.Lights;

import java.util.Timer;
import java.util.TimerTask;


public class HomeFragment extends Fragment implements listenerFragmen{

    private RaspberryServiceLightsTask raspberryServiceLightsTask;
    private HomeFragment yo;
    private ImageButton imgLamp;
    private EditText editIp;
    private SeekBar seekBar;
    private TextView powerView;



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
        seekBar = (SeekBar) v.findViewById(R.id.seekBarLed);
        powerView = (TextView) v.findViewById(R.id.powerView);
        yo = this;

        editIp = (EditText) v.findViewById(R.id.editIp);
        imgLamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                raspberryServiceLightsTask =  new RaspberryServiceLightsTask(yo);
                raspberryServiceLightsTask.execute("http://"+editIp.getText().toString()+":8080/lights/kichent");
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                raspberryServiceLightsTask =  new RaspberryServiceLightsTask(yo);
                raspberryServiceLightsTask.execute("http://"+editIp.getText().toString()+":8080/lights/bedroom?power="+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        callAsynchronousTask();
        return v;
    }

    @Override
    public void onFinish(Lights status) {
        raspberryServiceLightsTask.cancel(true);
        updateStatusServer(status);
    }

    @Override
    public void updateStatusServer(Lights status) {
        if(status != null) {
            if (status.getKichent().equals("HIGH")) {
                imgLamp.setImageResource(R.drawable.on_lightbulb);
            } else {
                imgLamp.setImageResource(R.drawable.off_lightbulb);
            }
            powerView.setText(status.getBedroom() / 10 + "%");
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
                            raspberryStatusTask.execute("http://"+editIp.getText().toString()+":8080/lights/status");
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
