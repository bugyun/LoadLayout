package com.github.test.loadlayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import vip.ruoyun.widget.quick.QuickLoadLayout;

public class QuickLoadLayoutActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private QuickLoadLayout mQuickLoadLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_load_layout);
        mQuickLoadLayout = findViewById(R.id.mQuickLoadLayout);
        mQuickLoadLayout.setOnReloadListener(new QuickLoadLayout.OnReloadListener() {
            @Override
            public void onReload() {
                mQuickLoadLayout.setLoadMode(QuickLoadLayout.LoadMode.LOADING);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mQuickLoadLayout.setLoadMode(QuickLoadLayout.LoadMode.SUCCESS);
                    }
                }, 2000);
            }
        });
        mQuickLoadLayout.setLoadMode(QuickLoadLayout.LoadMode.LOADING);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mQuickLoadLayout.setLoadMode(QuickLoadLayout.LoadMode.ERROR);
            }
        }, 2000);
    }


    public static void actionStart(Context context) {
        Intent intent = new Intent(context, QuickLoadLayoutActivity.class);
        context.startActivity(intent);
    }
}
