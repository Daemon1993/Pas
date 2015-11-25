package com.daemon.pas;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.orhanobut.logger.Logger;

/**
 * Created by Daemon on 2015/11/25.
 */
public class MyApplication extends MultiDexApplication {

    private static Context mInstance;   //小心使用 不要持有其余的引用 不然会内存泄露 只用于 调用一些方法 获取这个对象

    public static Context getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=getApplicationContext();
        Logger.init("Daemon");

    }
}
