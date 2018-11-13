package com.github.test.loadlayout.testFactoryLoadLayout;

import com.github.bugyun.factoryloadlayout.LoadLayout;
import com.github.test.loadlayout.loadlayout.IFetchData;
import com.github.test.loadlayout.testFactoryLoadLayout.factory.ErrorILoadLayoutState;
import com.github.test.loadlayout.testFactoryLoadLayout.factory.LoadingLayoutState;
import com.github.test.loadlayout.testFactoryLoadLayout.factory.NoDataLayoutState;
import com.github.test.loadlayout.testFactoryLoadLayout.factory.NoNetworkLayoutState;

/**
 * Created by ruoyun on 2018/8/1.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class QuickCreateLoadBuildHelper {

    public static LoadLayout.Builder create(IFetchData iFetchData) {
        return new LoadLayout.Builder()
                .buildCreateErrorLayout(new ErrorILoadLayoutState(iFetchData))
                .buildCreateLoadingLayout(new LoadingLayoutState(iFetchData))
                .buildCreateNoDataLayout(new NoDataLayoutState(iFetchData))
                .buildCreateNoNetworkLayout(new NoNetworkLayoutState(iFetchData))
                .build();
    }
}
