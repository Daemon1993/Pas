package com.daemon.pas;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.daemon.framework.dutils.DLog;
import com.daemon.framework.dutils.DensityUtil;
import com.daemon.pas.common.AppRunCache;
import com.daemon.pas.utils.ImageLoaderSetting;

/**
 * Created by Daemon on 2015/11/25.
 */
public class MyApplication extends MultiDexApplication {

    private static Context mInstance;

    public static Context getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        mInstance = getApplicationContext();


        AppRunCache.screen_width = DensityUtil.getScreenW(this);
        AppRunCache.screen_height = DensityUtil.getScreenH(this);


        ImageLoaderSetting.initImageLoader(getApplicationContext());

        DLog.ENABLE = false;

    }

}
