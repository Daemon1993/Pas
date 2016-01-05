package com.daemon.pas.ui.activity;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.daemon.mvp.view.AppView;
import com.daemon.pas.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Daemon on 2016/1/5.
 */
public class AboutActivityView extends AppView {
    @Bind(R.id.toolbar)
    public Toolbar toolbar;
    @Bind(R.id.tv_link)
    TextView tvLink;
    @Bind(R.id.tv_version)
    TextView tvVersion;
    @Bind(R.id.tv_home)
    TextView tvHome;

    @Override
    protected int getRootLayoutId() {
        return R.layout.activity_about;
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

    public String getLinkUrl() {
        return tvLink.getText().toString().trim();
    }

    public void setVersionName(String vversionName) {
        tvVersion.setText(vversionName);
    }

    public String getLinkHome() {
        return tvHome.getText().toString().trim();
    }
}
