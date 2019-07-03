package vip.ruoyun.widget.quick;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by ruoyun on 2019-07-03.
 * Author:若云
 * Mail:zyhdvlp@gmail.com
 * Depiction: 快速帮助类
 */
public class QuickLoadLayout extends FrameLayout implements View.OnClickListener {

    public interface LoadMode {
        int NO_DATA = 0x10;//没有数据
        int ERROR = 0x15;//错误
        int LOADING = 0x20;//加载中
        int SUCCESS = 0x25;//成功
        int NO_NETWORK = 0x30;//没有网络
    }

    private View layout_load_error;//加载失败
    private View layout_load_loading;//正在加载中
    private View layout_load_no_data;//没有数据
    private View layout_load_no_network;//没有网络
    private View mContentView;//需要加载的界面
    private OnReloadListener onReloadListener;

    private int loadEmptyLayoutId;
    private int loadErrorLayoutId;
    private int loadLoadingLayoutId;
    private int loadNoNetworkLayoutId;


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

    public QuickLoadLayout(Context context) {
        super(context);
        initView(context);
    }

    public QuickLoadLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context, attrs);
        initView(context);
    }

    public QuickLoadLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
        initAttributes(context, attrs);
        initView(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public QuickLoadLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initAttributes(context, attrs);
        initView(context);
    }

    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.QuickLoadLayout, 0, 0);
        loadEmptyLayoutId = ta.getResourceId(R.styleable.QuickLoadLayout_loadEmptyLayout, R.layout.quick_layout_load_no_data);
        loadErrorLayoutId = ta.getResourceId(R.styleable.QuickLoadLayout_loadErrorLayout, R.layout.quick_layout_load_error);
        loadLoadingLayoutId = ta.getResourceId(R.styleable.QuickLoadLayout_loadLoadingLayout, R.layout.quick_layout_load_loading);
        loadNoNetworkLayoutId = ta.getResourceId(R.styleable.QuickLoadLayout_loadNoNetworkLayout, R.layout.quick_layout_load_error);
        ta.recycle();
    }


    private void initView(Context context) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        layout_load_error = layoutInflater.inflate(loadErrorLayoutId, this, false);
        layout_load_loading = layoutInflater.inflate(loadLoadingLayoutId, this, false);
        layout_load_no_data = layoutInflater.inflate(loadEmptyLayoutId, this, false);
        layout_load_no_network = layoutInflater.inflate(loadNoNetworkLayoutId, this, false);
        layout_load_error.setVisibility(mLoadMode == LoadMode.ERROR ? View.VISIBLE : View.GONE);
        layout_load_loading.setVisibility(mLoadMode == LoadMode.LOADING ? View.VISIBLE : View.GONE);
        layout_load_no_data.setVisibility(mLoadMode == LoadMode.NO_DATA ? View.VISIBLE : View.GONE);
        layout_load_no_network.setVisibility(mLoadMode == LoadMode.NO_NETWORK ? View.VISIBLE : View.GONE);
        layout_load_error.setOnClickListener(this);
        layout_load_no_data.setOnClickListener(this);
        layout_load_no_network.setOnClickListener(this);
        addView(layout_load_error);
        addView(layout_load_loading);
        addView(layout_load_no_data);
        addView(layout_load_no_network);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 4) {
            mContentView = getChildAt(4);
            mContentView.setVisibility(mLoadMode == LoadMode.SUCCESS ? View.VISIBLE : View.GONE);
        }
        if (getChildCount() > 5) {
            throw new IllegalStateException("QuickLoadLayout can host only one direct child");
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
                break;
            case LoadMode.LOADING:
                layout_load_error.setVisibility(View.GONE);
                layout_load_loading.setVisibility(View.VISIBLE);
                layout_load_no_data.setVisibility(View.GONE);
                layout_load_no_network.setVisibility(View.GONE);
                if (mContentView != null)
                    mContentView.setVisibility(View.GONE);
                break;
            case LoadMode.NO_DATA:
                layout_load_error.setVisibility(View.GONE);
                layout_load_loading.setVisibility(View.GONE);
                layout_load_no_data.setVisibility(View.VISIBLE);
                layout_load_no_network.setVisibility(View.GONE);
                if (mContentView != null)
                    mContentView.setVisibility(View.GONE);
                break;
            case LoadMode.SUCCESS:
                layout_load_error.setVisibility(View.GONE);
                layout_load_loading.setVisibility(View.GONE);
                layout_load_no_data.setVisibility(View.GONE);
                layout_load_no_network.setVisibility(View.GONE);
                if (mContentView != null)
                    mContentView.setVisibility(View.VISIBLE);
                break;
            case LoadMode.NO_NETWORK:
                layout_load_error.setVisibility(View.GONE);
                layout_load_loading.setVisibility(View.GONE);
                layout_load_no_data.setVisibility(View.GONE);
                layout_load_no_network.setVisibility(View.VISIBLE);
                if (mContentView != null)
                    mContentView.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (onReloadListener != null) {
            onReloadListener.onReload();
        }
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

        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }
}
