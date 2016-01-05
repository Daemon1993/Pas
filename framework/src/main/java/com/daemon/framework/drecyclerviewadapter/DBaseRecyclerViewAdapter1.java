package com.daemon.framework.drecyclerviewadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Daemon on 2015/11/12.
 */
public abstract class DBaseRecyclerViewAdapter1<T> extends RecyclerView.Adapter<DBaseRecyclerViewHolder1> implements View.OnClickListener {

    @Override
    public void onClick(View v) {
        baseListener.onclick(mDbaeDBaseRecyclerViewHolder.getAdapterItemPosition());
    }



    public interface BaseListener<T> {
        void onclick(int index);

        void setData(T data);
    }

    BaseListener<T> baseListener;

    public BaseListener<T> getBaseListener() {
        return baseListener;
    }

    public void setBaseListener(BaseListener<T> baseListener) {
        this.baseListener = baseListener;
    }

    public DBaseRecyclerViewHolder1 mDbaeDBaseRecyclerViewHolder;

    private List<T> mDatas;

    private Context mContext;

    private DRecyclerViewAdapter mDRecyclerViewAdapter;

    public DRecyclerViewAdapter getmDRecyclerViewAdapter() {
        return mDRecyclerViewAdapter;
    }

    public void setmDRecyclerViewAdapter(DRecyclerViewAdapter mDRecyclerViewAdapter) {
        this.mDRecyclerViewAdapter = mDRecyclerViewAdapter;
    }


    public DBaseRecyclerViewAdapter1(List<T> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
    }

    @Override
    public DBaseRecyclerViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        mDbaeDBaseRecyclerViewHolder = new DBaseRecyclerViewHolder1(parent, viewType, this);
        mDbaeDBaseRecyclerViewHolder.itemView.setOnClickListener(this);
        return mDbaeDBaseRecyclerViewHolder;

    }

    @Override
    public void onBindViewHolder(DBaseRecyclerViewHolder1 holder, int position) {
        holder.setData(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


}

