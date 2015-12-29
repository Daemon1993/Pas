package com.daemon.pas.utils;

/**
 * Created by Daemon1993 on 15/12/26.
 */
public class TimeUtils {

    /**
     * int 转换 分:秒
     * @param size
     * @return
     */
    public static String transformationMS(int size){
        size=size/1000;
        int minute=size/60;
        int second=size%60;

        StringBuilder sb=new StringBuilder();
        if(minute<10){
            sb.append("0"+minute+":");
        }else{
            sb.append(minute+":");
        }
        if(second<10){
            sb.append("0"+second);
        }else{
            sb.append(second);
        }
        return sb.toString();
    }
}
