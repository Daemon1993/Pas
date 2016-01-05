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
    public static final String NewsData="news_item_data";



    public static final String SUCCESS="success";


    public static final String Error_Msg="爆炸 接口爆炸了";


    //优酷视频
    public static final String CLIENT_ID="client_id";
    public static final String CLIENT_SECRET="client_secret";
    public static final String GRANT_TYPE="grant_type";
    public static final String RESPONSE_TYPE="response_type";
    public static final String REFRESH_TOKEN="refresh_token";
    public static final String username="username";
    public static final String password="password";

    public static final String CLIENT_ID_VALUE="f5f33fe9dbbda47d";
    public static final String CLIENT_SECRET_VALUE="0d55c27756f1cb005482a1d0cdd2409c";


}
