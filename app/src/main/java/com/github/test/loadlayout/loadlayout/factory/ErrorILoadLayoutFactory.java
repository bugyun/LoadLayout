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
public class ErrorILoadLayoutFactory extends ILoadLayoutFactory.DefaultILoadLayoutFactory {

    //或者通过接口来实现
    private IFetchData iFetchData;

    public ErrorILoadLayoutFactory(IFetchData iFetchData) {
        this.iFetchData = iFetchData;
    }

    @Override
    public int createLayoutId() {
        return R.layout.layout_load_error;
    }

    @Override
    public View createView(View view) {


        return view;
    }

    @Override
    public void setOnClick() {
        //如果要刷新的话可以执行
//        activity.fetchData();
    }

    @Override
    public void onShow() {//动画开始的地方
    }

    @Override
    public void onHide() {//动画结束的地方
    }
}
