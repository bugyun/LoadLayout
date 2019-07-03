package vip.ruoyun.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by ruoyun on 2019-07-03.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction:
 */
public class SuperLoadLayout extends FrameLayout {

    public interface LoadMode {
        int NO_DATA = 0x10;//没有数据
        int ERROR = 0x15;//错误
        int LOADING = 0x20;//加载中
        int SUCCESS = 0x25;//成功
        int NO_NETWORK = 0x30;//没有网络
    }

    private View mErrorView;//加载失败
    private View mLoadingView;//正在加载中
    private View mNoDataView;//没有数据
    private View mNoNetWorkView;//没有网络
    private View mContentView;//需要加载的界面
    private Builder builder;//
    private int mLoadMode = LoadMode.SUCCESS;//
    private boolean isInitialize = false;//初始化状态

    public SuperLoadLayout(Context context) {
        super(context);
    }

    public SuperLoadLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SuperLoadLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SuperLoadLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setBuilder(Builder builder) {
        this.builder = builder;
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
        if (getChildCount() == 1) {
            mContentView = getChildAt(0);
            mContentView.setVisibility(mLoadMode == LoadMode.SUCCESS ? View.VISIBLE : View.GONE);
        }
        if (getChildCount() > 2) {
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

        private ISuperLoadLayoutState noDataLayoutState;
        private ISuperLoadLayoutState errorLayoutState;
        private ISuperLoadLayoutState loadingLayoutState;
        private ISuperLoadLayoutState noNetworkLayoutState;

        public Builder buildCreateNoDataLayout(ISuperLoadLayoutState state) {
            this.noDataLayoutState = state;
            return this;
        }

        public Builder buildCreateErrorLayout(ISuperLoadLayoutState state) {
            this.errorLayoutState = state;
            return this;
        }

        public Builder buildCreateLoadingLayout(ISuperLoadLayoutState state) {
            this.loadingLayoutState = state;
            return this;
        }

        public Builder buildCreateNoNetworkLayout(ISuperLoadLayoutState state) {
            this.noNetworkLayoutState = state;
            return this;
        }

        public Builder build() {
            checkNotNull();
            return this;
        }

        private void checkNotNull() {
            if (noDataLayoutState == null) {
                noDataLayoutState = new ISuperLoadLayoutState.DefaultLoadLayoutState() {

                    @Override
                    public int onCreateLayoutId() {
                        return R.layout.super_layout_load_default;
                    }

                    @Override
                    public void onCreateView(View view) {
                        TextView mTvText = view.findViewById(R.id.mTvText);
                        mTvText.setText("没有数据");
                    }
                };
            }
            if (errorLayoutState == null) {
                errorLayoutState = new ISuperLoadLayoutState.DefaultLoadLayoutState() {

                    @Override
                    public int onCreateLayoutId() {
                        return R.layout.super_layout_load_default;
                    }

                    @Override
                    public void onCreateView(View view) {
                        TextView mTvText = view.findViewById(R.id.mTvText);
                        mTvText.setText("发生错误，请重试");
                    }
                };
            }
            if (loadingLayoutState == null) {
                loadingLayoutState = new ISuperLoadLayoutState.DefaultLoadLayoutState() {

                    @Override
                    public int onCreateLayoutId() {
                        return R.layout.super_layout_load_default;
                    }

                    @Override
                    public void onCreateView(View view) {
                        TextView mTvText = view.findViewById(R.id.mTvText);
                        mTvText.setText("加载中");
                    }
                };
            }
            if (noNetworkLayoutState == null) {
                noNetworkLayoutState = new ISuperLoadLayoutState.DefaultLoadLayoutState() {

                    @Override
                    public int onCreateLayoutId() {
                        return R.layout.super_layout_load_default;
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

        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public SuperLoadLayout.SavedState createFromParcel(Parcel in) {
                return new SuperLoadLayout.SavedState(in);
            }

            public SuperLoadLayout.SavedState[] newArray(int size) {
                return new SuperLoadLayout.SavedState[size];
            }
        };
    }


}
