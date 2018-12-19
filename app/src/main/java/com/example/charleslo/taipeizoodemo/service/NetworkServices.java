package com.example.charleslo.taipeizoodemo.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.charleslo.taipeizoodemo.model.Cache;
import com.example.charleslo.taipeizoodemo.model.ZooPlant;
import com.example.charleslo.taipeizoodemo.model.ZooSection;
import com.example.charleslo.taipeizoodemo.utils.SimpleObjectCache;
import com.example.charleslo.taipeizoodemo.utils.okHttpUtil;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static com.example.charleslo.taipeizoodemo.model.Constant.*;

public class NetworkServices extends IntentService {
    private static String TAG = NetworkServices.class.getSimpleName();

    public NetworkServices() {
        super(NetworkServices.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        switch (intent.getIntExtra(CMD, 0)) {
            case CMD_GETCACHE: {
                // Retrieving
                try {
                    Cache.getInstance().m_ZooSectionList = (ArrayList<ZooSection>) SimpleObjectCache
                            .loadObject(LIST_ZOOSECTION, getApplicationContext());

                    Cache.getInstance().m_ZooPlantList = (ArrayList<ZooPlant>) SimpleObjectCache
                            .loadObject(LIST_ZOOPLANT, getApplicationContext());
                    Log.d(TAG, " case CMD_GETCACHE");

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            }
            case CMD_GETDATA: {
                SimpleObjectCache.saveObject(okHttpUtil.getInstance().<ZooSection>getTpeGovData(URL_ZOOSECTION, new ZooSection()), LIST_ZOOSECTION, getApplicationContext());
                SimpleObjectCache.saveObject(okHttpUtil.getInstance().<ZooPlant>getTpeGovData(URL_ZOOPLANT, new ZooPlant()), LIST_ZOOPLANT, getApplicationContext());

                Log.d(TAG, " case CMD_GETDATA");
                break;
            }
            default: {
                break;
            }
        }

    }

}



