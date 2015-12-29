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
    public static final String Base_Url_Pic_Search="http://so.picasso.adesk.com/v1/search/wallpaper/resource/";
    public static final String Pic_Type="v1/wallpaper/category";

    public static final String Pic_Type_Data=Base_Url_Pic+Pic_Type;

    public static final String Pic_Last="/wallpaper?order=new&adult=false&limit=30&first=0&skip=";


    //天天动听的APi
    public static final String Muisc_Recommend="http://api.dongting.com/" +
            "favorite/song/plaza?from=android&api_version=1.0&agent=none&user_id=0&language=zh&random=";



    //public static final String Music_Play="http://search.dongting.com/artwork/search?singerid=55480&song_id=25011637";
    public static final String Music_Play="http://search.dongting.com/artwork/search?singerid=";


    //获取歌词前一步 拿到id
    public static final String Music_Get_Lrc1="http://search.dongting.com" +
            "/lyric/search?sign=457d14c89fcb9b920bbdc2698f3603a3&" +
            "utdid=Vgny1yVrWZsDAJwcljC18b%2Bg";

    public static final String getMusic_Get_Lrc2="http://search.dongting.com/lyric/content?sign=ce73d2c0ebc4f82f87d077aa6c4f87cc&" +
            "utdid=Vgny1yVrWZsDAJwcljC18b%2Bg&";




}
