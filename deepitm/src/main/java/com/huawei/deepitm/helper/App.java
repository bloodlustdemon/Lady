package com.huawei.deepitm.helper;

import android.app.Application;
import android.content.Context;

/**
 * AUTHOR Paul
 * DATE 2018/3/24
 */
public class App extends Application {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }

    public static Context getContext() {
        return instance;
    }
}
