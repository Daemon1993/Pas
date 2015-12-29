package com.daemon.pas;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.daemon.framework.dutils.DensityUtil;
import com.daemon.pas.common.AppRunCache;
import com.daemon.pas.utils.ImageLoaderSetting;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Daemon on 2015/11/25.
 */
public class MyApplication extends MultiDexApplication {

    private static Context mInstance;   //小心使用 不要持有其余的引用 不然会内存泄露 只用于 调用一些方法 获取这个对象


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


        refWatcher = LeakCanary.install(this);


        AppRunCache.screen_width = DensityUtil.getScreenW(this);
        AppRunCache.screen_height =DensityUtil.getScreenH(this);


        ImageLoaderSetting.initImageLoader(getApplicationContext());


    }



    protected RefWatcher installLeakCanary() {
        return RefWatcher.DISABLED;
    }

}
