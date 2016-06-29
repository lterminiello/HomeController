package com.despegar.homecontroller;

import android.os.AsyncTask;
import android.util.Log;

import com.despegar.homecontroller.com.despegar.homecontroller.model.Lights;
import com.despegar.homecontroller.com.despegar.homecontroller.utils.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RaspberryStatusTask extends AsyncTask<String, Void, Lights> {


    private listenerFragmen listenerFragmen;

    public RaspberryStatusTask( listenerFragmen listenerFragmen) {
        this.listenerFragmen = listenerFragmen;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Lights lights) {
        super.onPostExecute(lights);

        listenerFragmen.updateStatusServer(lights);
    }

    @Override
    protected Lights doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        try {
            Log.e("--->",params[0] );
            URL url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();

            JsonFactory jsonFactory = new JsonFactory();

            return jsonFactory.fromJson(new InputStreamReader(inputStream), new TypeReference<Lights>() {
            });

        } catch (IOException e) {
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }
}
