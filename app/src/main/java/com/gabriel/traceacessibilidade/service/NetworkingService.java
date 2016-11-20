package com.gabriel.traceacessibilidade.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.gabriel.traceacessibilidade.view.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 * Created by gabriel on 20/11/16.
 */

public class NetworkingService {

    public boolean isOnline(Object obj) {
        ConnectivityManager connectivityManager = (ConnectivityManager) obj;
        NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void downloadTransportPublicHours() throws IOException {
        String json = null;
        json = getJsonFromURL("http://private-35a570-acessibilidade.apiary-mock.com/horarios");
        Log.d("Json", json);
    }

    public String getJsonFromURL(String urlString) throws IOException {
        HttpURLConnection urlConnection = null;

        URL url = new URL(urlString);
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setReadTimeout(10000);
        urlConnection.setConnectTimeout(15000);
        urlConnection.setDoOutput(true);
        urlConnection.connect();

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        char[] buffer = new char[1024];
        String jsonString = new String();

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line+"\n");
        }
        br.close();

        jsonString = sb.toString();
        return jsonString;
    }

}
