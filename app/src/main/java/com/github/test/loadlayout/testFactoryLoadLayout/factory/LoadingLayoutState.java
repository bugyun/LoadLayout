package com.github.test.loadlayout.testFactoryLoadLayout.factory;

import android.view.View;

import com.github.bugyun.factoryloadlayout.ILoadLayoutState;
import com.github.test.loadlayout.R;
import com.github.test.loadlayout.loadlayout.IFetchData;

/**
 * Created by ruoyun on 2018/8/1.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class LoadingLayoutState implements ILoadLayoutState {

    //或者通过接口来实现
    private IFetchData iFetchData;

    public LoadingLayoutState(IFetchData iFetchData) {
        this.iFetchData = iFetchData;
    }

    @Override
    public int onCreateLayoutId() {
        return R.layout.layout_load_loading;
    }

    @Override
    public void onCreateView(View view) {

    }

    @Override
    public void onShow() {

    }

    @Override
    public void onHide() {

    }
}
