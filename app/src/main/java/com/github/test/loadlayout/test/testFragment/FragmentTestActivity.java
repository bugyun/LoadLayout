package com.github.test.loadlayout.test.testFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.github.bugyun.loadlayout.LoadLayout;
import com.github.test.loadlayout.R;

public class FragmentTestActivity extends AppCompatActivity {

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
                    return new LoadLayout(context, attrs, 100);
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
        setContentView(R.layout.activity_fragment_test);
    }
}
