package com.github.bugyun.loadlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
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
public class LoadLayout extends FrameLayout implements View.OnClickListener {

    private View layout_load_error;//加载失败
    private View layout_load_loading;//正在加载中
    private View layout_load_no_data;//没有数据
    private View mContentView;//需要加载的界面
    private OnReloadListener onReloadListener;

    private int loadEmptyLayoutId = R.layout.layout_load_no_data;
    private int loadErrorLayoutId = R.layout.layout_load_error;
    private int loadLoadingLayoutId = R.layout.layout_load_loading;
    //private int loadNoNetworkLayoutId = R.layout.layout_load_error;


    public void setOnReloadListener(OnReloadListener onReloadListener) {
        this.onReloadListener = onReloadListener;
    }

    private int mLoadMode = LoadMode.SUCCESS;

    public int getLoadMode() {
        return mLoadMode;
    }

    public void setLoadMode(int mLoadMode) {
        this.mLoadMode = mLoadMode;
        setLayoutLoadMode(mLoadMode);
    }

    public LoadLayout(Context context) {
        super(context);
        initView(context);
    }

    public LoadLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context, attrs);
        initView(context);
    }

    public LoadLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(context, attrs);
        initView(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LoadLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttributes(context, attrs);
        initView(context);
    }

    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.loadLayout, 0, 0);
        loadEmptyLayoutId = ta.getResourceId(R.styleable.loadLayout_loadEmptyLayout, R.layout.layout_load_no_data);
        loadErrorLayoutId = ta.getResourceId(R.styleable.loadLayout_loadErrorLayout, R.layout.layout_load_error);
        loadLoadingLayoutId = ta.getResourceId(R.styleable.loadLayout_loadLoadingLayout, R.layout.layout_load_loading);
        //loadNoNetworkLayoutId = ta.getResourceId(R.styleable.loadLayout_loadNoNetworkLayout, R.layout.layout_load_error);
        ta.recycle();
    }


    private void initView(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layout_load_error = layoutInflater.inflate(loadErrorLayoutId, this, false);
        layout_load_loading = layoutInflater.inflate(loadLoadingLayoutId, this, false);
        layout_load_no_data = layoutInflater.inflate(loadEmptyLayoutId, this, false);
        layout_load_error.setVisibility(mLoadMode == LoadMode.ERROR ? View.VISIBLE : View.GONE);
        layout_load_loading.setVisibility(mLoadMode == LoadMode.LOADING ? View.VISIBLE : View.GONE);
        layout_load_no_data.setVisibility(mLoadMode == LoadMode.NO_DATA ? View.VISIBLE : View.GONE);
        layout_load_error.setOnClickListener(this);
        layout_load_no_data.setOnClickListener(this);
        addView(layout_load_error);
        addView(layout_load_loading);
        addView(layout_load_no_data);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 3) {
            mContentView = getChildAt(3);
            mContentView.setVisibility(mLoadMode == LoadMode.SUCCESS ? View.VISIBLE : View.GONE);
        }
        if (getChildCount() > 4) {
            throw new IllegalStateException("LoadLayout can host only one direct child");
        }
    }

    private void setLayoutLoadMode(int mode) {
        switch (mode) {
            case LoadMode.ERROR:
                layout_load_error.setVisibility(View.VISIBLE);
                layout_load_loading.setVisibility(View.GONE);
                layout_load_no_data.setVisibility(View.GONE);
                if (mContentView != null)
                    mContentView.setVisibility(View.GONE);
                break;
            case LoadMode.LOADING:
                layout_load_error.setVisibility(View.GONE);
                layout_load_loading.setVisibility(View.VISIBLE);
                layout_load_no_data.setVisibility(View.GONE);
                if (mContentView != null)
                    mContentView.setVisibility(View.GONE);
                break;
            case LoadMode.NO_DATA:
                layout_load_error.setVisibility(View.GONE);
                layout_load_loading.setVisibility(View.GONE);
                layout_load_no_data.setVisibility(View.VISIBLE);
                if (mContentView != null)
                    mContentView.setVisibility(View.GONE);
                break;
            case LoadMode.SUCCESS:
                layout_load_error.setVisibility(View.GONE);
                layout_load_loading.setVisibility(View.GONE);
                layout_load_no_data.setVisibility(View.GONE);
                if (mContentView != null)
                    mContentView.setVisibility(View.VISIBLE);
                break;
        }
//        invalidate();
    }

    @Override
    public void onClick(View v) {
        if (onReloadListener != null) {
            onReloadListener.onReload();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("zyh", "onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.e("zyh", "onLayout");
    }

    public interface OnReloadListener {
        void onReload();
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
        setLayoutLoadMode(ss.state);
    }

    private static class SavedState extends BaseSavedState {
        int state;

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

        public static final Parcelable.Creator<SavedState> CREATOR
                = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }


}
