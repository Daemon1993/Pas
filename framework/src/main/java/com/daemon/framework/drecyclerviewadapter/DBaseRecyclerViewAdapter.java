package com.daemon.framework.drecyclerviewadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Daemon on 2015/11/12.
 */
public abstract class DBaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<DBaseRecyclerViewHolder> {

    private List<T> mDatas;

    private Context mContext;

    private DRecyclerViewAdapter mDRecyclerViewAdapter;

    public DRecyclerViewAdapter getmDRecyclerViewAdapter() {
        return mDRecyclerViewAdapter;
    }

    public void setmDRecyclerViewAdapter(DRecyclerViewAdapter mDRecyclerViewAdapter) {
        this.mDRecyclerViewAdapter = mDRecyclerViewAdapter;
    }


    public DBaseRecyclerViewAdapter(List<T> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
    }

    @Override
    public DBaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateViewHolder1(parent, viewType,this);
    }

    @Override
    public void onBindViewHolder(DBaseRecyclerViewHolder holder, int position) {
       holder.setData(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public abstract DBaseRecyclerViewHolder onCreateViewHolder1(ViewGroup parent, int viewType,DBaseRecyclerViewAdapter dBaseRecyclerViewAdapter);

}
