package com.daemon.pas.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.daemon.mvp.view.AppView;
import com.daemon.pas.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Daemon on 2015/11/25.
 */
public class FragmentNewsView extends AppView {
    @Bind(R.id.tabnews)
    public TabLayout tabnews;
    @Bind(R.id.viewPager)
    public ViewPager viewPager;

    @Override
    protected int getRootLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void destory() {
        ButterKnife.unbind(this);
    }

    @Override
    public void initWeidget() {
        super.destory();
        ButterKnife.bind(this,getRootView());
//
//        tabnews.addTab(tabnews.newTab().setText("Tab 1"));
//        tabnews.addTab(tabnews.newTab().setText("Tab 2"));
//        tabnews.addTab(tabnews.newTab().setText("Tab 3"));

    }

}
