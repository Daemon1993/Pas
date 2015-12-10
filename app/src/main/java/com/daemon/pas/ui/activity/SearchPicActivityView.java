package com.daemon.pas.ui.activity;

import com.daemon.mvp.view.AppView;
import com.daemon.pas.R;

import butterknife.ButterKnife;

/**
 * Created by Daemon on 2015/12/10.
 */
public class SearchPicActivityView extends AppView {
    @Override
    protected int getRootLayoutId() {
        return R.layout.activity_search_pic;
    }

    @Override
    public void initWeidget() {
        ButterKnife.bind(this,getRootView());
    }


    @Override
    public void destory() {
        super.destory();
        ButterKnife.unbind(this);
    }
}
