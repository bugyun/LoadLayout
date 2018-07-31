package com.github.test.loadlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.bugyun.loadlayout.LoadLayout;
import com.github.bugyun.loadlayout.LoadMode;

public class MainActivity extends AppCompatActivity {

    private LoadLayout mLoadLayout;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("zyh", "onCreate 1");
        setContentView(R.layout.activity_main);
        Log.d("zyh", "onCreate 2");
        mLoadLayout = (LoadLayout) findViewById(R.id.mLoadLayout);
//        mLoadLayout.setLoadMode(LoadMode.LOADING);//加载状态
        mLoadLayout.setLoadMode(LoadMode.ERROR);//错误状态,没有网络
//        mLoadLayout.setLoadMode(LoadMode.NO_DATA);//没有数据状态
//        mLoadLayout.setLoadMode(LoadMode.SUCCESS);//成功状态

        mLoadLayout.setOnReloadListener(new LoadLayout.OnReloadListener() {
            @Override
            public void onReload() {
                mLoadLayout.setLoadMode(LoadMode.LOADING);//加载状态
            }
        });
    }
}
