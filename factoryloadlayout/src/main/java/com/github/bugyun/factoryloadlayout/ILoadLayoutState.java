package com.github.bugyun.factoryloadlayout;

import android.view.View;

public interface ILoadLayoutState {

    int onCreateLayoutId();

    void onCreateView(View view);

    void onShow();

    void onHide();


    public abstract class DefaultLoadLayoutState implements ILoadLayoutState {

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
}
