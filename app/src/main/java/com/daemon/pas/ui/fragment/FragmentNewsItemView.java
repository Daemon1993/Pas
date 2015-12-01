package com.daemon.pas.ui.fragment;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.daemon.mvp.view.AppView;
import com.daemon.pas.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Daemon on 2015/11/26.
 */
public class FragmentNewsItemView extends AppView {

    @Bind(R.id.rlv_news)
    public RecyclerView rlvNews;
    @Bind(R.id.pf_refresh)
    public PtrFrameLayout pfRefresh;
    @Bind(R.id.iv_2top)
    public ImageView iv2top;
    @Bind(R.id.tv_noData)
    public TextView tvNoData;


    @Override
    protected int getRootLayoutId() {
        return R.layout.fragment_news_item;
    }

    @Override
    public void destory() {
        super.destory();
        ButterKnife.unbind(this);
    }

    @Override
    public void initWeidget() {
        ButterKnife.bind(this, getRootView());

    }

}
