package com.github.test.loadlayout;

import android.app.Application;

import com.github.bugyun.loadlayout.LoadLayoutFactory;
import com.github.bugyun.loadlayout.LoadLayoutT;

/**
 * Created by ruoyun on 2018/7/31.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LoadLayoutT.setBuilder(new LoadLayoutT.Builder()
                .buildCreateErrorLayout(new LoadLayoutFactory.DefaultLoadLayoutFactory() {

                    @Override
                    public int createLayoutId() {
                        return 0;
                    }
                })
                .buildCreateLoadingLayout(new LoadLayoutFactory.DefaultLoadLayoutFactory() {
                    @Override
                    public int createLayoutId() {
                        return 0;
                    }
                })
                .buildCreateNoDataLayout(new LoadLayoutFactory.DefaultLoadLayoutFactory() {
                    @Override
                    public int createLayoutId() {
                        return 0;
                    }
                })
                .buildCreateNoNetworkLayout(new LoadLayoutFactory.DefaultLoadLayoutFactory() {
                    @Override
                    public int createLayoutId() {
                        return 0;
                    }
                })
                .build());
    }
}
