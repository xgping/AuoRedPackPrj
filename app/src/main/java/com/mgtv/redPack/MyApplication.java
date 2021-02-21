package com.mgtv.redPack;

import android.app.Application;
import android.preference.PreferenceManager;

import com.mgtv.redPack.utils.ContextProvider;

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
        PreferenceManager.setDefaultValues(this, R.xml.general_preferences, false);
    }
}
