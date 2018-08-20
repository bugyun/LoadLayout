package com.github.bugyun.factoryloadlayout;

import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ruoyun on 2018/8/1.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class LoadLayoutInflaterFactory implements LayoutInflaterFactory {

    private AppCompatActivity appCompatActivity;

    private LoadLayout.Builder builder;

    public LoadLayoutInflaterFactory(AppCompatActivity appCompatActivity, LoadLayout.Builder builder) {
        this.appCompatActivity = appCompatActivity;
        this.builder = builder;
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        if (name.equalsIgnoreCase("com.github.bugyun.factoryloadlayout.LoadLayout")) {
            return new LoadLayout(context, attrs, builder);
        }
        AppCompatDelegate delegate = appCompatActivity.getDelegate();
        return delegate.createView(parent, name, context, attrs);
    }
}
