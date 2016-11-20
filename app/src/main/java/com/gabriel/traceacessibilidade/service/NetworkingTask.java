package com.gabriel.traceacessibilidade.service;

import android.content.Context;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;

public class NetworkingTask extends AsyncTask<String, Void, String> {

    private Exception exception;
    private Object obj = null;

    public NetworkingTask(Object obj) {
        this.obj = obj;
    }

    protected String doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            NetworkingService networking = new NetworkingService();

            if(networking.isOnline(this.obj))
                networking.downloadTransportPublicHours();

        } catch (Exception e) {
            this.exception = e;
        }
        return "";
    }

    protected void onPostExecute(String feed) {

    }
}