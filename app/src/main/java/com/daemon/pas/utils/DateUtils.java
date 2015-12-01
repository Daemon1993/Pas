package com.daemon.pas.utils;

import java.text.SimpleDateFormat;

/**
 * Created by h2h on 2015/8/7.
 */
public class DateUtils {
    public static String getFormatDate(Long time){

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String t=format.format(time);

//        Date date=new Date();       String newDate=format.format(date);
//        LogUtils.e(newDate);

        return t;
    }

    public static String getFormatDate1(Long time){

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        String t=format.format(time);

//        Date date=new Date();
//        String newDate=format.format(date);
//        LogUtils.e(newDate);

        return t;
    }
}
