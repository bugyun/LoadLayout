package com.github.test.loadlayout.testNormalLoadLayout;

import com.github.bugyun.loadlayout.normal.LoadLayoutNormal;
import com.github.test.loadlayout.loadlayout.IFetchData;
import com.github.test.loadlayout.loadlayout.factory.ErrorILoadLayoutFactory;
import com.github.test.loadlayout.loadlayout.factory.LoadingLayoutFactory;
import com.github.test.loadlayout.loadlayout.factory.NoDataLayoutFactory;
import com.github.test.loadlayout.loadlayout.factory.NoNetworkLayoutFactory;

/**
 * Created by ruoyun on 2018/8/1.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class QuickCreateLoadBuildHelper {

    public static LoadLayoutNormal.Builder create(IFetchData iFetchData) {
        return new LoadLayoutNormal.Builder()
                .buildCreateErrorLayout(new ErrorILoadLayoutFactory(iFetchData))
                .buildCreateLoadingLayout(new LoadingLayoutFactory(iFetchData))
                .buildCreateNoDataLayout(new NoDataLayoutFactory(iFetchData))
                .buildCreateNoNetworkLayout(new NoNetworkLayoutFactory(iFetchData))
                .build();
    }
}
