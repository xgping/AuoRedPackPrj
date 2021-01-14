package com.mgtv.auoredpackprj;

import android.app.Application;

import com.mgtv.auoredpackprj.utils.ContextProvider;

/**
 * 文件描述:
 * 作   者: 向庚平
 * 邮   箱: gengping@mgtv.com
 * 创建时间: 2021/1/12.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ContextProvider.init(this);
    }
}
