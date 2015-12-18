package com.daemon.pas.presenter.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daemon.framework.drecyclerviewadapter.DBaseRecyclerViewAdapter;
import com.daemon.framework.drecyclerviewadapter.DBaseRecyclerViewHolder;
import com.daemon.pas.R;
import com.daemon.pas.model.MusicMainData;

import java.util.List;

/**
 * Created by Daemon on 2015/12/16.
 */
public class FragmentMusicMainAdapter extends DBaseRecyclerViewAdapter<MusicMainData.DataEntity.SongsEntity> {

    public FragmentMusicMainAdapter(List<MusicMainData.DataEntity.SongsEntity> mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public DBaseRecyclerViewHolder onCreateViewHolder1(ViewGroup parent, int viewType, DBaseRecyclerViewAdapter dBaseRecyclerViewAdapter) {
        return new FragmentMusicMainViewHolder(parent, R.layout.item_music_main_item,this);
    }
}

class FragmentMusicMainViewHolder extends DBaseRecyclerViewHolder<MusicMainData.DataEntity.SongsEntity>{

    private TextView tv_number;
    private TextView tv_title;
    private TextView tv_author;

    public FragmentMusicMainViewHolder(ViewGroup parent, @LayoutRes int res, DBaseRecyclerViewAdapter mDBaseRecyclerViewAdapter) {
        super(parent, res, mDBaseRecyclerViewAdapter);

        tv_number=$(R.id.tv_number);
        tv_title=$(R.id.tv_title);
        tv_author=$(R.id.tv_author);

    }

    @Override
    public void setData(MusicMainData.DataEntity.SongsEntity data) {
        super.setData(data);

        tv_number.setText(getAdapterItemPosition()+1+"");
        tv_title.setText(data.getName());
        tv_author.setText(data.getSingerName());

    }
}