package com.github.bugyun.loadlayout;

import android.view.View;

/**
 * Created by ruoyun on 2018/7/31.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:Strategy 策略模式
 */
public interface ILoadLayoutFactory {

    int createLayoutId();

    View createView(View view);

    void onShow();

    void onHide();


    abstract class DefaultILoadLayoutFactory implements ILoadLayoutFactory {

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
}
