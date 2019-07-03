# LoadLayout
android 加载布局的两种方式

- QuickLoadLayout
- SuperLoadLayout

完美支持 androidx 和 Android 库。

---
## QuickLoadLayout

使用方法,在使用的子 build.gradle 中添加配置
```groovy
implementation 'vip.ruoyun.widget:quick-load-layout:1.0.0'
```

### xml设置
- loadEmptyLayout : 没有数据显示的界面，对应状态 LoadMode.NO_DATA
- loadErrorLayout : 网络错误显示的界面，对应状态 LoadMode.ERROR
- loadLoadingLayout : 加载中的界面，对应状态 LoadMode.LOADING
- loadNoNetworkLayout : 没有网络显示的界面，对应状态 LoadMode.NO_NETWORK
- 成功的界面 : LoadMode.SUCCESS

```xml
<vip.ruoyun.widget.quick.QuickLoadLayout
    android:id="@+id/mQuickLoadLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:loadEmptyLayout="@layout/quick_layout_load_no_data"
    app:loadErrorLayout="@layout/quick_layout_load_error"
    app:loadLoadingLayout="@layout/quick_layout_load_loading"
    app:loadNoNetworkLayout="@layout/quick_layout_load_no_network">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="我是内容" />
</vip.ruoyun.widget.quick.QuickLoadLayout>
```

### 代码设置

可以添加点击事件，重新加载，NO_DATA，ERROR，NO_NETWORK 的布局会响应此事件
```java
mQuickLoadLayout = findViewById(R.id.mQuickLoadLayout);
mQuickLoadLayout.setOnReloadListener(new QuickLoadLayout.OnReloadListener() {
    @Override
    public void onReload() {
        mQuickLoadLayout.setLoadMode(QuickLoadLayout.LoadMode.LOADING);
    }
});
```



---

## SuperLoadLayout

使用方法,在使用的子 build.gradle 中添加配置
```groovy
implementation 'vip.ruoyun.widget:super-load-layout:1.0.0'
```

### xml
```xml
<vip.ruoyun.widget.superlayout.SuperLoadLayout
    android:id="@+id/mSuperLoadLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="我是成功界面" />

</vip.ruoyun.widget.superlayout.SuperLoadLayout>
```

### 创建 state ,各种状态创建对应的类
```java
public class SuperCreateLoadBuildHelper {

    public static SuperLoadLayout.Builder create(IFetchData iFetchData) {
        return new SuperLoadLayout.Builder()
                .buildCreateErrorLayout(new ErrorILoadLayoutState(iFetchData))//网络错误
                .buildCreateLoadingLayout(new LoadingLayoutState(iFetchData))//加载中
                .buildCreateNoDataLayout(new NoDataLayoutState(iFetchData))//没有数据
                .buildCreateNoNetworkLayout(new NoNetworkLayoutState(iFetchData))//没有网络
                .build();
    }
}
```

创建对应的状态，需要实现 ISuperLoadLayoutState 接口，

```java
public class LoadingLayoutState implements ISuperLoadLayoutState {

    //或者通过接口来实现
    private IFetchData iFetchData;

    public LoadingLayoutState(IFetchData iFetchData) {
        this.iFetchData = iFetchData;
    }

    @Override
    public int onCreateLayoutId() {//对应的布局
        return R.layout.layout_load_loading;
    }

    @Override
    public void onCreateView(View view) {
        //在这里进行一些 findviewbyid
        //setOnClick 等操作
    }

    @Override
    public void onShow() {
        //当界面要展示的时候，会回调此方法，比如开始动画
    }

    @Override
    public void onHide() {
        //当界面要展示的时候，会回调此方法，比如结束动画
    }
}

```

把创建好的 build 传入到 SuperLoadLayout 类中。
```java
mSuperLoadLayout.setBuilder(SuperCreateLoadBuildHelper.create(this));
```
