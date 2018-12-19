package com.example.charleslo.taipeizoodemo.utils;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.charleslo.taipeizoodemo.R;

public class NetworkUtil {
    static public boolean checkNetworkState(Context ctx) {
        boolean flag = false;
// get network info
        ConnectivityManager manager = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
// check connect status
        if (manager.getActiveNetworkInfo() != null) {
            flag = manager.getActiveNetworkInfo().isAvailable();
        }
        if (!flag) {
            setNetwork(ctx);
        } else {
            isNetworkAvailable(ctx, manager);
        }
        return flag;
    }

    static private void setNetwork(Context ctx){
        Toast.makeText(ctx, "wifi is closed!", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
        builder.setIcon(R.drawable.ic_launcher_background);
        builder.setTitle("網路提示資訊");
        builder.setMessage("網路不可用，如果繼續，請先設定網路！");
        builder.setPositiveButton("設定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = null;
                if (android.os.Build.VERSION.SDK_INT > 10) {
                    intent = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
                } else {
                    intent = new Intent();
                    ComponentName component = new ComponentName(
                            "com.android.settings",
                            "com.android.settings.WirelessSettings");
                    intent.setComponent(component);
                    intent.setAction("android.intent.action.VIEW");
                }
                ctx.startActivity(intent);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create();
        builder.show();
    }
    /**
     */
    static private void isNetworkAvailable(Context ctx, ConnectivityManager manager){
        NetworkInfo.State gprs = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        NetworkInfo.State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        if(gprs == NetworkInfo.State.CONNECTED || gprs == NetworkInfo.State.CONNECTING){
//            Toast.makeText(ctx, "wifi is open! gprs", Toast.LENGTH_SHORT).show();
        }

        if(wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING){
//            Toast.makeText(ctx, "wifi is open! wifi", Toast.LENGTH_SHORT).show();
        }
    }
}
