/**
 * Copyright 2012 LeTV Technology Co. Ltd., Inc. All rights reserved.
 *
 * @Author : qingxia
 * @Description :
 */

package com.mgtv.redPack.utils;

import android.app.Application;
import android.content.Context;

/**
 * description: This class provide a global application context.
 * <p>
 * author: Created by xianggengping on 2017/10/18.
 * <p>
 * email: gengping@mgtv.com
 */
public final class ContextProvider {
    private static Application sApplication;
    private static Context sContext = null;

    public static Application getApplication() {
        return sApplication;
    }

    /**
     * This function should be invoked in Application while the
     * application is been
     * created.
     * @param application
     */
    public static void init(Application application) {
        if (application == null) {
            throw new NullPointerException("Can not use null initialized application context");
        }
        sContext = application.getApplicationContext();
        sApplication = application;
    }

    /**
     * Get application context.
     * @return
     */
    public static Context getApplicationContext() {
        if (sContext == null) {
            throw new NullPointerException("Global application uninitialized");
        }
        return sContext;
    }

    private ContextProvider() {
    }
}
