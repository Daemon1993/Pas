package com.daemon.pas.presenter.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daemon.framework.dutils.dproxyutil.image.ImageProxyUtils;
import com.daemon.framework.drecyclerviewadapter.DBaseRecyclerViewAdapter;
import com.daemon.framework.drecyclerviewadapter.DBaseRecyclerViewHolder;
import com.daemon.pas.R;
import com.daemon.pas.model.NewsItemData;

import java.util.List;

/**
 * Created by Daemon on 2015/11/26.
 */
public class FragmentNewsItemAdapter extends DBaseRecyclerViewAdapter<NewsItemData.NewslistEntity> {

    private List<NewsItemData.NewslistEntity> mDatas;
    private Context mContext;
    private RecyclerViewListener mRecyclerViewListener;


    public void setRecyclerViewListener(RecyclerViewListener recyclerViewListener){
        mRecyclerViewListener=recyclerViewListener;
    }


    public FragmentNewsItemAdapter(List<NewsItemData.NewslistEntity> mDatas, Context mContext) {
        super(mDatas, mContext);
        this.mDatas=mDatas;
        this.mContext=mContext;
    }

    @Override
    public DBaseRecyclerViewHolder onCreateViewHolder1(ViewGroup parent, int viewType, DBaseRecyclerViewAdapter dBaseRecyclerViewAdapter) {
        FragmentNewsItemViewHolder fragmentNewsItemViewHolder=new FragmentNewsItemViewHolder(parent, R.layout.item_news,this);
        fragmentNewsItemViewHolder.setRecyclerViewListener(mRecyclerViewListener);
        return fragmentNewsItemViewHolder;
    }

}

class FragmentNewsItemViewHolder extends DBaseRecyclerViewHolder<NewsItemData.NewslistEntity> implements View.OnClickListener {

    private TextView tv_abstract;
    private TextView tv_title;
    private TextView tv_source;
    private TextView tv_time;
    private ImageView iv_img;

    private Context mContext;
    private RecyclerViewListener mRecyclerViewListener;

    public void setRecyclerViewListener(RecyclerViewListener recyclerViewListener){
        mRecyclerViewListener=recyclerViewListener;
    }

    public FragmentNewsItemViewHolder(ViewGroup parent, @LayoutRes int res, DBaseRecyclerViewAdapter mDBaseRecyclerViewAdapter) {
        super(parent, res, mDBaseRecyclerViewAdapter);

        mContext=parent.getContext();

        tv_title=$(R.id.tv_title);
        tv_source=$(R.id.tv_source);
        tv_time=$(R.id.tv_time);
        tv_abstract=$(R.id.tv_abstract);
        iv_img=$(R.id.iv_img);

        itemView.setOnClickListener(this);
    }

    @Override
    public void setData(NewsItemData.NewslistEntity data) {
        super.setData(data);

        tv_title.setText(data.getTitle());
        tv_abstract.setText(data.getAbstractX());
        tv_source.setText(data.getSource());
        tv_time.setText(data.getTime());

        ImageProxyUtils.getImageProxyUtils().loadImage(mContext,data.getThumbnails().get(0),iv_img);

    }

    @Override
    public void onClick(View v) {
        mRecyclerViewListener.onClickItem(getAdapterItemPosition());
    }
}