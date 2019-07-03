package vip.ruoyun.widget;

import android.view.View;

public interface ISuperLoadLayoutState {

    int onCreateLayoutId();

    void onCreateView(View view);

    void onShow();

    void onHide();


    abstract class DefaultLoadLayoutState implements ISuperLoadLayoutState {

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
