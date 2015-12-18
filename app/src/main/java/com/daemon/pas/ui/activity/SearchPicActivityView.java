package com.daemon.pas.ui.activity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.daemon.framework.drecyclerviewadapter.DRecyclerViewAdapter;
import com.daemon.framework.drecyclerviewadapter.DRecyclerViewScrollListener;
import com.daemon.mvp.view.AppView;
import com.daemon.pas.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Daemon on 2015/12/10.
 */
public class SearchPicActivityView extends AppView {
    @Bind(R.id.toolbar)
    public Toolbar toolbar;
    @Bind(R.id.et_search)
    EditText etSearch;
    @Bind(R.id.rl_search)
    RelativeLayout rlSearch;
    @Bind(R.id.iv_search)
    ImageView ivSearch;
    @Bind(R.id.rlv_pics)
    RecyclerView rlvPics;
    @Bind(R.id.ll_serach)
    LinearLayout llSerach;

    @Override
    protected int getRootLayoutId() {
        return R.layout.activity_search_pic;
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

    public void setEditextKey(String key) {
        etSearch.setText(key);
    }

    public String getEdittextKey() {


        return etSearch.getText().toString().trim();
    }

    public void setRecyclerViewInit(DRecyclerViewAdapter dRecyclerViewAdapter,
                                    GridLayoutManager dStaggeredGridLayoutManager) {

        rlvPics.setLayoutManager(dStaggeredGridLayoutManager);
        rlvPics.setAdapter(dRecyclerViewAdapter);


        rlvPics.setItemAnimator(new DefaultItemAnimator());

    }

    public void addLoadMoreListener(DRecyclerViewScrollListener dRecyclerViewScrollListener) {
        rlvPics.addOnScrollListener(dRecyclerViewScrollListener);
    }

    public void scrollToTop() {
        rlvPics.scrollToPosition(0);
    }

    public void setSearchGone() {
        llSerach.setVisibility(View.GONE);
    }
}
