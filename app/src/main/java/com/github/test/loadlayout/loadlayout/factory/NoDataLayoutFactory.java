package com.github.test.loadlayout.loadlayout.factory;

import android.util.Log;
import android.view.View;

import com.github.bugyun.loadlayout.ILoadLayoutFactory;
import com.github.test.loadlayout.R;
import com.github.test.loadlayout.loadlayout.IFetchData;

/**
 * Created by ruoyun on 2018/8/1.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class NoDataLayoutFactory implements ILoadLayoutFactory {

    //或者通过接口来实现
    private IFetchData iFetchData;

    public NoDataLayoutFactory(IFetchData iFetchData) {
        this.iFetchData = iFetchData;
    }

    @Override
    public int createLayoutId() {
        return R.layout.layout_load_no_data;
    }

    @Override
    public View createView(View view) {
        Log.i("zyh", "NoNetworkLayoutFactory   createView");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("zyh", "点击layout_load_no_data");
                iFetchData.fetchData();
            }
        });
        return view;
    }

    @Override
    public void onShow() {

    }

    @Override
    public void onHide() {

    }
}
