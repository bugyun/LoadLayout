# LoadLayout
android 加载布局

## 使用方法

    public class MainActivity extends AppCompatActivity {
        private LoadLayout mLoadLayout;
    
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            mLoadLayout = (LoadLayout) findViewById(R.id.mLoadLayout);
            mLoadLayout.setLoadMode(LoadMode.LOADING);//加载状态
            mLoadLayout.setLoadMode(LoadMode.ERROR);//错误状态,没有网络
            mLoadLayout.setLoadMode(LoadMode.NO_DATA);//没有数据状态
            mLoadLayout.setLoadMode(LoadMode.SUCCESS);//成功状态
    
            mLoadLayout.setOnReloadListener(new LoadLayout.OnReloadListener() {
                @Override
                public void onReload() {
    
                }
            });
        }
    }
    
## 布局使用方法
    <com.github.bugyun.loadlayout.LoadLayout
            android:id="@+id/mLoadLayout"
            android:layout_width="368dp"
            android:layout_height="495dp"
            app:loadEmptyLayout="@layout/layout_load_no_data"
            app:loadErrorLayout="@layout/layout_load_error"
            app:loadLoadingLayout="@layout/layout_load_loading">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Hello World!"/>

    </com.github.bugyun.loadlayout.LoadLayout>
    

## 引用
    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
	dependencies {
    	  compile 'com.github.bugyun:LoadLayout:1.0.1'
    }