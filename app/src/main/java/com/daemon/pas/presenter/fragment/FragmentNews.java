package com.daemon.pas.presenter.fragment;

import android.content.Context;

import com.daemon.framework.okhttp.OkHttpUtil;
import com.daemon.mvp.presenter.FragmentPresenter;
import com.daemon.pas.model.NewsTypeData;
import com.daemon.pas.presenter.MainAFInterface;
import com.daemon.pas.presenter.activity.MainActivity;
import com.daemon.pas.presenter.adapter.FragmentNewsAdapter;
import com.daemon.pas.ui.fragment.FragmentNewsView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Daemon on 2015/11/25.
 */
public class FragmentNews extends FragmentPresenter<FragmentNewsView> {

    public static final String Title="新闻";

    private NewsTypeData newsType;
    private FragmentNewsAdapter fragmentNewsAdapter;
    private MainAFInterface mListener;
    private List<NewsTypeData.ChannellistEntity> list;


    @Override
    protected Class<FragmentNewsView> getIViewClass() {
        return FragmentNewsView.class;
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
        mListener=null;
    }

    @Override
    public void bindEvenListener() {

        super.bindEvenListener();


        intitView();
    }


    private void intitView() {
        getData();
    }

    private void getData() {

        list = new ArrayList<NewsTypeData.ChannellistEntity>();
        try {
            InputStream inputStream = getResources().getAssets().open("news.json");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufReader = new BufferedReader(inputStreamReader);

            String line = "";
            String result = "";
            while ((line = bufReader.readLine()) != null)
                result += line;

            setData(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * json转成bean
     *
     * @param result
     */
    private void setData(String result) {
        newsType = OkHttpUtil.getInstance().getGson().fromJson(result, NewsTypeData.class);

        if (newsType != null && newsType.getChannellist() != null) {

            List<Integer> nums = new ArrayList<Integer>();

            //始终保持每次取出来只有5个
            for (int i = 0; i < 5; i++) {
                int random = 0;
                while (true) {
                    random = getRandom(newsType.getChannellist().size());
                    //如果获取的随机数里面没有就break 否则从来一次
                    if (judge(nums, random)) {
                        nums.add(random);
                        break;
                    }
                }

                list.add(newsType.getChannellist().get(random));
            }
            //list.addAll(newsType.getChannellist());

            //拿到所有新闻的分类 添加tab
            fragmentNewsAdapter = new FragmentNewsAdapter(getChildFragmentManager(), list);

            iView.setViewPagerInit(fragmentNewsAdapter);

        }
    }


    /**
     * 不存在 返回true
     *
     * @param nums
     * @param random
     * @return
     */
    private boolean judge(List<Integer> nums, int random) {
        if (nums.contains(random)) {
            return false;
        }
        return true;
    }

    private int getRandom(int size) {
        return new Random().nextInt(size);
    }


}
