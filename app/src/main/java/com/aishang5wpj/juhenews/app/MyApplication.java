package com.aishang5wpj.juhenews.app;

import android.app.Application;

import com.aishang5wpj.baiduloc.LocationHelper;

import java.io.File;

public class MyApplication extends Application {

    /**
     * 以文件分隔符结尾"/"
     */
    public static String ROOT_PATH;
    public static String CITY;

    @Override
    public void onCreate() {
        super.onCreate();

        ROOT_PATH = getCacheDir().getPath();
        if (!ROOT_PATH.endsWith(File.separator)) {
            ROOT_PATH += File.separator;
        }

        LocationHelper.getHelper().init(this);
    }
}
