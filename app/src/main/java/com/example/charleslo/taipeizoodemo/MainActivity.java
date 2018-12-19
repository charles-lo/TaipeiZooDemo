package com.example.charleslo.taipeizoodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.charleslo.taipeizoodemo.utils.NetworkUtil;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (NetworkUtil.checkNetworkState(this)) {
            Controller.getTpeZooCache(this);
            Controller.getTpeZooData(this);
        } else {
            
        }
    }
}
