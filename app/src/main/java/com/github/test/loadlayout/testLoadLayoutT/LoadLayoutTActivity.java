package com.github.test.loadlayout.testLoadLayoutT;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.github.bugyun.loadlayout.LoadLayoutT;
import com.github.bugyun.loadlayout.LoadMode;
import com.github.test.loadlayout.BaseActivity;
import com.github.test.loadlayout.R;
import com.github.test.loadlayout.loadlayout.LoadLayoutInflater;
import com.github.test.loadlayout.loadlayout.QuickCreateLoadBuildHelper;

public class LoadLayoutTActivity extends BaseActivity {

    LoadLayoutT.Builder builder = QuickCreateLoadBuildHelper.create(this);
    private LoadLayoutT mLoadLayoutT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LoadLayoutInflater.setFactory(this, builder);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_layout_t);
        mLoadLayoutT = (LoadLayoutT) findViewById(R.id.mLoadLayoutT);
        mLoadLayoutT.setLoadMode(LoadMode.ERROR);
    }

    @Override
    public void fetchData() {
        mLoadLayoutT.setLoadMode(LoadMode.LOADING);
        Log.i("zyh", Thread.currentThread() + "当前线程");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("zyh", "执行 LoadMode.SUCCESS");
                mLoadLayoutT.setLoadMode(LoadMode.SUCCESS);
            }
        }, 3000);
    }
}
