package com.gabriel.traceacessibilidade.service;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by gabriel on 20/11/16.
 */

public class PersistService {

    private Context context = null;
    public static String KEY_PERSIST_PUBLIC_TRANSPORT = "hours";

    public PersistService(Context context) {
        this.context = context;
    }

    public void writeObject(String key, Object object) {
        try {
            FileOutputStream fos = this.context.openFileOutput(key, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Object readObject(String key) {
        Object object = null;
        try {
            FileInputStream fis = null;
            fis = context.openFileInput(key);
            ObjectInputStream ois = new ObjectInputStream(fis);
            object = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return object;
        }
        return object;
    }
}
