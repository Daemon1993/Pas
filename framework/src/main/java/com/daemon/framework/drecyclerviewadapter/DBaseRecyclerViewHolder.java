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
public class DBaseRecyclerViewHolder<M> extends RecyclerView.ViewHolder {

    public DRecyclerViewAdapter mDRecyclerViewAdapter;
    public DBaseRecyclerViewAdapter mDBaseRecyclerViewAdapter;



    public DBaseRecyclerViewHolder(View itemView, DBaseRecyclerViewAdapter mDBaseRecyclerViewAdapter) {
        super(itemView);
        this.mDRecyclerViewAdapter = mDBaseRecyclerViewAdapter.getmDRecyclerViewAdapter();
        this.mDBaseRecyclerViewAdapter = mDBaseRecyclerViewAdapter;
    }

    public DBaseRecyclerViewHolder(ViewGroup parent, @LayoutRes int res, DBaseRecyclerViewAdapter mDBaseRecyclerViewAdapter) {
        super(LayoutInflater.from(parent.getContext()).inflate(res, parent, false));
        this.mDRecyclerViewAdapter = mDBaseRecyclerViewAdapter.getmDRecyclerViewAdapter();
        this.mDBaseRecyclerViewAdapter = mDBaseRecyclerViewAdapter;
    }

    protected <T extends View> T $(@IdRes int id) {
        return (T) itemView.findViewById(id);
    }

    public void setData(M data) {

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
