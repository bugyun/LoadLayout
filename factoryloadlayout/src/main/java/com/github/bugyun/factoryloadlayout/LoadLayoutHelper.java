package com.github.bugyun.factoryloadlayout;

import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ruoyun on 2018/8/1.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class LoadLayoutHelper {
    public static void setFactory(final AppCompatActivity appCompatActivity, final LoadLayout.Builder builder) {
        LayoutInflaterCompat.setFactory(appCompatActivity.getLayoutInflater(), new LoadLayoutInflaterFactory(appCompatActivity, builder));
    }
}
