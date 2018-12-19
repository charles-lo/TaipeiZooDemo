package com.example.charleslo.taipeizoodemo;

import android.content.Context;
import android.content.Intent;

import com.example.charleslo.taipeizoodemo.service.NetworkServices;

import static com.example.charleslo.taipeizoodemo.model.Constant.*;

public class Controller {

    public static void getTpeZooCache(Context context) {
        Intent intent = new Intent(context, NetworkServices.class);
        intent.putExtra(CMD, CMD_GETCACHE);
        context.startService(new Intent(context, NetworkServices.class));
    }

    public static void getTpeZooData(Context context) {
        Intent intent = new Intent(context, NetworkServices.class);
        intent.putExtra(CMD, CMD_GETDATA);
        context.startService(new Intent(context, NetworkServices.class));
    }
}
