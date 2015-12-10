package com.daemon.pas;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.util.DisplayMetrics;

import com.socks.library.KLog;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Daemon on 2015/11/25.
 */
public class MyApplication extends MultiDexApplication {

    private static Context mInstance;   //小心使用 不要持有其余的引用 不然会内存泄露 只用于 调用一些方法 获取这个对象
    public static int screen_width;
    public static int screen_height;

    public static RefWatcher getRefWatcher(Context context) {
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

    public static Context getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        mInstance = getApplicationContext();


        refWatcher = installLeakCanary();

        KLog.init(true);

        getDimens();

    }

    private void getDimens() {

        DisplayMetrics dm =getResources().getDisplayMetrics();

        screen_width = dm.widthPixels;
        screen_height = dm.heightPixels;

        KLog.e("screen with height "+screen_width +"  "+screen_height);

    }

    protected RefWatcher installLeakCanary() {
        return RefWatcher.DISABLED;
    }

}
