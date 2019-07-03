package com.github.test.loadlayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.github.test.loadlayout.superlayout.SuperCreateLoadBuildHelper;

import vip.ruoyun.widget.quick.QuickLoadLayout;
import vip.ruoyun.widget.superlayout.SuperLoadLayout;

public class SuperLoadLayoutActivity extends BaseActivity {

    private SuperLoadLayout mSuperLoadLayout;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_load_layout);
        mSuperLoadLayout = findViewById(R.id.mSuperLoadLayout);
        mSuperLoadLayout.setBuilder(SuperCreateLoadBuildHelper.create(this));
        mSuperLoadLayout.setLoadMode(SuperLoadLayout.LoadMode.LOADING);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSuperLoadLayout.setLoadMode(QuickLoadLayout.LoadMode.NO_NETWORK);
            }
        }, 2000);
    }

    @Override
    public void fetchData() {
        mSuperLoadLayout.setLoadMode(SuperLoadLayout.LoadMode.LOADING);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSuperLoadLayout.setLoadMode(SuperLoadLayout.LoadMode.SUCCESS);
            }
        }, 3000);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SuperLoadLayoutActivity.class);
        context.startActivity(intent);
    }
}
