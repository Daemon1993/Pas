package com.daemon.framework.dutils.dproxyutil.image;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.daemon.framework.R;
import com.daemon.framework.dutils.ImageLoaderSetting;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by h2h on 2015/10/29.
 */
public class ImageProxyUtils implements ImageTypeLoad {


    //后续要增加或者改变就在这里天就 下面的TYPE修改
    public static final int TYPE_GLIDE = 1;
    public static final int TYPE_UIL = 2;


    public static int TYPE = TYPE_GLIDE;

    private RequestManager requestManager;

    private Context mContext;
    private ImageView mImage;
    private String url;

    public static ImageProxyUtils imageProxyUtils = new ImageProxyUtils();

    public static ImageProxyUtils getImageProxyUtils() {
        return imageProxyUtils;
    }

    //在Glide中fragment的类型
    public static final int TYPE_F = 100;
    public static final int TYPE_F4 = 101;
    public static final int TYPE_FN = 102;  //不是fragment


    private Fragment mFragment;
    private android.support.v4.app.Fragment mFragment4;


    public void loadImage(Context context, String url, ImageView imageView) {
        requestManager = Glide.with(context);
        this.mContext = context;
        this.mImage = imageView;
        this.url = url;

        loadImage(TYPE);
    }

    //这里有多个是因为Glide的特性 可以传入ACtivity Fragment
    public void loadImage(Activity context, String url, ImageView imageView) {
        requestManager = Glide.with(context);
        this.mContext = context;
        this.mImage = imageView;
        this.url = url;

        loadImage(TYPE);
    }

    public void loadImage(FragmentActivity context, String url, ImageView imageView) {
        requestManager = Glide.with(context);
        this.mContext = context;
        this.mImage = imageView;
        this.url = url;

        loadImage(TYPE);
    }

    public void loadImage(Fragment fragment, String url, ImageView imageView) {
        requestManager = Glide.with(fragment);
        this.mFragment = fragment;
        this.mImage = imageView;
        this.url = url;

        loadImage(TYPE);
    }

    public void loadImage(android.support.v4.app.Fragment fragment4, String url, ImageView imageView) {
        requestManager = Glide.with(fragment4);
        this.mFragment4 = fragment4;
        this.mImage = imageView;
        this.url = url;

        loadImage(TYPE);
    }


    //这里价格判断 重新一个方法
    private void loadImage(int type) {
        switch (type) {
            case TYPE_GLIDE:
                loadImageGLIDE();
                break;

            case TYPE_UIL:
                LoadImageUIL();

                break;
            default:
                break;
        }
    }


    /**
     * 根据需求添加相关的配置
     * 默认图片 等等
     */
    @Override
    public void loadImageGLIDE() {


        requestManager
                .load(url)
                .crossFade()
                .placeholder(R.mipmap.defaultbg)
                .error(R.mipmap.defaultbg)
                .into(mImage);


    }

    @Override
    public void LoadImageUIL() {
        ImageLoader.getInstance().displayImage(url,mImage, ImageLoaderSetting.defaultOptions,ImageLoaderSetting.animateFirstListener);
    }
}
