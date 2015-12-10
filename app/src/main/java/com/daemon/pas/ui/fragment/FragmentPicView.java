package com.daemon.pas.ui.fragment;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.daemon.framework.dcustomview.DividerGridItemDecoration;
import com.daemon.mvp.view.AppView;
import com.daemon.pas.R;
import com.daemon.pas.presenter.adapter.FragmentPicAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Daemon on 2015/11/25.
 */
public class FragmentPicView extends AppView {

    @Bind(R.id.rlv_show)
    RecyclerView rlvShow;


    @Override
    protected int getRootLayoutId() {
        return R.layout.fragment_pic;
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


    public void setRecyclerViewInit(FragmentPicAdapter dRecyclerViewAdapter, GridLayoutManager gridLayoutManager,
                                    DividerGridItemDecoration dividerGridItemDecoration) {

        rlvShow.setLayoutManager(gridLayoutManager);
        rlvShow.setAdapter(dRecyclerViewAdapter);
        rlvShow.setItemAnimator(new DefaultItemAnimator());


        //rlvShow.addItemDecoration(dividerGridItemDecoration);

    }
}
