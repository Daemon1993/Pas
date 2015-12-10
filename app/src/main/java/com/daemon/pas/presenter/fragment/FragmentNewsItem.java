package com.daemon.pas.presenter.fragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daemon.framework.dpullrefresh_loadmore.DPullRefreshLayout;
import com.daemon.framework.drecyclerviewadapter.DRecyclerViewAdapter;
import com.daemon.framework.drecyclerviewadapter.DRecyclerViewScrollListener;
import com.daemon.mvp.presenter.FragmentPresenter;
import com.daemon.pas.R;
import com.daemon.pas.common.API;
import com.daemon.pas.model.NewsItemData;
import com.daemon.pas.okhttp.OkHttpUtil;
import com.daemon.pas.presenter.MainAFInterface;
import com.daemon.pas.presenter.activity.MainActivity;
import com.daemon.pas.presenter.activity.NewsDetailActivity;
import com.daemon.pas.presenter.adapter.FragmentNewsItemAdapter;
import com.daemon.pas.presenter.adapter.RecyclerViewListener;
import com.daemon.pas.ui.fragment.FragmentNewsItemView;
import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;
import com.socks.library.KLog;
import com.squareup.okhttp.Request;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Daemon on 2015/11/26.
 */
public class FragmentNewsItem extends FragmentPresenter<FragmentNewsItemView> implements View.OnClickListener,
        RecyclerViewListener, DPullRefreshLayout.DPullRefreshLayoutDelegate {



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

        if (old_data == null) {
            getData();
        } else {
            KLog.e("缓存读取 " + old_data);
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

        HorizontalDividerItemDecoration horizontalDividerItemDecoration = new HorizontalDividerItemDecoration.Builder(getActivity())
                .sizeResId(R.dimen.divider)
                .color(getResources().getColor(R.color.colorLine)).build();


        fragmentNewsItemAdapter = new FragmentNewsItemAdapter(newslist, getActivity());
        fragmentNewsItemAdapter.setRecyclerViewListener(this);

        dRecyclerViewAdapter = new DRecyclerViewAdapter(fragmentNewsItemAdapter);
        dRecyclerViewAdapter.setAdapter(fragmentNewsItemAdapter);

        footView = getActivity().getLayoutInflater().inflate(R.layout.item_foot, null, false);
        tvMsg = (TextView) footView.findViewById(R.id.tv_msg);
        pbLoading = (ProgressBar) footView.findViewById(R.id.pb_loading);
        dRecyclerViewAdapter.addFooterView(footView);


        iView.setRecyclerViewInit(linearLayoutManager, horizontalDividerItemDecoration, dRecyclerViewAdapter);


        //刚进来 不显示 只有下滑了才显示
        footView.setVisibility(View.GONE);
        iView.setDrlRefreshLoad(getContext(), this);

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
                    iView.set2TopVisible(View.VISIBLE);
                } else {
                    iView.set2TopVisible(View.GONE);
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
        String url = API.Get_Nes_List + url_aruments;
        if ("快报".equals(name)) {
            url = API.Get_Nes_KB + url_aruments;
        }

        KLog.e("Daemon " + url);

        Request request = new Request.Builder()
                .url(url)
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
                iView.setRefreshComplete();
            }

            @Override
            public void onResponse(String json) {
                mListener.hiheLoading();
                iView.setRefreshComplete();

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
            iView.setDataVisible(View.GONE);

            int old = newslist.size();
            newslist.addAll(newsItemData.getNewslist());
            dRecyclerViewAdapter.notifyItemRangeInserted(old, newslist.size() - old);

            if (page == 1) {
                iView.scrollToTop();
            }

        } else {
            if (page == 1) {
                //无数据 显示默认背景？
                iView.setDataVisible(View.VISIBLE);

            }
            if (page > 1) {
                tvMsg.setText(getResources().getString(R.string.loadingOver));
                pbLoading.setVisibility(View.GONE);
                isOver = true;
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            snappydb.close();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_2top:

                iView.scrollToTop();

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

    @Override
    public void onDPullRefreshLayoutBeginRefreshing(DPullRefreshLayout refreshLayout) {
        page = 1;
        newslist.clear();
        dRecyclerViewAdapter.notifyDataSetChanged();

        isRefresh = true;
        getData();
    }

    @Override
    public boolean onDPullRefreshLayoutBeginLoadingMore(DPullRefreshLayout refreshLayout) {
        return false;
    }
}
