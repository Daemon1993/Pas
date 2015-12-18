package com.daemon.pas.presenter.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.daemon.framework.dproxyutil.image.ImageProxyUtils;
import com.daemon.framework.drecyclerviewadapter.DBaseRecyclerViewAdapter;
import com.daemon.framework.drecyclerviewadapter.DBaseRecyclerViewHolder;
import com.daemon.pas.MyApplication;
import com.daemon.pas.R;
import com.daemon.pas.model.PicData;

import java.util.List;

/**
 * Created by Daemon on 2015/12/14.
 */
public class PicAdapter extends DBaseRecyclerViewAdapter<PicData.ResEntity.WallpaperEntity> {
    public PicAdapter(List<PicData.ResEntity.WallpaperEntity> mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public DBaseRecyclerViewHolder onCreateViewHolder1(ViewGroup parent, int viewType, DBaseRecyclerViewAdapter dBaseRecyclerViewAdapter) {

        return new PicViewHolder(parent, R.layout.item_pic_show, this);

    }
}

class PicViewHolder extends DBaseRecyclerViewHolder<PicData.ResEntity.WallpaperEntity> implements View.OnClickListener {

    private int h;
    private int w;
    private ImageView ic_show;

    public PicViewHolder(ViewGroup parent, @LayoutRes int res, DBaseRecyclerViewAdapter mDBaseRecyclerViewAdapter) {
        super(parent, res, mDBaseRecyclerViewAdapter);

        ic_show = $(R.id.iv_show);

        w = (MyApplication.screen_width) / 3;
        h = w;

        itemView.setOnClickListener(this);
    }


    @Override
    public void setData(PicData.ResEntity.WallpaperEntity data) {
        super.setData(data);


        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(w, h);
        ic_show.setLayoutParams(params);

        ImageProxyUtils.getImageProxyUtils().loadImage(itemView.getContext(), data.getPreview(), ic_show);

    }

    @Override
    public void onClick(View v) {
        mDBaseRecyclerViewAdapter.getmItemOnClickListener().onclick(getAdapterItemPosition());
    }
}
