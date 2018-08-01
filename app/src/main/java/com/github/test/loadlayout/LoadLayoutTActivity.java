package com.github.test.loadlayout;

import android.os.Bundle;

import com.github.bugyun.loadlayout.LoadLayoutT;
import com.github.test.loadlayout.loadlayout.LoadLayoutInflater;
import com.github.test.loadlayout.loadlayout.QuickCreateLoadBuildHelper;

public class LoadLayoutTActivity extends BaseActivity {

    LoadLayoutT.Builder builder = QuickCreateLoadBuildHelper.create(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LoadLayoutInflater.setFactory(this, builder);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_layout_t);


    }

    @Override
    public void fetchData() {

    }
}
