package com.github.test.loadlayout.testFragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.bugyun.loadlayout.LoadLayout;
import com.github.bugyun.loadlayout.LoadMode;
import com.github.test.loadlayout.R;

/**
 */
public class LoadLayoutTFragment extends Fragment {

    public LoadLayoutTFragment() {
    }

    public static LoadLayoutTFragment newInstance(String param1, String param2) {
        LoadLayoutTFragment fragment = new LoadLayoutTFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_load_layout_t, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoadLayout mLoadLayout = (LoadLayout) view.findViewById(R.id.mLoadLayout);
        mLoadLayout.setLoadMode(LoadMode.ERROR);
    }
}
