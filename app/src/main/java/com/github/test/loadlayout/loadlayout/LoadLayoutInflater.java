package com.github.test.loadlayout.loadlayout;

import android.content.Context;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.github.bugyun.loadlayout.LoadLayoutT;

/**
 * Created by ruoyun on 2018/8/1.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class LoadLayoutInflater {

    public static void setFactory(final AppCompatActivity appCompatActivity, final LoadLayoutT.Builder builder) {
        LayoutInflaterCompat.setFactory(appCompatActivity.getLayoutInflater(), new LayoutInflaterFactory() {
            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
                Log.i("zyh", name);
                //你可以在这里直接new自定义View
                if (name.equalsIgnoreCase("com.github.bugyun.loadlayout.LoadLayoutT")) {
                    Log.i("zyh", "LoadLayoutT   equalsIgnoreCase 执行方法 ");
                    return new LoadLayoutT(context, attrs, builder);
                }
                //你可以在这里将系统类替换为自定义View
                //appcompat 创建view代码
                AppCompatDelegate delegate = appCompatActivity.getDelegate();
                View view = delegate.createView(parent, name, context, attrs);

                if ((view instanceof LoadLayoutT)) {
//                    ((LoadLayout) view).setBuilder(builder);
                    Log.i("zyh", "LoadLayout   执行方法");
                }
                Log.i("zyh", "return view ");
                return view;
            }
        });

    }
}
