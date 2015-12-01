package com.daemon.drecyclerviewadapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by Daemon on 2015/11/10.
 */
public class DStaggeredGridLayoutManager  extends StaggeredGridLayoutManager {

    private final String TAG = getClass().getSimpleName();

    GridLayoutManager.SpanSizeLookup mSpanSizeLookup;

    public DStaggeredGridLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }

    public GridLayoutManager.SpanSizeLookup getSpanSizeLookup() {
        return mSpanSizeLookup;
    }

    public void setSpanSizeLookup(GridLayoutManager.SpanSizeLookup spanSizeLookup) {
        mSpanSizeLookup = spanSizeLookup;

    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        for (int i = 0; i < getItemCount(); i++) {
            if (mSpanSizeLookup.getSpanSize(i) > 1) {
                try {
                    View view = recycler.getViewForPosition(i);
                    if (view != null) {
                        StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
                        lp.setFullSpan(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        super.onMeasure(recycler, state, widthSpec, heightSpec);
    }
}