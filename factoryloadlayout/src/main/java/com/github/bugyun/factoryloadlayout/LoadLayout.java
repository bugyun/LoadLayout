package com.github.bugyun.factoryloadlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by ruoyun on 2016/10/14.
 * 加载布局的组合控件
 */
public final class LoadLayout extends FrameLayout {

    private View mErrorView;//加载失败
    private View mLoadingView;//正在加载中
    private View mNoDataView;//没有数据
    private View mNoNetWorkView;//没有数据
    private View mContentView;//需要加载的界面
    private Builder builder;//
    private int mLoadMode = LoadMode.SUCCESS;//
    private boolean isInitialize = false;//初始化状态

    public LoadLayout(@NonNull Context context) {
        super(context);
        initView();
    }

    public LoadLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public LoadLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public LoadLayout(Context context, AttributeSet attrs, Builder builder) {
        super(context, attrs);
        this.builder = builder;
        initView();
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LoadLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private void initView() {
        if (isInitialize) {
            return;
        }
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        //1.加载布局
        mLoadingView = layoutInflater.inflate(builder.loadingLayoutState.onCreateLayoutId(), this, false);
        mErrorView = layoutInflater.inflate(builder.errorLayoutState.onCreateLayoutId(), this, false);
        mNoDataView = layoutInflater.inflate(builder.noDataLayoutState.onCreateLayoutId(), this, false);
        mNoNetWorkView = layoutInflater.inflate(builder.noNetworkLayoutState.onCreateLayoutId(), this, false);
        //2.设置view
        builder.loadingLayoutState.onCreateView(mLoadingView);
        builder.errorLayoutState.onCreateView(mErrorView);
        builder.noDataLayoutState.onCreateView(mNoDataView);
        builder.noNetworkLayoutState.onCreateView(mNoNetWorkView);
        //3.设置隐藏属性
        mLoadingView.setVisibility(mLoadMode == LoadMode.LOADING ? View.VISIBLE : View.GONE);
        mErrorView.setVisibility(mLoadMode == LoadMode.ERROR ? View.VISIBLE : View.GONE);
        mNoDataView.setVisibility(mLoadMode == LoadMode.NO_DATA ? View.VISIBLE : View.GONE);
        mNoNetWorkView.setVisibility(mLoadMode == LoadMode.NO_NETWORK ? View.VISIBLE : View.GONE);
        //4.添加view
        addView(mLoadingView);
        addView(mErrorView);
        addView(mNoDataView);
        addView(mNoNetWorkView);
        //5.初始化完成
        isInitialize = true;
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

    public int getLoadMode() {
        return mLoadMode;
    }

    public void setLoadMode(int mLoadMode) {
        if (!isInitialize) {//没有初始化的话，就返回
            throw new IllegalStateException("LoadLayout must be setBuilder()");
        }
        this.mLoadMode = mLoadMode;
        switch (mLoadMode) {
            case LoadMode.LOADING:
                mLoadingView.setVisibility(View.VISIBLE);
                mErrorView.setVisibility(View.GONE);
                mNoDataView.setVisibility(View.GONE);
                mNoNetWorkView.setVisibility(View.GONE);
                if (mContentView != null)
                    mContentView.setVisibility(View.GONE);
                builder.loadingLayoutState.onShow();
                builder.errorLayoutState.onHide();
                builder.noNetworkLayoutState.onHide();
                builder.noNetworkLayoutState.onHide();
                break;
            case LoadMode.ERROR:
                mLoadingView.setVisibility(View.GONE);
                mErrorView.setVisibility(View.VISIBLE);
                mNoDataView.setVisibility(View.GONE);
                mNoNetWorkView.setVisibility(View.GONE);
                if (mContentView != null)
                    mContentView.setVisibility(View.GONE);
                builder.loadingLayoutState.onHide();
                builder.errorLayoutState.onShow();
                builder.noNetworkLayoutState.onHide();
                builder.noNetworkLayoutState.onHide();
                break;
            case LoadMode.NO_DATA:
                mLoadingView.setVisibility(View.GONE);
                mErrorView.setVisibility(View.GONE);
                mNoDataView.setVisibility(View.VISIBLE);
                mNoNetWorkView.setVisibility(View.GONE);
                if (mContentView != null)
                    mContentView.setVisibility(View.GONE);
                builder.loadingLayoutState.onHide();
                builder.errorLayoutState.onHide();
                builder.noNetworkLayoutState.onShow();
                builder.noNetworkLayoutState.onHide();
                break;
            case LoadMode.NO_NETWORK:
                mLoadingView.setVisibility(View.GONE);
                mErrorView.setVisibility(View.GONE);
                mNoDataView.setVisibility(View.GONE);
                mNoNetWorkView.setVisibility(View.VISIBLE);
                if (mContentView != null)
                    mContentView.setVisibility(View.GONE);
                builder.loadingLayoutState.onHide();
                builder.errorLayoutState.onHide();
                builder.noNetworkLayoutState.onHide();
                builder.noNetworkLayoutState.onShow();
                break;
            case LoadMode.SUCCESS:
                mLoadingView.setVisibility(View.GONE);
                mErrorView.setVisibility(View.GONE);
                mNoDataView.setVisibility(View.GONE);
                mNoNetWorkView.setVisibility(View.GONE);
                if (mContentView != null)
                    mContentView.setVisibility(View.VISIBLE);
                builder.loadingLayoutState.onHide();
                builder.errorLayoutState.onHide();
                builder.noNetworkLayoutState.onHide();
                builder.noNetworkLayoutState.onHide();
                break;
        }
    }

    public static class Builder {

        private ILoadLayoutState noDataLayoutState;
        private ILoadLayoutState errorLayoutState;
        private ILoadLayoutState loadingLayoutState;
        private ILoadLayoutState noNetworkLayoutState;

        public Builder buildCreateNoDataLayout(ILoadLayoutState state) {
            this.noDataLayoutState = state;
            return this;
        }

        public Builder buildCreateErrorLayout(ILoadLayoutState state) {
            this.errorLayoutState = state;
            return this;
        }

        public Builder buildCreateLoadingLayout(ILoadLayoutState state) {
            this.loadingLayoutState = state;
            return this;
        }

        public Builder buildCreateNoNetworkLayout(ILoadLayoutState state) {
            this.noNetworkLayoutState = state;
            return this;
        }

        public Builder build() {
            checkNotNull();
            return this;
        }

        private void checkNotNull() {
            if (noDataLayoutState == null) {
                noDataLayoutState = new ILoadLayoutState.DefaultLoadLayoutState() {

                    @Override
                    public int onCreateLayoutId() {
                        return R.layout.factory_layout_load_default;
                    }

                    @Override
                    public void onCreateView(View view) {
                        TextView mTvText = view.findViewById(R.id.mTvText);
                        mTvText.setText("没有数据");
                    }
                };
            }
            if (errorLayoutState == null) {
                errorLayoutState = new ILoadLayoutState.DefaultLoadLayoutState() {

                    @Override
                    public int onCreateLayoutId() {
                        return R.layout.factory_layout_load_default;
                    }

                    @Override
                    public void onCreateView(View view) {
                        TextView mTvText = view.findViewById(R.id.mTvText);
                        mTvText.setText("发生错误，请重试");
                    }
                };
            }
            if (loadingLayoutState == null) {
                loadingLayoutState = new ILoadLayoutState.DefaultLoadLayoutState() {

                    @Override
                    public int onCreateLayoutId() {
                        return R.layout.factory_layout_load_default;
                    }

                    @Override
                    public void onCreateView(View view) {
                        TextView mTvText = view.findViewById(R.id.mTvText);
                        mTvText.setText("加载中");
                    }
                };
            }
            if (noNetworkLayoutState == null) {
                noNetworkLayoutState = new ILoadLayoutState.DefaultLoadLayoutState() {

                    @Override
                    public int onCreateLayoutId() {
                        return R.layout.factory_layout_load_default;
                    }

                    @Override
                    public void onCreateView(View view) {
                        TextView mTvText = view.findViewById(R.id.mTvText);
                        mTvText.setText("没有网络");
                    }
                };
            }
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

        public static final Creator<SavedState> CREATOR = new Creator<LoadLayout.SavedState>() {
            public LoadLayout.SavedState createFromParcel(Parcel in) {
                return new LoadLayout.SavedState(in);
            }

            public LoadLayout.SavedState[] newArray(int size) {
                return new LoadLayout.SavedState[size];
            }
        };
    }
}
