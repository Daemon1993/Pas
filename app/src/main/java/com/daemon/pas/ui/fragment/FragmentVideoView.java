package com.daemon.pas.ui.fragment;

import com.daemon.framework.dcustomview.AroundCircleView;
import com.daemon.mvp.view.AppView;
import com.daemon.pas.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Daemon on 2015/11/25.
 */
public class FragmentVideoView extends AppView {
    @Bind(R.id.acv_icon)
    AroundCircleView acvIcon;

    @Override
    protected int getRootLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    public void destory() {
        super.destory();
        ButterKnife.unbind(this);
    }

    @Override
    public void initWeidget() {
        ButterKnife.bind(this,getRootView());

    }

    public void setAVCProgress(int progress) {
        acvIcon.setProgress(progress);
    }
}
