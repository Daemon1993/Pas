package com.daemon.framework.drecyclerviewadapter;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Daemon on 2015/11/12.
 */
public class DBaseRecyclerViewHolder1<M> extends RecyclerView.ViewHolder {

    public DRecyclerViewAdapter mDRecyclerViewAdapter;
    public DBaseRecyclerViewAdapter1<M> mDBaseRecyclerViewAdapter;

    DBaseRecyclerViewAdapter1.BaseListener<M> mBaseListener;

    public DBaseRecyclerViewHolder1(ViewGroup parent, @LayoutRes int res, DBaseRecyclerViewAdapter1<M> mDBaseRecyclerViewAdapter) {
        super(LayoutInflater.from(parent.getContext()).inflate(res, parent, false));
        this.mDRecyclerViewAdapter = mDBaseRecyclerViewAdapter.getmDRecyclerViewAdapter();
        this.mDBaseRecyclerViewAdapter = mDBaseRecyclerViewAdapter;
        mBaseListener = (DBaseRecyclerViewAdapter1.BaseListener<M>) mDBaseRecyclerViewAdapter.getBaseListener();

    }

    protected <T extends View> T $(@IdRes int id) {
        return (T) itemView.findViewById(id);
    }

    public void setData(M data) {
        mBaseListener.setData(data);
    }

    /**
     * 获取点击的item的position
     * @return
     */
    public int getAdapterItemPosition() {
        int oldPosition =getAdapterPosition();

        if(mDRecyclerViewAdapter==null){
            return oldPosition;
        }

        if (mDRecyclerViewAdapter.isHeader(oldPosition) || mDRecyclerViewAdapter.isFooter(oldPosition)) {
            return -1;
        } else {
            return oldPosition - mDRecyclerViewAdapter.getHeaderViewsCount();
        }
    }
}
