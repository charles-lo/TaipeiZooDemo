package com.example.charleslo.taipeizoodemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.charleslo.taipeizoodemo.event.ActivityEvent;
import com.example.charleslo.taipeizoodemo.fragmemt.FragmentSection;
import com.example.charleslo.taipeizoodemo.fragmemt.FtPlantDetail;
import com.example.charleslo.taipeizoodemo.fragmemt.FtSectionDetail;
import com.example.charleslo.taipeizoodemo.utils.NetworkUtil;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.example.charleslo.taipeizoodemo.model.Constant.AE_PLANT_DETAIL;
import static com.example.charleslo.taipeizoodemo.model.Constant.AE_SECTION_DETAIL;
import static com.example.charleslo.taipeizoodemo.model.Constant.AE_SECTION_LIST;

public class MainActivity extends AppCompatActivity {

    private boolean m_bSaveInstanceState;
    private Toolbar m_Toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(MainActivity.this);
        setContentView(R.layout.activity_main);
        m_Toolbar = (Toolbar) findViewById(R.id.toolbar);
        m_Toolbar.setNavigationIcon(R.drawable.menu);
        setSupportActionBar(m_Toolbar);

        EventBus.getDefault().register(this);
        if (NetworkUtil.checkNetworkState(this)) {
            Controller.getTpeZooCache(this);
            Controller.getTpeZooData(this);
        } else {

        }
    }

    public void setToobarTitle(String title) {
        m_Toolbar.setTitle(title);
    }

    public void setToobarTitle(int rid) {
        m_Toolbar.setTitle(rid);
    }

    public void setToobarIcon(int rid) {
        m_Toolbar.setNavigationIcon(rid);
    }

    protected void addFragmentWithBackStack(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.container, fragment, fragment.getClass().getName());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    protected void addFragmentWithOutBackStack(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.container, fragment, fragment.getClass().getName());
        transaction.commit();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ActivityEvent event) {
        switch (event.getMsg()) {
            case AE_SECTION_LIST: {

                m_Toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });

                if (!m_bSaveInstanceState) {
                    addFragmentWithOutBackStack(FragmentSection.newInstance(""));
                }
                break;
            }
            case AE_SECTION_DETAIL: {
                m_Toolbar.setNavigationIcon(R.drawable.sync);
                m_Toolbar.setNavigationOnClickListener(v -> onBackPressed());

                if (!m_bSaveInstanceState) {
                    addFragmentWithBackStack(FtSectionDetail.newInstance(event.getIdx()));
                }
                break;
            }
            case AE_PLANT_DETAIL: {
                m_Toolbar.setNavigationIcon(R.drawable.sync);
                m_Toolbar.setNavigationOnClickListener(v -> {
                    onBackPressed();
                });

                if (!m_bSaveInstanceState) {
                    addFragmentWithBackStack(FtPlantDetail.newInstance(event.getIdx()));
                }
                break;
            }
            default: {

            }
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        m_bSaveInstanceState = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }

}
