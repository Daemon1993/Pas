package com.daemon.framework.dutils;

import android.util.Log;

/**
 * Created by Daemon on 2015/12/28.
 */
public class DLog {
    public static final String TAG="Daemon- ";
    public static void  e(String str){
        Log.e(TAG,str);
    }
}
