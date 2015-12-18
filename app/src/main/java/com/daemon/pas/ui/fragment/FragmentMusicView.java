package com.daemon.pas.ui.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.daemon.framework.drecyclerviewadapter.DRecyclerViewAdapter;
import com.daemon.mvp.view.AppView;
import com.daemon.pas.R;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Daemon on 2015/11/25.
 */
public class FragmentMusicView extends AppView {
    @Bind(R.id.rlv_music)
    RecyclerView rlvMusic;

    @Override
    protected int getRootLayoutId() {
        return R.layout.fragment_music;
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

    public void setRecyclerViewInit(LinearLayoutManager linearLayoutManager,
                                    HorizontalDividerItemDecoration horizontalDividerItemDecoration,
                                    DRecyclerViewAdapter dRecyclerViewAdapter) {

        rlvMusic.setAdapter(dRecyclerViewAdapter);
        rlvMusic.setLayoutManager(linearLayoutManager);
        rlvMusic.addItemDecoration(horizontalDividerItemDecoration);

    }
}
