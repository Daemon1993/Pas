package com.daemon.pas.ui.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.daemon.framework.dpullrefresh_loadmore.DNormalRefreshViewHolder;
import com.daemon.framework.dpullrefresh_loadmore.DPullRefreshLayout;
import com.daemon.framework.drecyclerviewadapter.DRecyclerViewAdapter;
import com.daemon.mvp.view.AppView;
import com.daemon.pas.R;
import com.daemon.pas.presenter.fragment.FragmentNewsItem;
import com.yqritc.recyclerviewflexibledivider.FlexibleDividerDecoration;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Daemon on 2015/11/26.
 */
public class FragmentNewsItemView extends AppView {

    @Bind(R.id.rlv_news)
    public RecyclerView rlvNews;

    @Bind(R.id.iv_2top)
    public ImageView iv2top;
    @Bind(R.id.tv_noData)
    public TextView tvNoData;
    @Bind(R.id.drl_refresh_load)
    DPullRefreshLayout drlRefreshLoad;


    @Override
    protected int getRootLayoutId() {
        return R.layout.fragment_news_item;
    }

    @Override
    public void destory() {
        ButterKnife.unbind(this);
    }

    @Override
    public void initWeidget() {
        ButterKnife.bind(this, getRootView());
    }


    public void setRecyclerViewInit(LinearLayoutManager linearLayoutManager,
                                    FlexibleDividerDecoration flexibleDividerDecoration, DRecyclerViewAdapter dRecyclerViewAdapter) {

        rlvNews.setLayoutManager(linearLayoutManager);
        rlvNews.addItemDecoration(flexibleDividerDecoration);
        rlvNews.setAdapter(dRecyclerViewAdapter);

    }


    public void setDrlRefreshLoad(Context context, FragmentNewsItem fragmentNewsItem) {
        DNormalRefreshViewHolder dNormalRefreshViewHolder = new DNormalRefreshViewHolder(context, false);
        drlRefreshLoad.setRefreshViewHolder(dNormalRefreshViewHolder);

        drlRefreshLoad.setDelegate(fragmentNewsItem);

    }

    public void setRefreshComplete() {
        drlRefreshLoad.endRefreshing();
    }

    public void set2TopVisible(int visible) {
        iv2top.setVisibility(visible);
    }

    public void scrollToTop() {
        rlvNews.scrollToPosition(0);
    }

    public void setDataVisible(int visible) {
        tvNoData.setVisibility(visible);
    }
}
