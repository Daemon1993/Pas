package com.daemon.pas.presenter.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daemon.drecyclerviewadapter.DRecyclerViewAdapter;
import com.daemon.drecyclerviewadapter.DRecyclerViewScrollListener;
import com.daemon.mvp.presenter.FragmentPresenter;
import com.daemon.pas.MyApplication;
import com.daemon.pas.R;
import com.daemon.pas.common.API;
import com.daemon.pas.common.BaseRequest;
import com.daemon.pas.model.NewsItemData;
import com.daemon.pas.okhttp.OkHttpUtil;
import com.daemon.pas.presenter.MainAFInterface;
import com.daemon.pas.presenter.activity.MainActivity;
import com.daemon.pas.presenter.activity.NewsDetailActivity;
import com.daemon.pas.presenter.adapter.FragmentNewsItemAdapter;
import com.daemon.pas.presenter.adapter.RecyclerViewListener;
import com.daemon.pas.ui.fragment.FragmentNewsItemView;
import com.daemon.pas.utils.DensityUtil;
import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;
import com.socks.library.KLog;
import com.squareup.leakcanary.RefWatcher;
import com.squareup.okhttp.Request;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.MaterialHeader;


/**
 * Created by Daemon on 2015/11/26.
 */
public class FragmentNewsItem extends FragmentPresenter<FragmentNewsItemView> implements PtrHandler, View.OnClickListener, RecyclerViewListener {

    public static final String CHANNELID = "channelId";
    public static final String CHANNELNAME = "channelName";
    public String id;
    public String name;

    TextView tvMsg;

    ProgressBar pbLoading;

    private MainAFInterface mListener;
    private LinearLayoutManager linearLayoutManager;
    public FragmentNewsItemAdapter fragmentNewsItemAdapter;
    private DRecyclerViewAdapter dRecyclerViewAdapter;
    List<NewsItemData.NewslistEntity> newslist;
    private int page;
    private boolean isOver;
    private View footView;
    private boolean isRefresh = false;


    private FragmentNewsItem instance;

    private NewsItemData old_data;

    private String old_json;
    private DB snappydb;


    @Override
    public Class<FragmentNewsItemView> getIViewClass() {
        return FragmentNewsItemView.class;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mListener = (MainAFInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void bindEvenListener() {
        super.bindEvenListener();

        initView();
    }

    private void initView() {

        instance = this;
        id = getArguments().getString(CHANNELID);
        name = getArguments().getString(CHANNELNAME);

        if (TextUtils.isEmpty(id) || TextUtils.isEmpty(name)) {
            return;
        }

        try {
            snappydb = DBFactory.open(getActivity());
            old_data = snappydb.getObject(id, NewsItemData.class);

        } catch (SnappydbException e) {
            e.printStackTrace();
        }

        initRecycleView();

        if(old_data==null) {
            getData();
        }else{
            setData(old_data);
        }

    }


    /**
     * 初始化  RecyClerView
     */
    private void initRecycleView() {
        page = 1;
        newslist = new ArrayList<NewsItemData.NewslistEntity>();
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        iView.rlvNews.setLayoutManager(linearLayoutManager);

        iView.rlvNews.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .sizeResId(R.dimen.divider)
                .color(getResources().getColor(R.color.colorLine)).build());

        fragmentNewsItemAdapter = new FragmentNewsItemAdapter(newslist, getActivity());
        fragmentNewsItemAdapter.setRecyclerViewListener(this);

        dRecyclerViewAdapter = new DRecyclerViewAdapter(fragmentNewsItemAdapter);
        dRecyclerViewAdapter.setAdapter(fragmentNewsItemAdapter);


        footView = getActivity().getLayoutInflater().inflate(R.layout.item_foot, null, false);
        tvMsg = (TextView) footView.findViewById(R.id.tv_msg);
        pbLoading = (ProgressBar) footView.findViewById(R.id.pb_loading);

        dRecyclerViewAdapter.addFooterView(footView);

        iView.rlvNews.setLayoutManager(linearLayoutManager);
        iView.rlvNews.setAdapter(dRecyclerViewAdapter);


        //刚进来 不显示 只有下滑了才显示
        footView.setVisibility(View.GONE);

        final MaterialHeader header = new MaterialHeader(getContext());
        int[] colors = getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, DensityUtil.dip2px(getContext(), 15), 0, DensityUtil.dip2px(getContext(), 10));
        header.setPtrFrameLayout(iView.pfRefresh);

        iView.pfRefresh.setLoadingMinTime(1000);
        iView.pfRefresh.setDurationToCloseHeader(1500);
        iView.pfRefresh.setHeaderView(header);
        iView.pfRefresh.addPtrUIHandler(header);

        iView.pfRefresh.setPtrHandler(this);

        iView.rlvNews.addOnScrollListener(new DRecyclerViewScrollListener() {
            @Override
            public void onLoadNextPage(RecyclerView view) {
                footView.setVisibility(View.VISIBLE);
                if (isOver) {
                    return;
                }
                page++;
                getData();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (page > 1) {
                    iView.iv2top.setVisibility(View.VISIBLE);
                } else {
                    iView.iv2top.setVisibility(View.GONE);
                }
            }
        });

        iView.setOnClickListener(this, R.id.iv_2top);
        iView.iv2top.setOnClickListener(this);
    }

    /**
     * 获取数据
     */
    private void getData() {

        Long last_time = System.currentTimeMillis();

        String url_aruments = "&last_time=" + last_time + "&chlid=" + id + "&page=" + page + "&apptype=android";
//
        String url = API.Get_Nes_List + url_aruments;
        if ("快报".equals(name)) {
            url = API.Get_Nes_KB + url_aruments;
        }

        KLog.e("Daemon " + url);

        Request request = new Request.Builder()
                .url(url)
                .addHeader(BaseRequest.APIKEY, BaseRequest.APIKEY_VALUE)
                .get()
                .tag(this)
                .build();

        if (page == 1 && !isRefresh) {
            mListener.showLoading();
        }

        OkHttpUtil.getInstance().getData4Server(request, new OkHttpUtil.MyCallBack() {

            @Override
            public void onFailure(Request request, IOException e) {
                mListener.hiheLoading();
                iView.pfRefresh.refreshComplete();
            }

            @Override
            public void onResponse(String json) {
                mListener.hiheLoading();
                iView.pfRefresh.refreshComplete();

                NewsItemData newsItemData = OkHttpUtil.getInstance().getGson().fromJson(json, NewsItemData.class);

                setData(newsItemData);

                try {
                    snappydb.put(id, newsItemData);
                    snappydb.close();
                } catch (SnappydbException e) {
                    e.printStackTrace();
                }

            }
        });

    }


    /**
     * 更新数据
     *
     * @param
     */
    private void setData(NewsItemData newsItemData) {

        if (newsItemData != null && newsItemData.getNewslist() != null) {
            iView.tvNoData.setVisibility(View.GONE);

            int old = newslist.size();
            newslist.addAll(newsItemData.getNewslist());
            dRecyclerViewAdapter.notifyItemRangeInserted(old, newslist.size() - old);

            if (page == 1) {
                iView.rlvNews.scrollToPosition(0);
            }

        } else {
            if (page == 1) {
                //无数据 显示默认背景？
                iView.tvNoData.setVisibility(View.VISIBLE);
            }
            if (page > 1) {
                tvMsg.setText(getResources().getString(R.string.loadingOver));
                pbLoading.setVisibility(View.GONE);
                isOver = true;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            snappydb.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }

        RefWatcher refWatcher = MyApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }

    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {

        return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        page = 1;
        newslist.clear();
        dRecyclerViewAdapter.notifyDataSetChanged();

        isRefresh = true;
        getData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_2top:

                iView.rlvNews.scrollToPosition(0);

                break;
        }
    }


    @Override
    public void onClickItem(int adapterItemPosition) {
        Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
        intent.putExtra(NewsDetailActivity.URL, newslist.get(adapterItemPosition).getUrl());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getActivity().startActivity(intent);

    }

}
