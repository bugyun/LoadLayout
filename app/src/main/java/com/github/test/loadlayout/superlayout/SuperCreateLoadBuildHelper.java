package com.github.test.loadlayout.superlayout;

import com.github.test.loadlayout.test.loadlayout.IFetchData;

import vip.ruoyun.widget.superlayout.SuperLoadLayout;

/**
 * Created by ruoyun on 2019-07-03.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class SuperCreateLoadBuildHelper {

    public static SuperLoadLayout.Builder create(IFetchData iFetchData) {
        return new SuperLoadLayout.Builder()
                .buildCreateErrorLayout(new ErrorILoadLayoutState(iFetchData))
                .buildCreateLoadingLayout(new LoadingLayoutState(iFetchData))
                .buildCreateNoDataLayout(new NoDataLayoutState(iFetchData))
                .buildCreateNoNetworkLayout(new NoNetworkLayoutState(iFetchData))
                .build();
    }
}
