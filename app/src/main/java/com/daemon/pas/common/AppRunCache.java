package com.daemon.pas.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daemon on 2015/12/21.
 */
public class AppRunCache {
    public static int screen_width=0;
    public static int screen_height=0;

    public static String Music_Init_Data="music_init_data";

    //缓存 音乐数据 在进入音乐界面之后 流畅运行 默认缓存 后面两首的图片信息 因为图片是链接请求
    public static List<String> musicList=new ArrayList<String>();


}
