package com.example.sunwo.money_book;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

    public static Context context;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        TabHost.TabSpec spec;
        Intent intent;
        context = this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res = getResources();
        TabHost mTabHost = getTabHost();

        intent = new Intent().setClass(this, mainscreen.class);
        spec = mTabHost.newTabSpec("tab0").setIndicator("메인화면").setContent(intent);
        mTabHost.addTab(spec);

        intent = new Intent().setClass(this, inc_exp.class);
        spec = mTabHost.newTabSpec("tab1").setIndicator("수입지출").setContent(intent);
        mTabHost.addTab(spec);

        intent = new Intent().setClass(this, statistic.class);
        spec = mTabHost.newTabSpec("tab2").setIndicator("통계").setContent(intent);
        mTabHost.addTab(spec);

        intent = new Intent().setClass(this, budget.class);
        spec = mTabHost.newTabSpec("tab3").setIndicator("예산").setContent(intent);
        mTabHost.addTab(spec);

        intent = new Intent().setClass(this, settings.class);
        spec = mTabHost.newTabSpec("tab4").setIndicator("설정").setContent(intent);
        mTabHost.addTab(spec);

        mTabHost.setCurrentTab(0);
    }
}
