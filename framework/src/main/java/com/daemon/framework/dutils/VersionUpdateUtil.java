package com.daemon.framework.dutils;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by h2h on 2015/8/18.
 */
public class VersionUpdateUtil {

    public static int getVerCode(Context context) {
        int verCode = -1;
        try {
            verCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("获取versionCode失败");
        }
        return verCode;
    }

    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("获取versionName失败");
        }
        return verName;
    }
}
