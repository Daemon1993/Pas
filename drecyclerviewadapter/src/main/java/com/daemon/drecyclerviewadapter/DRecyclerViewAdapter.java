package com.daemon.drecyclerviewadapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 实现AddHead AddFoot 下滑加载更多的RecyclerView
 * Created by Daemon on 2015/11/10.
 */
public class DRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER_VIEW = Integer.MIN_VALUE;
    private static final int TYPE_FOOTER_VIEW = Integer.MIN_VALUE + 100;

    private RecyclerView.Adapter<RecyclerView.ViewHolder> mInnerAdapter;

    private ArrayList<View> mHeaderViews = new ArrayList<>();
    private ArrayList<View> mFooterViews = new ArrayList<>();


    /**
     * 在不改变原有的Adapter的基础上 做添加头 尾部 等等一些处理
     */
    public DRecyclerViewAdapter() {

    }

    public DRecyclerViewAdapter(DBaseRecyclerViewAdapter adapter) {
        setAdapter(adapter);
    }

    public void setAdapter(DBaseRecyclerViewAdapter myAdapter) {
        this.mInnerAdapter = myAdapter;
        myAdapter.setmDRecyclerViewAdapter(this);
    }

    public RecyclerView.Adapter getInnerAdapter() {
        return mInnerAdapter;
    }

    /**
     * addHead to RecyclerView
     *
     * @param header
     */
    public void addHeaderView(View header) {
        if (header == null) {
            throw new IllegalArgumentException("header is null");
        }
        mHeaderViews.add(header);
    }

    /**
     * addFoot to RecyclerView
     *
     * @param footer
     */
    public void addFooterView(View footer) {

        if (footer == null) {
            throw new IllegalArgumentException("footer is null");
        }

        mFooterViews.add(footer);
    }


    /**
     * 返回第一个FoView
     *
     * @return
     */
    public View getFooterView() {
        return getFooterViewsCount() > 0 ? mFooterViews.get(0) : null;
    }

    /**
     * 返回第一个HeaderView
     *
     * @return
     */
    public View getHeaderView() {
        return getHeaderViewsCount() > 0 ? mHeaderViews.get(0) : null;
    }

    public void removeHeaderView(View view) {
        mHeaderViews.remove(view);
    }

    public void removeFooterView(View view) {
        mFooterViews.remove(view);
    }

    public boolean isHeader(int position) {
        return getHeaderViewsCount() > 0 && position < getHeaderViewsCount();
    }

    public boolean isTopHeader(int position) {
        return getHeaderViewsCount() > 0 && position == 0;
    }


    public boolean isFooter(int position) {
        int lastPosition = getItemCount() - 1;
        return getFooterViewsCount() > 0 && position > lastPosition - getFooterViewsCount() && position <= lastPosition;
    }

    public boolean isLastFooter(int position) {
        int lastPosition = getItemCount() - 1;
        return getFooterViewsCount() > 0 && position == lastPosition;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int headerViewsCountCount = getHeaderViewsCount();

        if (viewType < TYPE_HEADER_VIEW + headerViewsCountCount) {
            return new DViewHolder(mHeaderViews.get(viewType - TYPE_HEADER_VIEW));
        } else if (viewType >= TYPE_FOOTER_VIEW && viewType < Integer.MAX_VALUE / 2) {
            return new DViewHolder(mFooterViews.get(viewType - TYPE_FOOTER_VIEW));
        } else {
            return mInnerAdapter.onCreateViewHolder(parent, viewType - Integer.MAX_VALUE / 2);
        }

    }


    @Override
    public int getItemViewType(int position) {

        int innerCount = mInnerAdapter.getItemCount();
        int headerViewsCountCount = getHeaderViewsCount();

        if (position < headerViewsCountCount) {
            return TYPE_HEADER_VIEW + position;
        } else if (headerViewsCountCount <= position && position < headerViewsCountCount + innerCount) {

            int innerItemViewType = mInnerAdapter.getItemViewType(position - headerViewsCountCount);
            if (innerItemViewType >= Integer.MAX_VALUE / 2) {
                throw new IllegalArgumentException("your adapter's return value of getViewTypeCount() must < Integer.MAX_VALUE / 2");
            }
            return innerItemViewType + Integer.MAX_VALUE / 2;

        } else {
            return TYPE_FOOTER_VIEW + position - headerViewsCountCount - innerCount;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int headerViewsCountCount = getHeaderViewsCount();

        //是数据item 就调用原本的
        if (position >= headerViewsCountCount && position < headerViewsCountCount + mInnerAdapter.getItemCount()) {
            mInnerAdapter.onBindViewHolder(holder, position - headerViewsCountCount);
        } else {
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
                ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
            }
        }

    }

    @Override
    public int getItemCount() {
        return getHeaderViewsCount() + getFooterViewsCount() + mInnerAdapter.getItemCount();
    }


    public int getHeaderViewsCount() {
        return mHeaderViews.size();
    }

    public int getFooterViewsCount() {
        return mFooterViews.size();
    }


    public static class DViewHolder extends RecyclerView.ViewHolder {

        public DViewHolder(View itemView) {
            super(itemView);
        }
    }


}

