package com.github.test.loadlayout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_load_layout_t, container, false);
    }

}
