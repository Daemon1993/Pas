package com.daemon.pas.model;

import com.daemon.mvp.model.IModel;

import java.util.List;

/**
 * Created by Daemon on 2015/11/26.
 */
public class NewsTypeData extends IModel{


    /**
     * ret : 0
     * version : 4.0
     * channellist : [{"chlid":"daily_timeline","chlname":"快报","recommend":0,"order":0,"interfaceType":"timeline"},{"chlid":"kb_news_bagua","chlname":"娱乐","recommend":0,"order":0,"group":"推荐"},{"chlid":"kb_news_qipa","chlname":"社会","recommend":0,"order":0,"group":"推荐"},{"chlid":"kb_photo_news","chlname":"图片","recommend":0,"order":0,"rendtype":"photo","group":"推荐"},{"chlid":"kb_video_news","chlname":"短视频","recommend":0,"order":0,"rendtype":"video","group":"推荐"},{"chlid":"kb_news_tech","chlname":"互联网","recommend":0,"order":0,"group":"推荐"},{"chlid":"kb_news_finance","chlname":"财经","recommend":0,"order":0,"group":"推荐"},{"chlid":"kb_news_dailyhot","chlname":"热评","recommend":0,"order":0,"rendtype":"dailyhot","interfaceType":"dailyhot","group":"推荐"},{"chlid":"location","chlname":"本地","recommend":0,"order":0,"rendtype":"location","interfaceType":"location","group":"推荐"},{"chlid":"kb_news_world","chlname":"国际","recommend":0,"order":0,"group":"商务精英"},{"chlid":"kb_news_hardcore","chlname":"重口味","recommend":0,"order":0,"group":"流行娱乐"},{"chlid":"kb_news_movie","chlname":"电影","recommend":0,"order":0,"group":"文艺青年"},{"chlid":"kb_news_gaojidi","chlname":"数码控","recommend":0,"order":0,"group":"数码宅腐"},{"chlid":"kb_news_wealth","chlname":"理财","recommend":0,"order":0,"group":"品质生活"},{"chlid":"kb_photo_gif","chlname":"GIF","recommend":1,"order":0,"rendtype":"gif","group":"推荐"},{"chlid":"kb_news_sports","chlname":"体育","recommend":1,"order":0,"group":"推荐"},{"chlid":"kb_news_mil","chlname":"军事","recommend":1,"order":0,"group":"推荐"},{"chlid":"kb_news_history","chlname":"历史","recommend":1,"order":0,"group":"推荐"},{"chlid":"kb_news_nba","chlname":"NBA","recommend":1,"order":0,"group":"流行娱乐"},{"chlid":"kb_news_car","chlname":"汽车","recommend":1,"order":0,"group":"商务精英"},{"chlid":"kb_news_chaobao","chlname":"时尚","recommend":1,"order":0,"group":"流行娱乐"},{"chlid":"kb_news_laugh","chlname":"搞笑","recommend":1,"order":0,"group":"流行娱乐"},{"chlid":"kb_news_pet","chlname":"萌宠","recommend":1,"order":0,"group":"流行娱乐"},{"chlid":"kb_news_science","chlname":"涨知识","recommend":1,"order":0,"group":"文艺青年"},{"chlid":"kb_news_baby","chlname":"育儿","recommend":0,"order":0,"group":"品质生活"},{"chlid":"kb_news_astro","chlname":"星座","recommend":1,"order":0,"group":"流行娱乐"},{"chlid":"kb_news_sex","chlname":"情感","recommend":1,"order":0,"group":"推荐"},{"chlid":"kb_news_beauty","chlname":"美女","recommend":1,"order":0,"rendtype":"photo","group":"推荐"},{"chlid":"kb_news_share","chlname":"股票","recommend":0,"order":0,"group":"商务精英"},{"chlid":"kb_news_augury","chlname":"占卜","recommend":0,"order":0,"group":"流行娱乐"},{"chlid":"kb_news_young","chlname":"男神","recommend":0,"order":0,"rendtype":"photo","group":"流行娱乐"},{"chlid":"kb_news_funpic","chlname":"趣图","recommend":0,"order":0,"group":"流行娱乐"},{"chlid":"kb_news_exo","chlname":"EXO","recommend":0,"order":0,"group":"流行娱乐"},{"chlid":"kb_news_koreaent","chlname":"韩娱","recommend":0,"order":0,"group":"流行娱乐"},{"chlid":"kb_news_photography","chlname":"摄影","recommend":0,"order":0,"group":"流行娱乐"},{"chlid":"kb_news_lottery","chlname":"彩票","recommend":0,"order":0,"group":"流行娱乐"},{"chlid":"kb_news_cate","chlname":"美食咖","recommend":0,"order":0,"group":"文艺青年"},{"chlid":"kb_news_julebu","chlname":"电视剧","recommend":0,"order":0,"group":"文艺青年"},{"chlid":"kb_news_travel","chlname":"旅游","recommend":0,"order":0,"group":"文艺青年"},{"chlid":"kb_news_artsy","chlname":"文艺范","recommend":0,"order":0,"group":"文艺青年"},{"chlid":"kb_news_idea","chlname":"创意","recommend":0,"order":0,"group":"文艺青年"},{"chlid":"kb_news_usdrama","chlname":"美剧","recommend":0,"order":0,"group":"文艺青年"},{"chlid":"kb_news_lol","chlname":"LOL","recommend":0,"order":0,"group":"数码宅腐"},{"chlid":"kb_news_erciyuan","chlname":"二次元","recommend":0,"order":0,"group":"数码宅腐"},{"chlid":"kb_news_space","chlname":"太空","recommend":0,"order":0,"group":"数码宅腐"},{"chlid":"kb_news_game","chlname":"游戏","recommend":0,"order":0,"group":"数码宅腐"},{"chlid":"kb_news_iphone","chlname":"iPhone","recommend":0,"order":0,"group":"数码宅腐"},{"chlid":"kb_news_esport","chlname":"电竞","recommend":0,"order":0,"group":"数码宅腐"},{"chlid":"kb_news_life","chlname":"民生","recommend":0,"order":0,"group":"数码宅腐"},{"chlid":"kb_news_cutebaby","chlname":"萌宝","recommend":0,"order":0,"group":"品质生活"},{"chlid":"kb_news_health","chlname":"养生","recommend":0,"order":0,"group":"品质生活"},{"chlid":"kb_news_outfit","chlname":"穿搭","recommend":0,"order":0,"group":"品质生活"},{"chlid":"kb_news_furnishing","chlname":"家居","recommend":0,"order":0,"group":"品质生活"},{"chlid":"kb_news_workout","chlname":"健身","recommend":0,"order":0,"group":"品质生活"},{"chlid":"kb_news_soup","chlname":"鸡汤","recommend":0,"order":0,"group":"品质生活"},{"chlid":"kb_news_run","chlname":"跑步","recommend":0,"order":0,"group":"品质生活"},{"chlid":"kb_news_fishing","chlname":"钓鱼","recommend":0,"order":0,"group":"品质生活"},{"chlid":"kb_news_buddism","chlname":"佛学","recommend":0,"order":0,"group":"品质生活"},{"chlid":"kb_news_diet","chlname":"减肥","recommend":0,"order":0,"group":"品质生活"},{"chlid":"kb_news_football","chlname":"中超","recommend":0,"order":0,"group":"品质生活"},{"chlid":"kb_news_tennis","chlname":"网球","recommend":0,"order":0,"group":"品质生活"},{"chlid":"kb_news_tea","chlname":"茶道","recommend":0,"order":0,"group":"品质生活"},{"chlid":"kb_news_yoga","chlname":"瑜伽","recommend":0,"order":0,"group":"品质生活"}]
     */

    private int ret;
    private String version;
    /**
     * chlid : daily_timeline
     * chlname : 快报
     * recommend : 0
     * order : 0
     * interfaceType : timeline
     */

    private List<ChannellistEntity> channellist;

    public void setRet(int ret) {
        this.ret = ret;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setChannellist(List<ChannellistEntity> channellist) {
        this.channellist = channellist;
    }

    public int getRet() {
        return ret;
    }

    public String getVersion() {
        return version;
    }

    public List<ChannellistEntity> getChannellist() {
        return channellist;
    }

    public static class ChannellistEntity {
        private String chlid;
        private String chlname;
        private int recommend;
        private int order;
        private String interfaceType;

        public void setChlid(String chlid) {
            this.chlid = chlid;
        }

        public void setChlname(String chlname) {
            this.chlname = chlname;
        }

        public void setRecommend(int recommend) {
            this.recommend = recommend;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public void setInterfaceType(String interfaceType) {
            this.interfaceType = interfaceType;
        }

        public String getChlid() {
            return chlid;
        }

        public String getChlname() {
            return chlname;
        }

        public int getRecommend() {
            return recommend;
        }

        public int getOrder() {
            return order;
        }

        public String getInterfaceType() {
            return interfaceType;
        }
    }
}
