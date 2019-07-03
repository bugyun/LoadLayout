package com.github.test.loadlayout.test.testFactoryLoadLayout;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.bugyun.factoryloadlayout.LoadLayout;
import com.github.bugyun.factoryloadlayout.LoadLayoutHelper;
import com.github.bugyun.factoryloadlayout.LoadMode;
import com.github.test.loadlayout.R;
import com.github.test.loadlayout.test.loadlayout.IFetchData;

public class FactoryLoadLayoutActivity extends AppCompatActivity implements IFetchData {

    LoadLayout.Builder builder = QuickCreateLoadBuildHelper.create(this);
    private LoadLayout mLoadLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LoadLayoutHelper.setFactory(this, builder);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory_load_layout);
        mLoadLayout = (LoadLayout) findViewById(R.id.mLoadLayout);
        mLoadLayout.setLoadMode(LoadMode.ERROR);
    }

    @Override
    public void fetchData() {
        mLoadLayout.setLoadMode(LoadMode.LOADING);
        Log.i("zyh", Thread.currentThread() + "当前线程");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("zyh", "执行 LoadMode.SUCCESS");
                mLoadLayout.setLoadMode(LoadMode.SUCCESS);
            }
        }, 3000);
    }
}
