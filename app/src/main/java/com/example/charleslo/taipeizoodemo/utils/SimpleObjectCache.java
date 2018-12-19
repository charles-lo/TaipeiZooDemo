package com.example.charleslo.taipeizoodemo.utils;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class SimpleObjectCache {

    public static void saveObject(Object object, String id,Context context) {
        try {
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream(new File(context.getCacheDir(), "") + "/"+id));
            out.writeObject(object);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Object loadObject(String id,Context context) throws FileNotFoundException {
        Object object = null;
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(new File(new File(context.getCacheDir(), "") + "/"+id)));
            object = in.readObject();
            in.close();
        }catch (IOException | ClassNotFoundException e){
            throw new FileNotFoundException();
        }
        return object;
    }

    public static Boolean removeObject(String id,Context context){
        File file = new File(new File(context.getCacheDir(), "") + "/"+id);
        return file.delete();
    }
}