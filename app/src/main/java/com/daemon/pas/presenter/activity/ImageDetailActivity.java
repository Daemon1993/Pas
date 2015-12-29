package com.daemon.pas.presenter.activity;

import android.view.View;

import com.daemon.framework.dutils.FileUtil;
import com.daemon.mvp.presenter.ActivityPresenter;
import com.daemon.pas.R;
import com.daemon.pas.common.BaseRequest;
import com.daemon.pas.ui.activity.ImageDetailView;
import com.daemon.pas.utils.ToastUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;

public class ImageDetailActivity extends ActivityPresenter<ImageDetailView> implements View.OnClickListener {


    public static final String URL = "url";
    public static final String H = "h";
    public static final String W = "w";
    public static final String NAME = "name";


    private String url;


    private String dir = BaseRequest.App_Dir + "downloadPic";
    private String name;

    @Override
    public Class<ImageDetailView> getIViewClass() {
        return ImageDetailView.class;
    }

    @Override
    protected void bindEventListener() {
        super.bindEventListener();

        url = getIntent().getStringExtra(URL);
        name = getIntent().getStringExtra(NAME);

        FileUtil.createDir(dir);

        iView.setOnClickListener(this, R.id.bt_download);

        iView.showImg(this,url,dir);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_download:

                File file = ImageLoader.getInstance().getDiskCache().get(url);
                File file1 = new File(dir + "/", name + ".jpg");
                FileUtil.saveFile(this, file, file1);

                ToastUtil.showToast("下载完毕");
                break;
        }
    }
}
