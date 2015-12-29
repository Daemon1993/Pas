package com.daemon.pas.common;

import android.os.Environment;

/**
 * Created by Daemon on 2015/11/26.
 */
public class BaseRequest {

    /**
     * 程序缓存的目录
     */
    public static final String App_Dir= Environment.getExternalStorageDirectory()+"/Pas/";


    public static final String APP="Daemon";
    public static final String APIKEY="apikey";
    public static final String APIKEY_VALUE="ca7a0c901d0935d450f62e256c022faa";
    public static final String NewsData="news_item_data";



    public static final String SUCCESS="success";


    public static final String Error_Msg="爆炸 接口爆炸了";



}
