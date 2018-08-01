package com.github.test.loadlayout.loadlayout.factory;

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
public class LoadingLayoutFactory implements ILoadLayoutFactory {

    //或者通过接口来实现
    private IFetchData iFetchData;

    public LoadingLayoutFactory(IFetchData iFetchData) {
        this.iFetchData = iFetchData;
    }

    @Override
    public int createLayoutId() {
        return R.layout.layout_load_loading;
    }

    @Override
    public View createView(View view) {
        return view;
    }

    @Override
    public void onShow() {

    }

    @Override
    public void onHide() {

    }
}
