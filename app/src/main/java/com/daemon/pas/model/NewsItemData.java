package com.daemon.pas.model;

import com.daemon.mvp.model.IModel;
import com.google.gson.annotations.SerializedName;
import com.litesuits.orm.db.annotation.Table;

import java.util.List;

/**
 * Created by Daemon on 2015/11/26.
 */

@Table("news_table")
public class NewsItemData extends IModel {


    public String type;


    @Override
    public String toString() {
        return "NewsItemData{" +
                "type='" + type + '\'' +
                ", ret=" + ret +
                ", info='" + info + '\'' +
                ", timestamp=" + timestamp +
                ", newslist=" + newslist +
                '}';
    }

    /**
     * ret : 0
     * info : success
     * timestamp : 1448590598
     * newslist : [{"id":"20151126C02USA00","thumbnails_qqnews":["http://inews.gtimg.com/newsapp_ls/0/104536500_600312/0","http://inews.gtimg.com/newsapp_bt/0/104015817/640","http://inews.gtimg.com/newsapp_bt/0/104015841/640","http://inews.gtimg.com/newsapp_bt/0/104015854/640","http://inews.gtimg.com/newsapp_bt/0/104015864/640","http://inews.gtimg.com/newsapp_bt/0/104015919/640","http://inews.gtimg.com/newsapp_bt/0/104015929/640","http://inews.gtimg.com/newsapp_bt/0/104015940/640"],"thumbnails":["http://inews.gtimg.com/newsapp_ls/0/104536500_600312/0"],"articletype":"0","flag":"0","timestamp":1448526240,"time":"2015-11-26 16:24:00","commentid":"1255773210","url":"http://kuaibao.qq.com/s/20151126C02USA00","short_url":"http://kuaibao.qq.com/s/20151126C02USA00","title":"日本变态综艺！大胸妹子只穿内衣 当众被人摸来摸去","tag":[],"abstract":"日本的综艺节目向来以尺度大出名！生吞金鱼、嘴含青蛙、吹蟑螂......现在，他们已经不满足动物界了，","author":[],"chlid":"43545","origUrl":"http://stcv5.vivame.cn/spider/article/2015/11/26/3487534563515250227","show_expr":1,"picShowType":3,"chlname":"畅读","source":"畅读","openMarks":0,"a_ver":"00"},{"id":"20151126A03V2H00","thumbnails_qqnews":["http://inews.gtimg.com/newsapp_ls/0/104328585_150120/0"],"thumbnails":["http://inews.gtimg.com/newsapp_ls/0/104328585_150120/0"],"articletype":"0","flag":"0","timestamp":1448548693,"time":"2015-11-26 22:38:13","commentid":"1256102505","url":"http://kuaibao.qq.com/s/20151126A03V2H00","short_url":"http://kuaibao.qq.com/s/20151126A03V2H00","title":"儿子谢霆锋恋上大11岁王菲，老爸谢贤找小49岁嫩模","tag":[],"abstract":"据说谢贤和谢霆锋父子俩的\u201c爱好\u201d特别的互补，一个喜欢年轻嫩模，而一个倒相反喜欢年长熟女，前段时间谢霆锋母亲狄波拉的一句\u201c张柏芝没做到的，王菲都做到了\u201d在微博上引发了一场骂战，就小编来说，有其父必有其子，父亲母亲风流成性，儿子肯定有影响，所以谢霆锋后和王菲在一起也就出其不怪了。谢贤喜欢嫩模是众所周知的...","author":[],"chlid":"24858","origUrl":"","show_expr":1,"picShowType":0,"chlname":"健康养生知识","chlmrk":"健康养生知识，告别生活烦恼","chlsicon":"http://inews.gtimg.com/newsapp_ls/0/100843642_100100/0","chlicon":"http://inews.gtimg.com/newsapp_ls/0/100843642_100100/0","uin":"ec8bb1459b9d84100312bf035bb43cd4d0","source":"健康养生知识","openMarks":0,"a_ver":"00"},{"id":"ENT2015112605545500","uinnick":"腾讯新闻","uinname":"news_news_ent","title":"综艺时代，小鲜肉的天赐良机","surl":"http://kuaibao.qq.com/s/ENT2015112605545500","url":"http://kuaibao.qq.com/s/ENT2015112605545500","short_url":"http://kuaibao.qq.com/s/ENT2015112605545500","weiboid":"","commentid":"1256023895","origUrl":"","time":"2015-11-27 03:39:06","timestamp":1448566746,"articletype":"0","specialID":"","showType_video":"normal","thumbnails_big":[],"thumbnails_qqnews":["http://inews.gtimg.com/newsapp_ls/0/104414099_300240/0"],"thumbnails":["http://inews.gtimg.com/newsapp_ls/0/104414099_150120/0"],"picShowType":0,"qishu":"","source":"娱乐观","imagecount":1,"comment":"","flag":"1","tag":[],"voteId":"","voteNum":"","abstract":"TFBOYS鹿晗等纷纷上综艺，小鲜肉们寻到优势平台。","thumbnails_qqnews_photo":["http://inews.gtimg.com/newsapp_ls/0/104414099_640330/0"],"showType":"three","show_expr":1,"pushCommentCount":0,"graphicLiveID":"","gesture":1,"zhibo_vid":"","chlid":"2563","chlname":"娱乐观","chlsicon":"http://inews.gtimg.com/newsapp_ls/0/19681737_100100/0","chlicon":"http://inews.gtimg.com/newsapp_ls/0/19681737_100100/0","chlmrk":"腾讯娱乐《娱乐观》栏目，娱乐圈大事热点最快发声。","intro":"腾讯娱乐《娱乐观》栏目","keywords":[{"tagid":"1135","tagname":"TFboys","site":"ent"},{"tagid":"840","tagname":"鹿晗","site":"ent"},{"tagid":"826","tagname":"吴亦凡","site":"ent"}],"openMarks":0,"a_ver":"00"},{"id":"NEW2015112700645700","uinnick":"腾讯新闻","uinname":"news_news_top","title":"侯耀华否认私生女传闻：是6年前资助的女孩","surl":"http://kuaibao.qq.com/s/NEW2015112700645700","url":"http://kuaibao.qq.com/s/NEW2015112700645700","short_url":"http://kuaibao.qq.com/s/NEW2015112700645700","weiboid":"","commentid":"1256160155","origUrl":"","time":"2015-11-27 01:50:27","timestamp":1448560227,"articletype":"0","specialID":"","showType_video":"normal","thumbnails_big":[],"thumbnails_qqnews":["http://inews.gtimg.com/newsapp_ls/0/104417925_300240/0"],"thumbnails":["http://inews.gtimg.com/newsapp_ls/0/104417925_150120/0"],"picShowType":0,"qishu":"","source":"腾讯娱乐","imagecount":3,"comment":"","flag":"3","hasVideo":1,"tag":[],"voteId":"","voteNum":"","abstract":"称是自己多年前资助的女孩，以后也会一如既往地照顾她。","thumbnails_qqnews_photo":["http://inews.gtimg.com/newsapp_ls/0/104417925_640330/0"],"showType":"three","show_expr":1,"pushCommentCount":0,"graphicLiveID":"","gesture":1,"zhibo_vid":"","chlid":"","chlname":"腾讯娱乐","chlsicon":"","chlicon":"","chlmrk":"","intro":"","keywords":[],"openMarks":0,"a_ver":"00","videoTotalTime":"01:34"},{"id":"2015112700108000","uinnick":"腾讯新闻","uinname":"news_news_top","title":"组图：郭采洁身材暴瘦 脸蛋有变化被疑\u201c整容\u201d","surl":"http://kuaibao.qq.com/s/2015112700108000","url":"http://kuaibao.qq.com/s/2015112700108000","short_url":"http://kuaibao.qq.com/s/2015112700108000","weiboid":"","commentid":"1256153509","origUrl":"","time":"2015-11-27 00:54:54","timestamp":1448556894,"articletype":"1","specialID":"","showType_video":"normal","thumbnails_big":[],"thumbnails_qqnews":["http://inews.gtimg.com/newsapp_bt/0/104387995/640","http://inews.gtimg.com/newsapp_bt/0/104387996/640","http://inews.gtimg.com/newsapp_bt/0/104387997/640","http://inews.gtimg.com/newsapp_bt/0/104387999/640"],"thumbnails":["http://inews.gtimg.com/newsapp_lsa/0/104478829_685160/0"],"picShowType":2,"qishu":"","source":"中国新闻网","imagecount":4,"comment":"","flag":"0","tag":[],"voteId":"","voteNum":"","abstract":"组图：郭采洁身材暴瘦 脸蛋有变化被疑\u201c整容\u201d","thumbnails_qqnews_photo":["http://inews.gtimg.com/newsapp_ls/0/104478828_640330/0"],"showType":"three","show_expr":1,"pushCommentCount":0,"graphicLiveID":"","gesture":1,"zhibo_vid":"","chlid":"","chlname":"中国新闻网","chlsicon":"","chlicon":"","chlmrk":"","intro":"","photo_channel":{"photos":[[{"url":"http://inews.gtimg.com/newsapp_match/0/104387995/640","origUrl":"http://inews.gtimg.com/newsapp_match/0/104387995/1024","width":320,"height":480,"showWidth":318,"showHeight":426},{"url":"http://inews.gtimg.com/newsapp_match/0/104387996/640","origUrl":"http://inews.gtimg.com/newsapp_match/0/104387996/1024","width":320,"height":480,"showWidth":318,"showHeight":426}],[{"url":"http://inews.gtimg.com/newsapp_match/0/104387997/640","origUrl":"http://inews.gtimg.com/newsapp_match/0/104387997/1024","width":320,"height":480,"showWidth":318,"showHeight":426},{"url":"http://inews.gtimg.com/newsapp_match/0/104387999/640","origUrl":"http://inews.gtimg.com/newsapp_match/0/104387999/1024","width":320,"height":480,"showWidth":318,"showHeight":426}]],"egid":"2015112700108000","eid":"1","openSupport":1},"openMarks":0,"a_ver":"00"},{"id":"2015112700105600","uinnick":"腾讯新闻","uinname":"news_news_top","title":"\"肿瘤君\"弘扬正能量 获国家重点扶持","surl":"http://kuaibao.qq.com/s/2015112700105600","url":"http://kuaibao.qq.com/s/2015112700105600","short_url":"http://kuaibao.qq.com/s/2015112700105600","weiboid":"","commentid":"1256153285","origUrl":"","time":"2015-11-27 00:53:57","timestamp":1448556837,"articletype":"0","specialID":"","showType_video":"normal","thumbnails_big":[],"thumbnails_qqnews":["http://inews.gtimg.com/newsapp_ls/0/104428191_300240/0"],"thumbnails":["http://inews.gtimg.com/newsapp_ls/0/104428191_150120/0"],"picShowType":0,"qishu":"","source":"新华社","imagecount":1,"comment":"","flag":"3","hasVideo":1,"tag":[],"voteId":"","voteNum":"","abstract":"文化部公布核心价值观动漫扶持名单，《肿瘤君》等入选。","thumbnails_qqnews_photo":["http://inews.gtimg.com/newsapp_ls/0/104428191_640330/0"],"showType":"three","show_expr":1,"pushCommentCount":0,"graphicLiveID":"","gesture":1,"zhibo_vid":"","chlid":"","chlname":"新华社","chlsicon":"","chlicon":"","chlmrk":"","intro":"","openMarks":0,"a_ver":"00","videoTotalTime":"00:37"},{"id":"20151127C003GF00","thumbnails_qqnews":["http://inews.gtimg.com/newsapp_ls/0/104392011_150110/0"],"thumbnails":["http://inews.gtimg.com/newsapp_ls/0/104392011_150110/0"],"articletype":"0","flag":"0","timestamp":1448554860,"time":"2015-11-27 00:21:00","commentid":"1256155196","url":"http://kuaibao.qq.com/s/20151127C003GF00","short_url":"http://kuaibao.qq.com/s/20151127C003GF00","title":"李易峰林允儿搞怪合影 互赞对方颜值逆天","tag":[],"abstract":"新浪娱乐讯 11月26日晚，李易峰[微博]通过微博晒和允儿俏皮合影，并称赞道：\u201c颜值逆天！\u201d照片中，两人身处布拉格，李易峰瞪大眼睛，一手捂嘴做惊讶状，另一手指向身旁的允儿，允儿双手抱于胸前，配合的非常","author":[],"chlid":"32670","origUrl":"http://ent.sina.com.cn/s/m/2015-11-27/doc-ifxmaznc5670088.shtml","show_expr":1,"picShowType":0,"chlname":"新浪新闻","source":"新浪新闻","openMarks":0,"a_ver":"00"},{"id":"20151127C006A500","thumbnails_qqnews":["http://inews.gtimg.com/newsapp_ls/0/104409341_150110/0"],"thumbnails":["http://inews.gtimg.com/newsapp_ls/0/104409341_150110/0"],"articletype":"0","flag":"0","timestamp":1448552940,"time":"2015-11-26 23:49:00","commentid":"1256161152","url":"http://kuaibao.qq.com/s/20151127C006A500","short_url":"http://kuaibao.qq.com/s/20151127C006A500","title":"李威谈与姚笛恋情进展：希望工作之余有好事发生","tag":[],"abstract":"由于之前在微博公开示爱姚笛以及和杨子晴（陶喆的出轨对象）亲密照片的曝光，演员李威最近一直受到外界关注。前日，他现身由其主演的电影《红髅》发布会现场。当记者问到他与姚笛的关系进展时，李威先是谢谢大家关心","author":[],"chlid":"42746","origUrl":"http://www.xinwenwang.com/r2196387/","show_expr":1,"picShowType":0,"chlname":"新闻王","source":"新闻王","img_face":{"http://inews.gtimg.com/newsapp_ls/0/104409341_450360/0":{"x":150,"y":84,"height":204,"width":87},"http://inews.gtimg.com/newsapp_ls/0/104409341_300240/0":{"x":100,"y":56,"height":136,"width":58},"http://inews.gtimg.com/newsapp_ls/0/104409341_150120/0":{"x":50,"y":28,"height":68,"width":29},"http://inews.gtimg.com/newsapp_ls/0/104409341_150110/0":{"x":50,"y":28,"height":68,"width":29}},"openMarks":0,"a_ver":"00"},{"id":"2015112700106600","uinnick":"腾讯新闻","uinname":"news_news_ent","title":"郭可盈已视熊黛林为弟媳 否认两人冲绳秘婚","surl":"http://kuaibao.qq.com/s/2015112700106600","url":"http://kuaibao.qq.com/s/2015112700106600","short_url":"http://kuaibao.qq.com/s/2015112700106600","weiboid":"","commentid":"1256153330","origUrl":"","time":"2015-11-27 00:54:11","timestamp":1448556851,"articletype":"1","specialID":"","showType_video":"normal","thumbnails_big":[],"thumbnails_qqnews":["http://inews.gtimg.com/newsapp_bt/0/104387695/640","http://inews.gtimg.com/newsapp_bt/0/104387696/640","http://inews.gtimg.com/newsapp_bt/0/104387697/640","http://inews.gtimg.com/newsapp_bt/0/104387699/640","http://inews.gtimg.com/newsapp_bt/0/104387701/640","http://inews.gtimg.com/newsapp_bt/0/104387702/640","http://inews.gtimg.com/newsapp_bt/0/104387703/640"],"thumbnails":["http://inews.gtimg.com/newsapp_ls/0/104387724_640330/0"],"picShowType":2,"qishu":"","source":"东方日报网-东方日报","imagecount":7,"comment":"","flag":"0","tag":[],"voteId":"","voteNum":"","abstract":"林文龙、郭可盈林文龙、郭可盈林文龙、郭可盈林文龙、郭可盈林文龙、郭可盈熊黛林郭可颂","thumbnails_qqnews_photo":[],"showType":"three","show_expr":1,"pushCommentCount":0,"graphicLiveID":"","gesture":1,"zhibo_vid":"","chlid":"","chlname":"东方日报网-东方日报","chlsicon":"","chlicon":"","chlmrk":"","intro":"","photo_channel":{"photos":[[{"url":"http://inews.gtimg.com/newsapp_match/0/104387695/640","origUrl":"http://inews.gtimg.com/newsapp_match/0/104387695/1024","width":320,"height":480,"showWidth":640,"showHeight":640}],[{"url":"http://inews.gtimg.com/newsapp_match/0/104387696/640","origUrl":"http://inews.gtimg.com/newsapp_match/0/104387696/1024","width":640,"height":426,"showWidth":318,"showHeight":212},{"url":"http://inews.gtimg.com/newsapp_match/0/104387697/640","origUrl":"http://inews.gtimg.com/newsapp_match/0/104387697/1024","width":640,"height":426,"showWidth":318,"showHeight":212}]],"egid":"2015112700106600","eid":"1","openSupport":1},"openMarks":0,"a_ver":"00"},{"id":"ENT2015112700065400","uinnick":"腾讯新闻","uinname":"news_news_ent","title":"郭晶晶难得盛装亮相 与老公霍启刚恩爱出席慈善晚宴","surl":"http://kuaibao.qq.com/s/ENT2015112700065400","url":"http://kuaibao.qq.com/s/ENT2015112700065400","short_url":"http://kuaibao.qq.com/s/ENT2015112700065400","weiboid":"","commentid":"1256148174","origUrl":"","time":"2015-11-27 00:20:21","timestamp":1448554821,"articletype":"1","specialID":"","showType_video":"normal","thumbnails_big":[],"thumbnails_qqnews":["http://inews.gtimg.com/newsapp_bt/0/104380209/640","http://inews.gtimg.com/newsapp_bt/0/104380210/640","http://inews.gtimg.com/newsapp_bt/0/104380211/640","http://inews.gtimg.com/newsapp_bt/0/104380213/640","http://inews.gtimg.com/newsapp_bt/0/104380214/640"],"thumbnails":["http://inews.gtimg.com/newsapp_lsa/0/104430142_685160/0"],"picShowType":2,"qishu":"","source":"视觉中国","imagecount":5,"comment":"","flag":"0","tag":[],"voteId":"","voteNum":"","abstract":"组图：霍启刚郭晶晶夫妇现身慈善晚宴 大方任拍","thumbnails_qqnews_photo":["http://inews.gtimg.com/newsapp_ls/0/104434443_640330/0"],"showType":"three","show_expr":1,"pushCommentCount":0,"graphicLiveID":"","gesture":1,"zhibo_vid":"","chlid":"","chlname":"视觉中国","chlsicon":"","chlicon":"","chlmrk":"","intro":"","keywords":[{"tagid":"766","tagname":"郭晶晶","site":"ent"}],"photo_channel":{"photos":[[{"url":"http://inews.gtimg.com/newsapp_match/0/104380209/640","origUrl":"http://inews.gtimg.com/newsapp_match/0/104380209/1024","width":640,"height":427,"showWidth":640,"showHeight":426}],[{"url":"http://inews.gtimg.com/newsapp_match/0/104380210/640","origUrl":"http://inews.gtimg.com/newsapp_match/0/104380210/1024","width":320,"height":480,"showWidth":318,"showHeight":426},{"url":"http://inews.gtimg.com/newsapp_match/0/104380211/640","origUrl":"http://inews.gtimg.com/newsapp_match/0/104380211/1024","width":320,"height":480,"showWidth":318,"showHeight":426}]],"egid":"ENT2015112700065400","eid":"1","openSupport":1},"openMarks":0,"a_ver":"00"},{"id":"ENT2015112700056500","uinnick":"腾讯新闻","uinname":"news_news_ent","title":"董洁现身机场 行李遭开包检查面露无奈","surl":"http://kuaibao.qq.com/s/ENT2015112700056500","url":"http://kuaibao.qq.com/s/ENT2015112700056500","short_url":"http://kuaibao.qq.com/s/ENT2015112700056500","weiboid":"","commentid":"1256146817","origUrl":"","time":"2015-11-27 00:14:33","timestamp":1448554473,"articletype":"1","specialID":"","showType_video":"normal","thumbnails_big":[],"thumbnails_qqnews":["http://inews.gtimg.com/newsapp_bt/0/104378691/640","http://inews.gtimg.com/newsapp_bt/0/104378692/640","http://inews.gtimg.com/newsapp_bt/0/104378693/640","http://inews.gtimg.com/newsapp_bt/0/104378694/640"],"thumbnails":["http://inews.gtimg.com/newsapp_lsa/0/104435704_685160/0"],"picShowType":2,"qishu":"","source":"视觉中国","imagecount":4,"comment":"","flag":"0","tag":[],"voteId":"","voteNum":"","abstract":"组图：董洁现身机场 行李遭开包检查面露无奈","thumbnails_qqnews_photo":["http://inews.gtimg.com/newsapp_ls/0/104435703_640330/0"],"showType":"three","show_expr":1,"pushCommentCount":0,"graphicLiveID":"","gesture":1,"zhibo_vid":"","chlid":"","chlname":"视觉中国","chlsicon":"","chlicon":"","chlmrk":"","intro":"","keywords":[{"tagid":"734","tagname":"董洁","site":"ent"}],"photo_channel":{"photos":[[{"url":"http://inews.gtimg.com/newsapp_match/0/104378691/640","origUrl":"http://inews.gtimg.com/newsapp_match/0/104378691/1024","width":640,"height":426,"showWidth":640,"showHeight":426}]],"egid":"ENT2015112700056500","eid":"1","openSupport":1},"openMarks":0,"a_ver":"00"},{"id":"20151127C006W900","thumbnails_qqnews":["http://inews.gtimg.com/newsapp_ls/0/104523185_600312/0","http://inews.gtimg.com/newsapp_bt/0/104412023/640","http://inews.gtimg.com/newsapp_bt/0/104412034/640","http://inews.gtimg.com/newsapp_bt/0/104412044/640","http://inews.gtimg.com/newsapp_bt/0/104412054/640","http://inews.gtimg.com/newsapp_bt/0/104412061/640","http://inews.gtimg.com/newsapp_bt/0/104412069/640","http://inews.gtimg.com/newsapp_bt/0/104412078/640"],"thumbnails":["http://inews.gtimg.com/newsapp_ls/0/104523185_600312/0"],"articletype":"0","flag":"0","timestamp":1448553600,"time":"2015-11-27 00:00:00","commentid":"1256162246","url":"http://kuaibao.qq.com/s/20151127C006W900","short_url":"http://kuaibao.qq.com/s/20151127C006W900","title":"这个妹子疯了！锯掉6根肋骨，只为把自己整成变态细腰","tag":[],"abstract":"这个妹纸叫Pixee，今年25岁","author":[],"chlid":"44289","origUrl":"http://www.vccoo.com/v/b67f3e","show_expr":1,"picShowType":3,"chlname":"微口网","source":"微口网","img_face":{"http://inews.gtimg.com/newsapp_bt/0/104412006/640":{"x":155,"y":58,"height":38,"width":38},"http://inews.gtimg.com/newsapp_bt/0/104412006/1000":{"x":287,"y":108,"height":70,"width":70},"http://inews.gtimg.com/newsapp_match/0/104412006/0":{"x":287,"y":108,"height":70,"width":70},"http://inews.gtimg.com/newsapp_bt/0/104412023/640":{"x":83,"y":56,"height":195,"width":195},"http://inews.gtimg.com/newsapp_bt/0/104412023/1000":{"x":141,"y":95,"height":331,"width":331},"http://inews.gtimg.com/newsapp_match/0/104412023/0":{"x":141,"y":95,"height":331,"width":331},"http://inews.gtimg.com/newsapp_bt/0/104412078/640":{"x":39,"y":133,"height":232,"width":232},"http://inews.gtimg.com/newsapp_bt/0/104412078/1000":{"x":55,"y":187,"height":327,"width":327},"http://inews.gtimg.com/newsapp_match/0/104412078/0":{"x":55,"y":187,"height":327,"width":327},"http://inews.gtimg.com/newsapp_bt/0/104412100/640":{"x":280,"y":129,"height":193,"width":193},"http://inews.gtimg.com/newsapp_bt/0/104412100/1000":{"x":280,"y":129,"height":193,"width":193},"http://inews.gtimg.com/newsapp_match/0/104412100/0":{"x":280,"y":129,"height":193,"width":193},"http://inews.gtimg.com/newsapp_bt/0/104412115/640":{"x":88,"y":58,"height":56,"width":56},"http://inews.gtimg.com/newsapp_bt/0/104412115/1000":{"x":143,"y":94,"height":91,"width":91},"http://inews.gtimg.com/newsapp_match/0/104412115/0":{"x":143,"y":94,"height":91,"width":91},"http://inews.gtimg.com/newsapp_bt/0/104412126/640":{"x":170,"y":77,"height":53,"width":53},"http://inews.gtimg.com/newsapp_bt/0/104412126/1000":{"x":337,"y":153,"height":105,"width":105},"http://inews.gtimg.com/newsapp_match/0/104412126/0":{"x":337,"y":153,"height":105,"width":105},"http://inews.gtimg.com/newsapp_bt/0/104412152/640":{"x":166,"y":71,"height":42,"width":42},"http://inews.gtimg.com/newsapp_bt/0/104412152/1000":{"x":329,"y":141,"height":83,"width":83},"http://inews.gtimg.com/newsapp_match/0/104412152/0":{"x":329,"y":141,"height":83,"width":83},"http://inews.gtimg.com/newsapp_bt/0/104412160/640":{"x":124,"y":42,"height":42,"width":42},"http://inews.gtimg.com/newsapp_bt/0/104412160/1000":{"x":259,"y":88,"height":88,"width":88},"http://inews.gtimg.com/newsapp_match/0/104412160/0":{"x":265,"y":90,"height":90,"width":90},"http://inews.gtimg.com/newsapp_bt/0/104412174/640":{"x":176,"y":17,"height":51,"width":51},"http://inews.gtimg.com/newsapp_bt/0/104412174/1000":{"x":268,"y":26,"height":78,"width":78},"http://inews.gtimg.com/newsapp_match/0/104412174/0":{"x":268,"y":26,"height":78,"width":78},"http://inews.gtimg.com/newsapp_bt/0/104412183/640":{"x":115,"y":34,"height":82,"width":82},"http://inews.gtimg.com/newsapp_bt/0/104412183/1000":{"x":207,"y":61,"height":148,"width":148},"http://inews.gtimg.com/newsapp_match/0/104412183/0":{"x":207,"y":61,"height":148,"width":148},"http://inews.gtimg.com/newsapp_bt/0/104412192/640":{"x":128,"y":44,"height":87,"width":87},"http://inews.gtimg.com/newsapp_bt/0/104412192/1000":{"x":231,"y":79,"height":157,"width":157},"http://inews.gtimg.com/newsapp_match/0/104412192/0":{"x":231,"y":79,"height":157,"width":157},"http://inews.gtimg.com/newsapp_bt/0/104412202/640":{"x":142,"y":46,"height":79,"width":79},"http://inews.gtimg.com/newsapp_bt/0/104412202/1000":{"x":256,"y":83,"height":142,"width":142},"http://inews.gtimg.com/newsapp_match/0/104412202/0":{"x":256,"y":83,"height":142,"width":142},"http://inews.gtimg.com/newsapp_bt/0/104412212/640":{"x":55,"y":38,"height":73,"width":73},"http://inews.gtimg.com/newsapp_bt/0/104412212/1000":{"x":80,"y":55,"height":106,"width":106},"http://inews.gtimg.com/newsapp_match/0/104412212/0":{"x":80,"y":55,"height":106,"width":106},"http://inews.gtimg.com/newsapp_bt/0/104412236/640":{"x":145,"y":24,"height":48,"width":123},"http://inews.gtimg.com/newsapp_bt/0/104412236/1000":{"x":241,"y":40,"height":80,"width":204},"http://inews.gtimg.com/newsapp_match/0/104412236/0":{"x":241,"y":40,"height":80,"width":204}},"openMarks":0,"a_ver":"00"},{"id":"20151127C00F6J00","thumbnails_qqnews":["http://inews.gtimg.com/newsapp_bt/0/104459212/640","http://inews.gtimg.com/newsapp_bt/0/104459224/640","http://inews.gtimg.com/newsapp_bt/0/104459233/640","http://inews.gtimg.com/newsapp_bt/0/104459240/640","http://inews.gtimg.com/newsapp_bt/0/104459248/640","http://inews.gtimg.com/newsapp_bt/0/104459259/640","http://inews.gtimg.com/newsapp_bt/0/104459267/640","http://inews.gtimg.com/newsapp_bt/0/104459278/640"],"thumbnails":["http://inews.gtimg.com/newsapp_ls/0/104459220_580300/0"],"articletype":"0","flag":"0","timestamp":1448553600,"time":"2015-11-27 00:00:00","commentid":"1256179675","url":"http://kuaibao.qq.com/s/20151127C00F6J00","short_url":"http://kuaibao.qq.com/s/20151127C00F6J00","title":"19岁日本女星教室换衣险走光 大尺度写真曝光","tag":[],"abstract":"96年日本女星星名美津纪是日本写真偶像，1996年出生，才18岁的她迄今拍摄过不少性感写真。在这组以","author":[],"chlid":"44527","origUrl":"http://www.shangc.net/tuku/a/39660.html","show_expr":1,"picShowType":2,"chlname":"尚之潮","source":"尚之潮","img_face":{"http://inews.gtimg.com/newsapp_bt/0/104459212/640":{"x":124,"y":33,"height":55,"width":55},"http://inews.gtimg.com/newsapp_bt/0/104459212/1000":{"x":174,"y":46,"height":77,"width":77},"http://inews.gtimg.com/newsapp_match/0/104459212/0":{"x":174,"y":46,"height":77,"width":77},"http://inews.gtimg.com/newsapp_bt/0/104459233/640":{"x":133,"y":111,"height":76,"width":76},"http://inews.gtimg.com/newsapp_bt/0/104459233/1000":{"x":250,"y":209,"height":143,"width":143},"http://inews.gtimg.com/newsapp_match/0/104459233/0":{"x":250,"y":209,"height":143,"width":143},"http://inews.gtimg.com/newsapp_bt/0/104459248/640":{"x":134,"y":111,"height":58,"width":58},"http://inews.gtimg.com/newsapp_bt/0/104459248/1000":{"x":252,"y":209,"height":109,"width":109},"http://inews.gtimg.com/newsapp_match/0/104459248/0":{"x":252,"y":209,"height":109,"width":109},"http://inews.gtimg.com/newsapp_bt/0/104459300/640":{"x":127,"y":80,"height":56,"width":56},"http://inews.gtimg.com/newsapp_bt/0/104459300/1000":{"x":239,"y":150,"height":105,"width":105},"http://inews.gtimg.com/newsapp_match/0/104459300/0":{"x":239,"y":150,"height":105,"width":105},"http://inews.gtimg.com/newsapp_bt/0/104459312/640":{"x":116,"y":62,"height":91,"width":91},"http://inews.gtimg.com/newsapp_bt/0/104459312/1000":{"x":218,"y":117,"height":171,"width":171},"http://inews.gtimg.com/newsapp_match/0/104459312/0":{"x":218,"y":117,"height":171,"width":171},"http://inews.gtimg.com/newsapp_bt/0/104459378/640":{"x":147,"y":94,"height":44,"width":44},"http://inews.gtimg.com/newsapp_bt/0/104459378/1000":{"x":276,"y":177,"height":83,"width":83},"http://inews.gtimg.com/newsapp_match/0/104459378/0":{"x":276,"y":177,"height":83,"width":83}},"openMarks":0,"a_ver":"00"},{"id":"20151127C00DUU00","thumbnails_qqnews":["http://inews.gtimg.com/newsapp_bt/0/104450973/640","http://inews.gtimg.com/newsapp_bt/0/104450980/640","http://inews.gtimg.com/newsapp_bt/0/104450986/640","http://inews.gtimg.com/newsapp_bt/0/104450990/640","http://inews.gtimg.com/newsapp_bt/0/104450994/640","http://inews.gtimg.com/newsapp_bt/0/104450999/640","http://inews.gtimg.com/newsapp_bt/0/104451002/640","http://inews.gtimg.com/newsapp_bt/0/104451005/640"],"thumbnails":["http://inews.gtimg.com/newsapp_ls/0/104450976_580300/0"],"articletype":"0","flag":"0","timestamp":1448553600,"time":"2015-11-27 00:00:00","commentid":"1256174933","url":"http://kuaibao.qq.com/s/20151127C00DUU00","short_url":"http://kuaibao.qq.com/s/20151127C00DUU00","title":"国际模特大赛后台：选手豪放坐姿不惧走光","tag":[],"abstract":"2014国际模特大赛总决赛彩排举行，俊男靓女选手亮相舞台。期间，他们内着泳装外套黑靓风衣走台，几十位","author":[],"chlid":"44527","origUrl":"http://www.shangc.net/tuku/a/36669.html","show_expr":1,"picShowType":2,"chlname":"尚之潮","source":"尚之潮","img_face":{"http://inews.gtimg.com/newsapp_bt/0/104450973/640":{"x":186,"y":137,"height":57,"width":57},"http://inews.gtimg.com/newsapp_bt/0/104450973/1000":{"x":262,"y":193,"height":80,"width":80},"http://inews.gtimg.com/newsapp_match/0/104450973/0":{"x":262,"y":193,"height":80,"width":80},"http://inews.gtimg.com/newsapp_bt/0/104450980/640":{"x":9,"y":190,"height":25,"width":25},"http://inews.gtimg.com/newsapp_bt/0/104450980/1000":{"x":13,"y":267,"height":35,"width":35},"http://inews.gtimg.com/newsapp_match/0/104450980/0":{"x":13,"y":267,"height":35,"width":35},"http://inews.gtimg.com/newsapp_bt/0/104450986/640":{"x":278,"y":53,"height":65,"width":332},"http://inews.gtimg.com/newsapp_bt/0/104450986/1000":{"x":391,"y":75,"height":91,"width":467},"http://inews.gtimg.com/newsapp_match/0/104450986/0":{"x":391,"y":75,"height":91,"width":467},"http://inews.gtimg.com/newsapp_bt/0/104450994/640":{"x":91,"y":92,"height":43,"width":226},"http://inews.gtimg.com/newsapp_bt/0/104450994/1000":{"x":128,"y":129,"height":60,"width":318},"http://inews.gtimg.com/newsapp_match/0/104450994/0":{"x":128,"y":129,"height":60,"width":318},"http://inews.gtimg.com/newsapp_bt/0/104451002/640":{"x":51,"y":70,"height":42,"width":294},"http://inews.gtimg.com/newsapp_bt/0/104451002/1000":{"x":72,"y":98,"height":59,"width":413},"http://inews.gtimg.com/newsapp_match/0/104451002/0":{"x":72,"y":98,"height":59,"width":413},"http://inews.gtimg.com/newsapp_bt/0/104451005/640":{"x":312,"y":92,"height":44,"width":44},"http://inews.gtimg.com/newsapp_bt/0/104451005/1000":{"x":439,"y":129,"height":62,"width":62},"http://inews.gtimg.com/newsapp_match/0/104451005/0":{"x":439,"y":129,"height":62,"width":62},"http://inews.gtimg.com/newsapp_bt/0/104451007/640":{"x":265,"y":124,"height":38,"width":322},"http://inews.gtimg.com/newsapp_bt/0/104451007/1000":{"x":373,"y":174,"height":53,"width":453},"http://inews.gtimg.com/newsapp_match/0/104451007/0":{"x":373,"y":174,"height":53,"width":453},"http://inews.gtimg.com/newsapp_bt/0/104451010/640":{"x":565,"y":72,"height":56,"width":56},"http://inews.gtimg.com/newsapp_bt/0/104451010/1000":{"x":795,"y":101,"height":79,"width":79},"http://inews.gtimg.com/newsapp_match/0/104451010/0":{"x":795,"y":101,"height":79,"width":79}},"openMarks":0,"a_ver":"00"},{"id":"20151127C002IA00","thumbnails_qqnews":["http://inews.gtimg.com/newsapp_bt/0/104387225/640","http://inews.gtimg.com/newsapp_bt/0/104387236/640","http://inews.gtimg.com/newsapp_bt/0/104387246/640","http://inews.gtimg.com/newsapp_bt/0/104387259/640"],"thumbnails":["http://inews.gtimg.com/newsapp_ls/0/104387231_150110/0"],"articletype":"0","flag":"0","timestamp":1448553360,"time":"2015-11-26 23:56:00","commentid":"1256153120","url":"http://kuaibao.qq.com/s/20151127C002IA00","short_url":"http://kuaibao.qq.com/s/20151127C002IA00","title":"周韦彤穿热裤露长腿 与两男友人现身影院","tag":[],"abstract":"新浪娱乐讯 早前，周韦彤现身某影院，穿着热裤和友人从卫生间走出，露出粗壮大腿。她身旁还有两位型男相伴","author":[],"chlid":"30621","origUrl":"http://slide.ent.sina.com.cn/star/slide_4_704_125994.html","show_expr":1,"picShowType":2,"chlname":"新浪网","source":"新浪网","img_face":{"http://inews.gtimg.com/newsapp_bt/0/104387225/640":{"x":126,"y":105,"height":34,"width":34},"http://inews.gtimg.com/newsapp_bt/0/104387225/1000":{"x":262,"y":218,"height":71,"width":71},"http://inews.gtimg.com/newsapp_match/0/104387225/0":{"x":268,"y":224,"height":72,"width":72},"http://inews.gtimg.com/newsapp_bt/0/104387236/640":{"x":56,"y":35,"height":144,"width":154},"http://inews.gtimg.com/newsapp_bt/0/104387236/1000":{"x":117,"y":73,"height":300,"width":321},"http://inews.gtimg.com/newsapp_match/0/104387236/0":{"x":120,"y":75,"height":307,"width":329},"http://inews.gtimg.com/newsapp_bt/0/104387246/640":{"x":60,"y":98,"height":56,"width":222},"http://inews.gtimg.com/newsapp_bt/0/104387246/1000":{"x":125,"y":204,"height":117,"width":462},"http://inews.gtimg.com/newsapp_match/0/104387246/0":{"x":128,"y":209,"height":119,"width":473}},"openMarks":0,"a_ver":"00"}]
     */

    private int ret;
    private String info;
    private int timestamp;
    /**
     * id : 20151126C02USA00
     * thumbnails_qqnews : ["http://inews.gtimg.com/newsapp_ls/0/104536500_600312/0","http://inews.gtimg.com/newsapp_bt/0/104015817/640","http://inews.gtimg.com/newsapp_bt/0/104015841/640","http://inews.gtimg.com/newsapp_bt/0/104015854/640","http://inews.gtimg.com/newsapp_bt/0/104015864/640","http://inews.gtimg.com/newsapp_bt/0/104015919/640","http://inews.gtimg.com/newsapp_bt/0/104015929/640","http://inews.gtimg.com/newsapp_bt/0/104015940/640"]
     * thumbnails : ["http://inews.gtimg.com/newsapp_ls/0/104536500_600312/0"]
     * articletype : 0
     * flag : 0
     * timestamp : 1448526240
     * time : 2015-11-26 16:24:00
     * commentid : 1255773210
     * url : http://kuaibao.qq.com/s/20151126C02USA00
     * short_url : http://kuaibao.qq.com/s/20151126C02USA00
     * title : 日本变态综艺！大胸妹子只穿内衣 当众被人摸来摸去
     * tag : []
     * abstract : 日本的综艺节目向来以尺度大出名！生吞金鱼、嘴含青蛙、吹蟑螂......现在，他们已经不满足动物界了，
     * author : []
     * chlid : 43545
     * origUrl : http://stcv5.vivame.cn/spider/article/2015/11/26/3487534563515250227
     * show_expr : 1
     * picShowType : 3
     * chlname : 畅读
     * source : 畅读
     * openMarks : 0
     * a_ver : 00
     */

    private List<NewslistEntity> newslist;

    public void setRet(int ret) {
        this.ret = ret;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public void setNewslist(List<NewslistEntity> newslist) {
        this.newslist = newslist;
    }

    public int getRet() {
        return ret;
    }

    public String getInfo() {
        return info;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public List<NewslistEntity> getNewslist() {
        return newslist;
    }

    public static class NewslistEntity{


        @Override
        public String toString() {
            return "NewslistEntity{" +
                    "id='" + id + '\'' +
                    ", articletype='" + articletype + '\'' +
                    ", flag='" + flag + '\'' +
                    ", timestamp=" + timestamp +
                    ", time='" + time + '\'' +
                    ", commentid='" + commentid + '\'' +
                    ", url='" + url + '\'' +
                    ", short_url='" + short_url + '\'' +
                    ", title='" + title + '\'' +
                    ", abstractX='" + abstractX + '\'' +
                    ", chlid='" + chlid + '\'' +
                    ", origUrl='" + origUrl + '\'' +
                    ", show_expr=" + show_expr +
                    ", picShowType=" + picShowType +
                    ", chlname='" + chlname + '\'' +
                    ", source='" + source + '\'' +
                    ", openMarks=" + openMarks +
                    ", a_ver='" + a_ver + '\'' +
                    ", thumbnails_qqnews=" + thumbnails_qqnews +
                    ", thumbnails=" + thumbnails +
                    ", tag=" + tag +
                    ", author=" + author +
                    '}';
        }

        private String id;
        private String articletype;
        private String flag;
        private int timestamp;
        private String time;
        private String commentid;
        private String url;
        private String short_url;
        private String title;
        @SerializedName("abstract")
        private String abstractX;
        private String chlid;
        private String origUrl;
        private int show_expr;
        private int picShowType;
        private String chlname;
        private String source;
        private int openMarks;
        private String a_ver;
        private List<String> thumbnails_qqnews;
        private List<String> thumbnails;
        private List<?> tag;
        private List<?> author;

        public void setId(String id) {
            this.id = id;
        }

        public void setArticletype(String articletype) {
            this.articletype = articletype;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public void setCommentid(String commentid) {
            this.commentid = commentid;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setShort_url(String short_url) {
            this.short_url = short_url;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setAbstractX(String abstractX) {
            this.abstractX = abstractX;
        }

        public void setChlid(String chlid) {
            this.chlid = chlid;
        }

        public void setOrigUrl(String origUrl) {
            this.origUrl = origUrl;
        }

        public void setShow_expr(int show_expr) {
            this.show_expr = show_expr;
        }

        public void setPicShowType(int picShowType) {
            this.picShowType = picShowType;
        }

        public void setChlname(String chlname) {
            this.chlname = chlname;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public void setOpenMarks(int openMarks) {
            this.openMarks = openMarks;
        }

        public void setA_ver(String a_ver) {
            this.a_ver = a_ver;
        }

        public void setThumbnails_qqnews(List<String> thumbnails_qqnews) {
            this.thumbnails_qqnews = thumbnails_qqnews;
        }

        public void setThumbnails(List<String> thumbnails) {
            this.thumbnails = thumbnails;
        }

        public void setTag(List<?> tag) {
            this.tag = tag;
        }

        public void setAuthor(List<?> author) {
            this.author = author;
        }

        public String getId() {
            return id;
        }

        public String getArticletype() {
            return articletype;
        }

        public String getFlag() {
            return flag;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public String getTime() {
            return time;
        }

        public String getCommentid() {
            return commentid;
        }

        public String getUrl() {
            return url;
        }

        public String getShort_url() {
            return short_url;
        }

        public String getTitle() {
            return title;
        }

        public String getAbstractX() {
            return abstractX;
        }

        public String getChlid() {
            return chlid;
        }

        public String getOrigUrl() {
            return origUrl;
        }

        public int getShow_expr() {
            return show_expr;
        }

        public int getPicShowType() {
            return picShowType;
        }

        public String getChlname() {
            return chlname;
        }

        public String getSource() {
            return source;
        }

        public int getOpenMarks() {
            return openMarks;
        }

        public String getA_ver() {
            return a_ver;
        }

        public List<String> getThumbnails_qqnews() {
            return thumbnails_qqnews;
        }

        public List<String> getThumbnails() {
            return thumbnails;
        }

        public List<?> getTag() {
            return tag;
        }

        public List<?> getAuthor() {
            return author;
        }
    }
}
