package com.github.bugyun.loadlayout;

import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ruoyun on 2018/8/1.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class LoadLayoutHelper {
    public static void setFactory(final AppCompatActivity appCompatActivity, final LoadLayoutT.Builder builder) {
        LayoutInflaterCompat.setFactory(appCompatActivity.getLayoutInflater(), new LoadLayoutInflaterFactory(appCompatActivity, builder));
    }
}
