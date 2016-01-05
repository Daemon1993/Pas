package com.daemon.pas.ui.activity;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.FrameLayout;

import com.daemon.mvp.view.AppView;
import com.daemon.pas.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Daemon on 2015/11/25.
 */
public class MainActivityView extends AppView {

    @Bind(R.id.toolbar)
    public Toolbar toolbar;

    @Bind(R.id.bt_news)
    Button btNews;
    @Bind(R.id.bt_music)
    Button btMusic;
    @Bind(R.id.bt_pic)
    Button btPic;

    @Bind(R.id.drawer_layout)
    public DrawerLayout drawerLayout;

    @Bind(R.id.frame_content)
    public FrameLayout frameContent;

    @Override
    public int getRootLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initWeidget() {

        ButterKnife.bind(this, getRootView());

    }

    @Override
    public void destory(){
        super.destory();
        ButterKnife.unbind(this);

    }


    public void invalidateOptionsMenu() {
        toolbar.invalidate();
    }

    public void setToolBarBgColor(int i) {
        switch (i){
            case 0:
                toolbar.setBackgroundColor(getRootView().getResources().getColor(R.color.colorAccent));
                break;
            case 1:
                toolbar.setBackgroundColor(getRootView().getResources().getColor(R.color.colorPrimary));
                break;
        }
    }
}
