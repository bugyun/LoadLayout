package com.github.test.loadlayout.testLoadLayoutT;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.github.bugyun.loadlayout.LoadLayoutT;
import com.github.bugyun.loadlayout.LoadMode;
import com.github.test.loadlayout.BaseActivity;
import com.github.test.loadlayout.R;
import com.github.test.loadlayout.loadlayout.LoadLayoutInflater;
import com.github.test.loadlayout.loadlayout.QuickCreateLoadBuildHelper;

public class LoadLayoutTActivity extends BaseActivity {

    LoadLayoutT.Builder builder = QuickCreateLoadBuildHelper.create(this);
    private LoadLayoutT mLoadLayoutT;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mLoadLayoutT.setLoadMode(LoadMode.SUCCESS);
        }
    };

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
        handler.sendEmptyMessageAtTime(1, 30000);
    }
}
