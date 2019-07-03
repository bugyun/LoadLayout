package com.github.test.loadlayout.test.testNormalLoadLayout;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.github.bugyun.loadlayout.LoadMode;
import com.github.bugyun.loadlayout.normal.LoadLayoutNormal;
import com.github.test.loadlayout.BaseActivity;
import com.github.test.loadlayout.R;

/**
 * 没有倾入性，不需要设置LayoutInflaterCompat.setFactory
 */
public class LoadLayouNormalActivity extends BaseActivity {

    private LoadLayoutNormal.Builder builder = QuickCreateLoadBuildHelper.create(this);

    private LoadLayoutNormal mLoadLayoutNormal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_layou_normal);
        mLoadLayoutNormal = (LoadLayoutNormal) findViewById(R.id.mLoadLayoutNormal);
        mLoadLayoutNormal.setBuilder(builder);
        mLoadLayoutNormal.setLoadMode(LoadMode.ERROR);
    }

    @Override
    public void fetchData() {
        mLoadLayoutNormal.setLoadMode(LoadMode.LOADING);
        Log.i("zyh", Thread.currentThread() + "当前线程");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("zyh", "执行 LoadMode.SUCCESS");
                mLoadLayoutNormal.setLoadMode(LoadMode.SUCCESS);
            }
        }, 3000);
    }
}
