package com.github.bugyun.loadlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by ruoyun on 2016/10/14.
 * 加载布局的组合控件
 */
public class LoadLayoutT extends FrameLayout {

    private Builder builder;

    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    private View layout_load_error;//加载失败
    private View layout_load_loading;//正在加载中
    private View layout_load_no_data;//没有数据
    private View layout_load_no_network;//没有数据
    private View mContentView;//需要加载的界面

    private int mLoadMode = LoadMode.SUCCESS;

    public int getLoadMode() {
        return mLoadMode;
    }

    public void setLoadMode(int mLoadMode) {
        this.mLoadMode = mLoadMode;
        setLayoutLoadMode(mLoadMode);
    }

    public LoadLayoutT(Context context) {
        super(context);
        initView(context);
    }

    public LoadLayoutT(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoadLayoutT(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LoadLayoutT(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        Log.d("zyh", "initView");
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layout_load_error = builder.errorLayoutFactory.createView(layoutInflater.inflate(builder.errorLayoutFactory.createLayoutId(), this, false));
        layout_load_loading = builder.loadingLayoutFactory.createView(layoutInflater.inflate(builder.loadingLayoutFactory.createLayoutId(), this, false));
        layout_load_no_data = builder.noDataLayoutFactory.createView(layoutInflater.inflate(builder.noDataLayoutFactory.createLayoutId(), this, false));
        layout_load_no_network = builder.noNetworkLayoutFactory.createView(layoutInflater.inflate(builder.noNetworkLayoutFactory.createLayoutId(), this, false));
        layout_load_error.setVisibility(mLoadMode == LoadMode.ERROR ? View.VISIBLE : View.GONE);
        layout_load_loading.setVisibility(mLoadMode == LoadMode.LOADING ? View.VISIBLE : View.GONE);
        layout_load_no_data.setVisibility(mLoadMode == LoadMode.NO_DATA ? View.VISIBLE : View.GONE);
        layout_load_no_data.setVisibility(mLoadMode == LoadMode.NO_DATA ? View.VISIBLE : View.GONE);
        layout_load_no_network.setVisibility(mLoadMode == LoadMode.NO_NETWORK ? View.VISIBLE : View.GONE);
        builder.errorLayoutFactory.setOnClick();
        builder.loadingLayoutFactory.setOnClick();
        builder.noDataLayoutFactory.setOnClick();
        builder.noNetworkLayoutFactory.setOnClick();
        addView(layout_load_error);
        addView(layout_load_loading);
        addView(layout_load_no_data);
        addView(layout_load_no_network);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() >= 4) {
            mContentView = getChildAt(4);
            mContentView.setVisibility(mLoadMode == LoadMode.SUCCESS ? View.VISIBLE : View.GONE);
        }
        if (getChildCount() > 5) {
            throw new IllegalStateException("LoadLayout can host only one direct child");
        }
    }

    private void setLayoutLoadMode(int mode) {
        switch (mode) {
            case LoadMode.ERROR:
                layout_load_error.setVisibility(View.VISIBLE);
                layout_load_loading.setVisibility(View.GONE);
                layout_load_no_data.setVisibility(View.GONE);
                layout_load_no_network.setVisibility(View.GONE);
                if (mContentView != null)
                    mContentView.setVisibility(View.GONE);
                builder.errorLayoutFactory.onShow();
                builder.loadingLayoutFactory.onHide();
                builder.noNetworkLayoutFactory.onHide();
                builder.noNetworkLayoutFactory.onHide();
                break;
            case LoadMode.LOADING:
                layout_load_error.setVisibility(View.GONE);
                layout_load_loading.setVisibility(View.VISIBLE);
                layout_load_no_data.setVisibility(View.GONE);
                layout_load_no_network.setVisibility(View.GONE);
                if (mContentView != null)
                    mContentView.setVisibility(View.GONE);
                builder.errorLayoutFactory.onHide();
                builder.loadingLayoutFactory.onShow();
                builder.noNetworkLayoutFactory.onHide();
                builder.noNetworkLayoutFactory.onHide();
                break;
            case LoadMode.NO_DATA:
                layout_load_error.setVisibility(View.GONE);
                layout_load_loading.setVisibility(View.GONE);
                layout_load_no_data.setVisibility(View.VISIBLE);
                layout_load_no_network.setVisibility(View.GONE);
                if (mContentView != null)
                    mContentView.setVisibility(View.GONE);
                builder.errorLayoutFactory.onHide();
                builder.loadingLayoutFactory.onHide();
                builder.noNetworkLayoutFactory.onShow();
                builder.noNetworkLayoutFactory.onHide();
                break;
            case LoadMode.NO_NETWORK:
                layout_load_error.setVisibility(View.GONE);
                layout_load_loading.setVisibility(View.GONE);
                layout_load_no_data.setVisibility(View.GONE);
                layout_load_no_network.setVisibility(View.VISIBLE);
                if (mContentView != null)
                    mContentView.setVisibility(View.GONE);
                builder.errorLayoutFactory.onHide();
                builder.loadingLayoutFactory.onHide();
                builder.noNetworkLayoutFactory.onHide();
                builder.noNetworkLayoutFactory.onShow();
                break;
            case LoadMode.SUCCESS:
                layout_load_error.setVisibility(View.GONE);
                layout_load_loading.setVisibility(View.GONE);
                layout_load_no_data.setVisibility(View.GONE);
                layout_load_no_network.setVisibility(View.GONE);
                if (mContentView != null)
                    mContentView.setVisibility(View.VISIBLE);
                builder.errorLayoutFactory.onHide();
                builder.loadingLayoutFactory.onHide();
                builder.noNetworkLayoutFactory.onHide();
                builder.noNetworkLayoutFactory.onHide();
                break;
        }
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState ss = new SavedState(superState);
        ss.state = mLoadMode;
        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        setLoadMode(ss.state);
    }

    private static class SavedState extends BaseSavedState {
        private int state;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            state = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeInt(state);
        }

        public static final Creator<SavedState> CREATOR = new Creator<LoadLayoutT.SavedState>() {
            public LoadLayoutT.SavedState createFromParcel(Parcel in) {
                return new LoadLayoutT.SavedState(in);
            }

            public LoadLayoutT.SavedState[] newArray(int size) {
                return new LoadLayoutT.SavedState[size];
            }
        };
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("zyh", "onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("zyh", "onLayout");
    }

    public static class Builder {

        private LoadLayoutFactory noDataLayoutFactory;
        private LoadLayoutFactory errorLayoutFactory;
        private LoadLayoutFactory loadingLayoutFactory;
        private LoadLayoutFactory noNetworkLayoutFactory;

        public Builder buildCreateNoDataLayout(LoadLayoutFactory factory) {
            this.noDataLayoutFactory = factory;
            return this;
        }

        public Builder buildCreateErrorLayout(LoadLayoutFactory factory) {
            this.errorLayoutFactory = factory;
            return this;
        }

        public Builder buildCreateLoadingLayout(LoadLayoutFactory factory) {
            this.loadingLayoutFactory = factory;
            return this;
        }

        public Builder buildCreateNoNetworkLayout(LoadLayoutFactory factory) {
            this.noNetworkLayoutFactory = factory;
            return this;
        }

        public Builder build() {
            checkNotNull();
//            setBuilder(this);
            return this;
        }

        private void checkNotNull() {
            if (noDataLayoutFactory == null) {
                noDataLayoutFactory = new LoadLayoutFactory.DefaultLoadLayoutFactory() {

                    @Override
                    public int createLayoutId() {
                        return R.layout.layout_load_no_data;
                    }
                };
            }
            if (errorLayoutFactory == null) {
                noDataLayoutFactory = new LoadLayoutFactory.DefaultLoadLayoutFactory() {

                    @Override
                    public int createLayoutId() {
                        return R.layout.layout_load_error;
                    }
                };
            }
            if (loadingLayoutFactory == null) {
                noDataLayoutFactory = new LoadLayoutFactory.DefaultLoadLayoutFactory() {

                    @Override
                    public int createLayoutId() {
                        return R.layout.layout_load_loading;
                    }
                };
            }
            if (noNetworkLayoutFactory == null) {
                noDataLayoutFactory = new LoadLayoutFactory.DefaultLoadLayoutFactory() {

                    @Override
                    public int createLayoutId() {
                        return R.layout.layout_load_error;
                    }
                };
            }
        }
    }
}
