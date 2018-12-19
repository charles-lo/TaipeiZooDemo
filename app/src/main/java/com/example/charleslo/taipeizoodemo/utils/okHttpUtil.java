package com.example.charleslo.taipeizoodemo.utils;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class okHttpUtil {
    private static okHttpUtil intance = null;
    private static String TAG = okHttpUtil.class.getSimpleName();
    OkHttpClient m_Client = new OkHttpClient();

    private okHttpUtil() {
    }

    public static synchronized okHttpUtil getInstance() {
        if (intance == null) {
            intance = new okHttpUtil();
        }
        return intance;
    }

    public <T> ArrayList<T> getTpeGovData(String url, T o) {
        ArrayList<T> listdata = new ArrayList<>();
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = m_Client.newCall(request).execute();
            JSONObject object = new JSONObject(response.body().string());
            JSONArray jArray = object.getJSONObject("result").getJSONArray("results");
            if (jArray != null) {
                for (int i = 0; i < jArray.length(); i++) {
                    listdata.add((T) new Gson().fromJson(jArray.getString(i), o.getClass()));
                }
            }
            Log.d(TAG, String.valueOf(listdata.size()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listdata;
    }
}
