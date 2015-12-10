package com.daemon.pas.common;

/**
 * Created by Daemon on 2015/11/26.
 */
public class API {


    //天天快报的新闻 抓取到的url
    public static final String Get_NewsType="http://r.cnews.qq.com/getSubChannels?apptype=android";

    public static final String Get_Nes_List="http://r.cnews.qq.com/getSubNewsChlidInterest?qn-rid=507545094&store=473" +
            "&qn-sig=d7183ca7c0c1f881b8d75f5d86cd7c6e&appver=18_areading_1.5.2&apptype=android&devid=sim_1448588673220621663";

    public static final String Get_Nes_KB="http://r.cnews.qq.com/getSubNewsInterest?qn-rid=507545094&store=473" +
            "&qn-sig=d7183ca7c0c1f881b8d75f5d86cd7c6e&appver=18_areading_1.5.2&apptype=android&devid=sim_1448588673220621663";


    //图片  -- 安卓壁纸
    public static final String Base_Url_Pic="http://service.picasso.adesk.com/";
    public static final String Pic_Type="v1/wallpaper/category";
    public static final String Pic_Search="search/wallpaper/resource/123?adult=false&first=1&limit=30&channel=360";

}
