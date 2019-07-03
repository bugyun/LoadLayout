package com.github.test.loadlayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * 最普通的 LoadLayout
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mBtQuick = findViewById(R.id.mBtQuick);
        Button mBtSuper = findViewById(R.id.mBtSuper);
        mBtQuick.setOnClickListener(this);
        mBtSuper.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mBtQuick:
                QuickLoadLayoutActivity.actionStart(this);
                break;
            case R.id.mBtSuper:
                SuperLoadLayoutActivity.actionStart(this);
                break;
        }
    }
}
