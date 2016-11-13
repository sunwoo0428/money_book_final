package com.example.sunwo.money_book;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class statistic extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        TabHost.TabSpec spec;
        Intent intent;

        Resources res = getResources();
        TabHost mTabHost = getTabHost();

        intent = new Intent().setClass(this, statistic_lin.class);
        spec = mTabHost.newTabSpec("tab1").setIndicator("선형").setContent(intent);
        mTabHost.addTab(spec);

        intent = new Intent().setClass(this, statistic_cir.class);
        spec = mTabHost.newTabSpec("tab2").setIndicator("원형").setContent(intent);
        mTabHost.addTab(spec);

        mTabHost.setCurrentTab(0);
    }
}
