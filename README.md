# LoadLayout
android 加载布局的两种方式

- QuickLoadLayout
- SuperLoadLayout

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

## SuperLoadLayout

```groovy
implementation 'vip.ruoyun.widget:super-load-layout:1.0.0'
```

