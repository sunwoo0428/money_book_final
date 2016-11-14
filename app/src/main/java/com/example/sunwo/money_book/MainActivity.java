package com.example.sunwo.money_book;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

    public static Context context;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        TabHost.TabSpec spec;
        Intent intent;
        context = this;

        final DBBudget dbBudget = new DBBudget(getApplicationContext(), "money_bdg.db", null, 1);

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


        try{
            SharedPreferences mPref = getSharedPreferences("isFirst", Activity.MODE_PRIVATE);

            Boolean bFirst = mPref.getBoolean("isFirst", false);
            if(bFirst == false)
            {
                Log.d("version", "first");
                SharedPreferences.Editor editor = mPref.edit();
                editor.putBoolean("isFirst", true);
                editor.commit();
                intent = new Intent().setClass(this, budget_view.class);
                spec = mTabHost.newTabSpec("tab3").setIndicator("예산").setContent(intent);
                mTabHost.addTab(spec);
            }
            if(bFirst == true)
            {
                Log.d("version", "not first");
                if(dbBudget.getBid()==2){
                    intent = new Intent().setClass(this, budget_view.class);
                    spec = mTabHost.newTabSpec("tab3").setIndicator("예산").setContent(intent);
                    mTabHost.addTab(spec);
                    mTabHost.setCurrentTab(3);
                }
                else{
                    intent = new Intent().setClass(this, budget_new.class);
                    spec = mTabHost.newTabSpec("tab3").setIndicator("예산").setContent(intent);
                    mTabHost.addTab(spec);
                    mTabHost.setCurrentTab(3);
                }
            }

        }finally{};


        intent = new Intent().setClass(this, settings.class);
        spec = mTabHost.newTabSpec("tab4").setIndicator("설정").setContent(intent);
        mTabHost.addTab(spec);

        if(Loading.count == 0) {
            mTabHost.setCurrentTab(0);
            Loading.count++;
        }

    }
}
