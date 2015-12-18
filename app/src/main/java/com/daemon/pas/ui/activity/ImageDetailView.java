package com.daemon.pas.ui.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.daemon.mvp.view.AppView;
import com.daemon.pas.R;
import com.daemon.pas.utils.ImageLoaderSetting;
import com.daemon.pas.utils.ToastUtil;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Daemon on 2015/12/15.
 */
public class ImageDetailView extends AppView {
    @Bind(R.id.img)
    ImageView img;
    @Bind(R.id.loading)
    ProgressBar loading;
    @Bind(R.id.bt_download)
    Button btDownload;

    PhotoViewAttacher mAttacher;

    @Override
    protected int getRootLayoutId() {
        return R.layout.activity_image_detail;
    }

    @Override
    public void initWeidget() {
        ButterKnife.bind(this, getRootView());
    }

    @Override
    public void destory() {
        super.destory();
        ButterKnife.unbind(this);

    }




    public void setPB(int visible) {
        loading.setVisibility(visible);
    }

    public void showImg(final Activity activity, String url, String dir) {
        mAttacher = new PhotoViewAttacher(img);
        mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {

            @Override
            public void onPhotoTap(View arg0, float arg1, float arg2) {
                activity.finish();
            }
        });

        ImageLoader.getInstance().displayImage(url, img, ImageLoaderSetting.defaultOptions, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                setPB(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                String message = null;
                switch (failReason.getType()) {
                    case IO_ERROR:
                        message = "下载错误";
                        break;
                    case DECODING_ERROR:
                        message = "图片无法显示";
                        break;
                    case NETWORK_DENIED:
                        message = "网络有问题，无法下载";
                        break;
                    case OUT_OF_MEMORY:
                        message = "图片太大无法显示";
                        break;
                    case UNKNOWN:
                        message = "未知的错误";
                        break;
                }

                ToastUtil.showToast(message);

                 setPB(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                 setPB(View.GONE);
                mAttacher.update();
            }
        });
    }
}
