package com.daemon.pas.presenter.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daemon.framework.drecyclerviewadapter.DBaseRecyclerViewAdapter;
import com.daemon.framework.drecyclerviewadapter.DRecyclerViewAdapter;
import com.daemon.framework.drecyclerviewadapter.DRecyclerViewScrollListener;
import com.daemon.framework.drecyclerviewadapter.DSpanSizeLookup;
import com.daemon.framework.okhttp.DOkHttp;
import com.daemon.mvp.presenter.ActivityPresenter;
import com.daemon.pas.R;
import com.daemon.pas.common.API;
import com.daemon.pas.model.PicData;
import com.daemon.pas.presenter.adapter.PicAdapter;
import com.daemon.pas.ui.activity.SearchPicActivityView;
import com.daemon.pas.utils.ToastUtil;
import com.google.gson.JsonSyntaxException;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.ArrayList;

public class SearchPicActivity extends ActivityPresenter<SearchPicActivityView> implements View.OnClickListener {


    public static final String Key = "key";

    private String old_title;
    private String key;
    private int skip;
    private ArrayList<PicData.ResEntity.WallpaperEntity> lists;
    private PicAdapter picShowAdapter;
    private DRecyclerViewAdapter dRecyclerViewAdapter;
    private ProgressBar pb_loading;
    private TextView tv_msg;
    private View footView;
    private boolean isOver;


    @Override
    public Class<SearchPicActivityView> getIViewClass() {
        return SearchPicActivityView.class;
    }


    @Override
    protected void bindEventListener() {
        super.bindEventListener();

        setSupportActionBar(iView.toolbar);
        old_title = getSupportActionBar().getTitle().toString();

        iView.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        key = getIntent().getStringExtra(Key);


        getSupportActionBar().setTitle(old_title + "-图片搜索");
        iView.setEditextKey(key);


        iView.setOnClickListener(this, R.id.iv_search);
        lists = new ArrayList<PicData.ResEntity.WallpaperEntity>();
        picShowAdapter = new PicAdapter(lists, this);
        dRecyclerViewAdapter = new DRecyclerViewAdapter(picShowAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setSpanSizeLookup(new DSpanSizeLookup(dRecyclerViewAdapter, 3));
        setFootView();

        iView.setRecyclerViewInit(dRecyclerViewAdapter, gridLayoutManager);
        iView.addLoadMoreListener(new DRecyclerViewScrollListener() {
            @Override
            public void onLoadNextPage(RecyclerView view) {
                footView.setVisibility(View.VISIBLE);
                if (isOver) {
                    return;
                }
                getMoreData();
            }
        });

        picShowAdapter.setmItemOnClickListener(new DBaseRecyclerViewAdapter.ItemOnClickListener() {
            @Override
            public void onclick(int index) {
                Intent intent = new Intent(SearchPicActivity.this, ImageDetailActivity.class);
                intent.putExtra(ImageDetailActivity.URL, lists.get(index).getImg());


                startActivity(intent);
            }
        });

        getInitData();
    }

    @NonNull
    private void setFootView() {
        footView = getLayoutInflater().inflate(R.layout.item_foot, null, false);
        pb_loading = (ProgressBar) footView.findViewById(R.id.pb_loading);
        tv_msg = (TextView) footView.findViewById(R.id.tv_msg);

        dRecyclerViewAdapter.addFooterView(footView);

        footView.setVisibility(View.GONE);

    }

    public void getMoreData() {
        skip += 30;
        getData();
    }

    private void getInitData() {

        key = iView.getEdittextKey();
        if (TextUtils.isEmpty(key)) {
            ToastUtil.showToast("请输入关键字");
            return;
        }

        skip = 30;
        lists.clear();
        footView.setVisibility(View.GONE);
        dRecyclerViewAdapter.notifyDataSetChanged();


        isOver = false;
        showNoMore(false);


        getData();
    }

    private void getData() {

        String url = API.Base_Url_Pic_Search + key + "?adult=false&limit=30&channel=360&first=0&skip=" + skip;


        Request request = new Request.Builder()
                .get()
                .tag(this)
                .url(url)
                .build();


        DOkHttp.getInstance().getData4Server(request, new DOkHttp.MyCallBack() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String json) {

                PicData picData = null;
                try {
                    picData = DOkHttp.getInstance().getGson().fromJson(json, PicData.class);

                    if (picData.getRes() != null && picData.getRes().getWallpaper() != null && picData.getRes().getWallpaper().size() != 0) {
                        if (picData.getRes().getWallpaper().size() < 30) {
                            if (skip == 30) {
                                int old = lists.size();
                                lists.addAll(picData.getRes().getWallpaper());
                                dRecyclerViewAdapter.notifyItemRangeInserted(old, lists.size() - old);

                                iView.scrollToTop();

                            }
                            isOver = true;
                            showNoMore(true);
                        } else {
                            int old = lists.size();
                            lists.addAll(picData.getRes().getWallpaper());
                            dRecyclerViewAdapter.notifyItemRangeInserted(old, lists.size() - old);
                            if (skip == 30) {
                                iView.scrollToTop();
                            }
                        }
                    } else {
                        //数据加载完毕没有更多了
                        footView.setVisibility(View.VISIBLE);
                        showNoMore(true);
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    isDataError();
                }

            }
        });

    }


    private void showNoMore(boolean b) {
        if (b) {
            //加载完了
            pb_loading.setVisibility(View.GONE);
            tv_msg.setText("没有更多数据了");
        } else {
            pb_loading.setVisibility(View.VISIBLE);
            tv_msg.setText("加载中...");
        }
    }


    public static void openActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, SearchPicActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }

        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search:
                getInitData();
                break;
        }
    }
}
