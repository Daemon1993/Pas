package com.daemon.pas.ui.activity;

import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.daemon.mvp.view.AppView;
import com.daemon.pas.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Daemon on 2015/11/30.
 */
public class NewsDetailView extends AppView {
    @Bind(R.id.toolbar)
    public Toolbar toolbar;
    @Bind(R.id.rl_main)
    public RelativeLayout rlMain;
    @Bind(R.id.fl_loading)
    public FrameLayout flLoading;

    @Override
    protected int getRootLayoutId() {
        return R.layout.activity_news_detail;
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
}
