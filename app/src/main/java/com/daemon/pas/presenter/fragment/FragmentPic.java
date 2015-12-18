package com.daemon.pas.presenter.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.daemon.framework.dcustomview.DividerGridItemDecoration;
import com.daemon.framework.okhttp.OkHttpUtil;
import com.daemon.mvp.presenter.FragmentPresenter;
import com.daemon.pas.common.API;
import com.daemon.pas.model.PicTypeData;
import com.daemon.pas.presenter.MainAFInterface;
import com.daemon.pas.presenter.activity.MainActivity;
import com.daemon.pas.presenter.activity.PicTypeDetailActivity;
import com.daemon.pas.presenter.adapter.FragmentPicAdapter;
import com.daemon.pas.ui.fragment.FragmentPicView;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daemon on 2015/11/25.
 */
public class FragmentPic extends FragmentPresenter<FragmentPicView> {


    public static final String Title = "美图";

    private MainAFInterface mListener;
    private List<PicTypeData.ResEntity.CategoryEntity> lists;
    private FragmentPicAdapter fragmentPicAdapter;


    @Override
    protected Class<FragmentPicView> getIViewClass() {
        return FragmentPicView.class;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mListener = (MainAFInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement MainAFInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void bindEvenListener() {
        super.bindEvenListener();

        initView();
    }

    private void initView() {


        lists = new ArrayList<PicTypeData.ResEntity.CategoryEntity>();

        getData();

    }

    private void getData() {
        Request request = new Request.Builder()
                .url(API.Base_Url_Pic+ API.Pic_Type)
                .tag(this)
                .get()
                .build();

        mListener.showLoading();

        OkHttpUtil.getInstance().getData4Server(request, new OkHttpUtil.MyCallBack() {
            @Override
            public void onFailure(Request request, IOException e) {
                mListener.hiheLoading();
            }

            @Override
            public void onResponse(String json) {
                mListener.hiheLoading();

                PicTypeData picTypeData = OkHttpUtil.getInstance().getGson().fromJson(json, PicTypeData.class);

                lists.addAll(picTypeData.getRes().getCategory());

                fragmentPicAdapter = new FragmentPicAdapter(lists, getContext());

                DividerGridItemDecoration dividerGridItemDecoration=new DividerGridItemDecoration(getContext());

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);

                iView.setRecyclerViewInit(fragmentPicAdapter, gridLayoutManager, dividerGridItemDecoration);

                fragmentPicAdapter.setOnClickItemListener(new FragmentPicAdapter.OnClickItemListener() {
                    @Override
                    public void onClickItem(int index) {
                        Bundle bundle=new Bundle();
                        bundle.putString(PicTypeDetailActivity.TYPE,lists.get(index).getId());
                        bundle.putString(PicTypeDetailActivity.TYPE_NAME,lists.get(index).getName());
                        PicTypeDetailActivity.openActivity(getActivity(),bundle);
                    }
                });

            }
        });


    }
}
