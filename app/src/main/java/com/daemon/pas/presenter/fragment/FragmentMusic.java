package com.daemon.pas.presenter.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.daemon.framework.drecyclerviewadapter.DBaseRecyclerViewAdapter;
import com.daemon.framework.drecyclerviewadapter.DRecyclerViewAdapter;
import com.daemon.framework.okhttp.OkHttpUtil;
import com.daemon.mvp.presenter.FragmentPresenter;
import com.daemon.pas.R;
import com.daemon.pas.common.API;
import com.daemon.pas.model.MusicMainData;
import com.daemon.pas.presenter.MainAFInterface;
import com.daemon.pas.presenter.activity.MainActivity;
import com.daemon.pas.presenter.adapter.FragmentMusicMainAdapter;
import com.daemon.pas.ui.fragment.FragmentMusicView;
import com.daemon.pas.utils.ToastUtil;
import com.squareup.okhttp.Request;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daemon on 2015/11/25.
 */
public class FragmentMusic extends FragmentPresenter<FragmentMusicView> {
    public static String Title="音乐";
    private List<MusicMainData.DataEntity.SongsEntity> lists;

    private LinearLayoutManager linearLayoutManager;
    private FragmentMusicMainAdapter fragmentMusicMainAdapter;
    private DRecyclerViewAdapter dRecyclerViewAdapter;
    private int type;
    private MainAFInterface mListener;


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
    protected Class<FragmentMusicView> getIViewClass() {
        return FragmentMusicView.class;
    }


    @Override
    public void bindEvenListener() {
        super.bindEvenListener();

        lists=new ArrayList<MusicMainData.DataEntity.SongsEntity>();

        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        HorizontalDividerItemDecoration horizontalDividerItemDecoration = new HorizontalDividerItemDecoration.Builder(getActivity())
                .sizeResId(R.dimen.divider)
                .color(getResources().getColor(R.color.colorLine)).build();


        fragmentMusicMainAdapter = new FragmentMusicMainAdapter(lists, getActivity());

        dRecyclerViewAdapter = new DRecyclerViewAdapter(fragmentMusicMainAdapter);
        dRecyclerViewAdapter.setAdapter(fragmentMusicMainAdapter);

        fragmentMusicMainAdapter.setmItemOnClickListener(new DBaseRecyclerViewAdapter.ItemOnClickListener() {
            @Override
            public void onclick(int index) {

            }
        });

        iView.setRecyclerViewInit(linearLayoutManager, horizontalDividerItemDecoration, dRecyclerViewAdapter);

        type=0;
        getData(type);
        
    }

    private void getData(int type) {

        String url=API.Muisc_Recommend+type;

        Request request
                =new Request.Builder()
                .tag(this)
                .url(url)
                .get().build();

        OkHttpUtil.getInstance().getData4Server(request, new OkHttpUtil.MyCallBack() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String json) {

                try {
                    MusicMainData musicMainData=OkHttpUtil.getInstance().getGson().fromJson(json,MusicMainData.class);
                    if(musicMainData.getData()!=null && musicMainData.getData().getSongs()!=null && musicMainData.getData().getSongs().size()>0){
                        lists.addAll(musicMainData.getData().getSongs());

                        dRecyclerViewAdapter.notifyDataSetChanged();

                    }else{
                        throw new RuntimeException("123");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtil.showToast("爆炸 接口数据好像出问题了");
                }

            }
        });



    }

    public void changeData() {
        type=1;
        lists.clear();
        dRecyclerViewAdapter.notifyDataSetChanged();

        getData(type);
    }
}
