package com.github.test.loadlayout;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.github.bugyun.loadlayout.LoadLayout;
import com.github.bugyun.loadlayout.LoadMode;

/**
 * https://blog.csdn.net/lmj623565791/article/details/51503977
 */
public class MainActivity extends AppCompatActivity {

    private LoadLayout mLoadLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final LayoutInflater layoutInflater = getLayoutInflater();
        LayoutInflaterCompat.setFactory(layoutInflater, new LayoutInflaterFactory() {
            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
                Log.i("zyh", name);
                //你可以在这里直接new自定义View
                if (name.equalsIgnoreCase("com.github.bugyun.loadlayout.LoadLayout")) {
                    Log.i("zyh", "LoadLayout   equalsIgnoreCase 执行方法 ");
                    return new LoadLayout(context, attrs, LoadMode.ERROR);
                }
                //你可以在这里将系统类替换为自定义View
                //appcompat 创建view代码
                AppCompatDelegate delegate = getDelegate();
                View view = delegate.createView(parent, name, context, attrs);

                if ((view instanceof LoadLayout)) {
//                    ((LoadLayout) view).setBuilder(builder);
                    Log.i("zyh", "LoadLayout   执行方法");
                }
                Log.i("zyh", "return view ");
                return view;
            }
        });
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
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("zyh", "你好");
                    }
                }, 9999);

            }
        });




    }
}
