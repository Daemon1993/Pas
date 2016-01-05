# Pas
杂货铺 业余时间实现一些功能   MVP模式(Activity presenter)

前段时间 看到MVP比较火热 所以也查了相关资料
- 发现主流有两种 核心就是
1：让Activity/Fragment 作为View 实现V层 抽取本来在Activity/Fragment中的逻辑处理到Presenter

2：让Activity/Fragment 作为Presenter实现罗基础  抽取界面显示View 作为V层

这里使用第二种 试试这种小众的实现方式
这篇文章有介绍 这种方式
[https://github.com/bboyfeiyu/android-tech-frontier/tree/master/androidweekly/%E4%B8%80%E7%A7%8D%E5%9C%A8android%E4%B8%AD%E5%AE%9E%E7%8E%B0MVP%E6%A8%A1%E5%BC%8F%E7%9A%84%E6%96%B0%E6%80%9D%E8%B7%AF](https://github.com/bboyfeiyu/android-tech-frontier/tree/master/androidweekly/%E4%B8%80%E7%A7%8D%E5%9C%A8android%E4%B8%AD%E5%AE%9E%E7%8E%B0MVP%E6%A8%A1%E5%BC%8F%E7%9A%84%E6%96%B0%E6%80%9D%E8%B7%AF)

- 说说项目内容吧 内容不多 以后如果有想实现的功能也会加入进去 
 接口都是抓取的 新闻-天天快报 图片-androi壁纸 音乐-天天动听  如果有相关权益损害还请告知 

项目结构如下 

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/831873-57b97712144e8b3a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/831873-b8f4f87df588cd24.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


- MVP的架构module
![Paste_Image.png](http://upload-images.jianshu.io/upload_images/831873-8cd2104c2a47c9ac.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


- 工具module 包括自定义的View 下拉刷新 RecyclerViewAdapter的封装(addHead addFoot loadMore) 
![Paste_Image.png](http://upload-images.jianshu.io/upload_images/831873-76bcef9ad9f80dfa.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

主要内容大致如下
 

![Paste_Image.png](http://upload-images.jianshu.io/upload_images/831873-a0bcb549511967e8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


- 新闻模块 数据显示 RecyclerView 详情WebView查看 随机5个内容模块展示  下拉刷新 加载更多 

- 音乐模块 
1 图片背景虚化(高斯模糊 NDK实现-因为网上的高斯模糊java代码会有相关问题-这里写过一篇blog 地址  [android 图片 高斯模糊 Blur Android Studio JNI NDK 生成 so 问题汇总](http://www.jianshu.com/p/d3ab6de52712) ) 
2 圆形进度View 选择的圆形头像 周边的红色边框是可以设置0-360 可以在根据音乐播放进度显示 考虑到选择如果加上进度就会头晕 可以用在状态栏进度显示 写过一篇博客 
[圆形图片 周边进度 Progress 音乐播放进度](http://www.jianshu.com/p/d86dd3a37941)
3 自定义View 显示歌词  

- 图片模块
搜索功能 分类查看 加载更多 大图查看 

这个APP是 闲暇时间练手的 有些地方可能比较粗糙因为很多图片都是到处拿过来的
 
![QQ图片20160105115049.gif](http://upload-images.jianshu.io/upload_images/831873-4a6b1623755aaac4.gif?imageMogr2/auto-orient/strip)

同时 也是在实战中加深MVP的理解 如果有发现一些地方做的不好的 可以通过联系方式告诉我  


