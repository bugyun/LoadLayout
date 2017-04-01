package com.github.bugyun.loadlayout;

/**
 * Created by ruoyun on 2016/10/14.
 */

public interface LoadMode {
    int NO_DATA = 0x10;//没有数据
    int ERROR = 0x15;//错误
    int LOADING = 0x20;//加载中
    int SUCCESS = 0x25;//成功
    //int NO_NETWORK = 0x30;//没有网络
}
