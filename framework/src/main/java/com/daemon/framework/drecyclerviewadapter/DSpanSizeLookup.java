package com.daemon.framework.drecyclerviewadapter;

import android.support.v7.widget.GridLayoutManager;

/**
 * Created by Daemon on 2015/11/10.
 */
public class DSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private DRecyclerViewAdapter adapter;
    private int mSpanSize = 1;

    public DSpanSizeLookup(DRecyclerViewAdapter adapter, int spanSize) {
        this.adapter = adapter;
        this.mSpanSize = spanSize;
    }

    @Override
    public int getSpanSize(int position) {
        boolean isHeaderOrFooter = adapter.isHeader(position) || adapter.isFooter(position);
        return isHeaderOrFooter ? mSpanSize : 1;
    }
}