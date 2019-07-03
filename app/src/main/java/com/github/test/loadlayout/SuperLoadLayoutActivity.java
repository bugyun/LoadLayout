package com.github.test.loadlayout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SuperLoadLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_load_layout);
    }


    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SuperLoadLayoutActivity.class);
        context.startActivity(intent);
    }
}
