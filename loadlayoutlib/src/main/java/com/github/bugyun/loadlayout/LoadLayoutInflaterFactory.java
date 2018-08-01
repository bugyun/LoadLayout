package com.github.bugyun.loadlayout;

import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by ruoyun on 2018/8/1.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class LoadLayoutInflaterFactory implements LayoutInflaterFactory {

    private AppCompatActivity appCompatActivity;

    private LoadLayoutT.Builder builder;

    public LoadLayoutInflaterFactory(AppCompatActivity appCompatActivity, LoadLayoutT.Builder builder) {
        this.appCompatActivity = appCompatActivity;
        this.builder = builder;
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        if (name.equalsIgnoreCase("com.github.bugyun.loadlayout.LoadLayoutT")) {
            Log.i("zyh", "LoadLayoutT   equalsIgnoreCase 执行方法 ");
            return new LoadLayoutT(context, attrs, builder);
        }
        AppCompatDelegate delegate = appCompatActivity.getDelegate();
        return delegate.createView(parent, name, context, attrs);
    }
}
