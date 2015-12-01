package com.daemon.pas.utils;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.daemon.pas.R;

/**
 * 图片框架代理
 * 以防后续要换图片加载框架 不改变项目中的使用代码
 * Created by Daemon on 2015/10/29.
 */
public class ImageProxyUtils implements LoadImageType {

    //后续要增加或者改变就在这里天就 下面的TYPE修改
    public static final int TYPE_GLIDE = 1;

    public static int TYPE = TYPE_GLIDE;

    private RequestManager requestManager;

    private Context mContext;
    private ImageView mImage;
    private String url;

    private ImageProxyUtils(){

    }

    public static ImageProxyUtils imageProxyUtils = new ImageProxyUtils();

    public static ImageProxyUtils getImageProxyUtils() {
        return imageProxyUtils;
    }


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
                Log.e("Gilde加载", "OK");
                loadImageGLIDE();
                break;

            default:
                break;
        }
    }


    @Override
    public void loadImageGLIDE() {
        requestManager
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(mImage);

    }

}
