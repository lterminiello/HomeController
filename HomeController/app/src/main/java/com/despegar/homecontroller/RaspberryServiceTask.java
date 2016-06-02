package com.despegar.homecontroller;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RaspberryServiceTask extends AsyncTask<String, Void, String> {


        private listenerFragmen listenerFragmen;

        public RaspberryServiceTask( listenerFragmen listenerFragmen) {
            this.listenerFragmen = listenerFragmen;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String string) {
            super.onPostExecute(string);

            listenerFragmen.onFinish(string);
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            try {
                Log.e("--->",params[0] );
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();

                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String s = br.readLine();
                return s;

            } catch (IOException e) {
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }
    }