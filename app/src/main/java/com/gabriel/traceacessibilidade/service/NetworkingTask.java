package com.gabriel.traceacessibilidade.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.gabriel.traceacessibilidade.model.PublicTransport;
import com.gabriel.traceacessibilidade.model.TransportAll;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class NetworkingTask extends AsyncTask<String, Void, String> {

    private Exception exception;
    private Object obj = null;
    private PersistService persistentService;

    public NetworkingTask(Object obj, PersistService persistentService) {
        this.obj = obj;
        this.persistentService = persistentService;
    }

    protected String doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            NetworkingService networking = new NetworkingService();

            String json;
            if(networking.isOnline(this.obj)) {
                json = networking.downloadJsonTransportPublicHours();
                TransportAll transportAll = converterJsonInObject(json);
                persistentService.writeObject(PersistService.KEY_PERSIST_PUBLIC_TRANSPORT, transportAll);
//                TransportAll transportAll = (TransportAll) persistentService.readObject("hours");
//                Log.d(transportAll.getPublicTransports().get(0).getItinerary(), transportAll.getPublicTransports().get(0).getItinerary());
            }

        } catch (Exception e) {
            this.exception = e;
            e.printStackTrace();
        }
        return "";
    }

    private TransportAll converterJsonInObject(String json) {
        Gson g = new Gson();
        TransportAll transportAll = g.fromJson(json, TransportAll.class);
        return transportAll;
    }

    protected void onPostExecute(String feed) {

    }
}