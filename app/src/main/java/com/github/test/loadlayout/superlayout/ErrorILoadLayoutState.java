package com.github.test.loadlayout.superlayout;

import android.util.Log;
import android.view.View;

import com.github.test.loadlayout.R;
import com.github.test.loadlayout.test.loadlayout.IFetchData;

import vip.ruoyun.widget.superlayout.ISuperLoadLayoutState;

/**
 * Created by ruoyun on 2018/8/1.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class ErrorILoadLayoutState implements ISuperLoadLayoutState {

    //或者通过接口来实现
    private IFetchData iFetchData;

    public ErrorILoadLayoutState(IFetchData iFetchData) {
        this.iFetchData = iFetchData;
    }

    @Override
    public int onCreateLayoutId() {
        return R.layout.layout_load_error;
    }

    @Override
    public void onCreateView(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("zyh", "点击layout_load_error");
                iFetchData.fetchData();
            }
        });
    }

    @Override
    public void onShow() {//动画开始的地方
    }

    @Override
    public void onHide() {//动画结束的地方
    }
}
