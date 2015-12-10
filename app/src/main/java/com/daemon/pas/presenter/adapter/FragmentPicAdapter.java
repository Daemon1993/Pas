package com.daemon.pas.presenter.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daemon.framework.dproxyutil.image.ImageProxyUtils;
import com.daemon.framework.drecyclerviewadapter.DBaseRecyclerViewAdapter;
import com.daemon.framework.drecyclerviewadapter.DBaseRecyclerViewHolder;
import com.daemon.pas.MyApplication;
import com.daemon.pas.R;
import com.daemon.pas.model.PicTypeData;
import com.daemon.pas.utils.DensityUtil;

import java.util.List;

/**
 * Created by Daemon on 2015/12/9.
 */
public class FragmentPicAdapter extends DBaseRecyclerViewAdapter<PicTypeData.ResEntity.CategoryEntity> {


    public FragmentPicAdapter(List<PicTypeData.ResEntity.CategoryEntity> mDatas, Context mContext) {
        super(mDatas, mContext);
    }

    @Override
    public DBaseRecyclerViewHolder onCreateViewHolder1(ViewGroup parent, int viewType, DBaseRecyclerViewAdapter dBaseRecyclerViewAdapter) {
        FragmentPicViewHolder fragmentPicViewHolder = new FragmentPicViewHolder(parent, R.layout.item_pic_type, dBaseRecyclerViewAdapter);
        return fragmentPicViewHolder;
    }
}

class FragmentPicViewHolder extends DBaseRecyclerViewHolder<PicTypeData.ResEntity.CategoryEntity> {


    private  RelativeLayout.LayoutParams layoutParams;
    ImageView ivImg;

    TextView tvName;

    int w;
    int h;

    public FragmentPicViewHolder(ViewGroup parent, @LayoutRes int res, DBaseRecyclerViewAdapter mDBaseRecyclerViewAdapter) {
        super(parent, res, mDBaseRecyclerViewAdapter);

        w = (MyApplication.screen_width - 1 * DensityUtil.dip2px(parent.getContext(), 5))/2;
        h=w/2;

        //KLog.e("w "+w +"   h "+h);

        layoutParams = new RelativeLayout.LayoutParams(w,h);
    }

    @Override
    public void setData(PicTypeData.ResEntity.CategoryEntity data) {
        super.setData(data);

        ivImg = $(R.id.iv_img);
        tvName = $(R.id.tv_name);


        ivImg.setLayoutParams(layoutParams);

        ImageProxyUtils.getImageProxyUtils().loadImage(itemView.getContext(), data.getCover(), ivImg);

        tvName.setText(data.getName());
    }
}
