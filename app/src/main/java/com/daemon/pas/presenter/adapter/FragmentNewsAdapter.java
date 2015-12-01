package com.daemon.pas.presenter.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.daemon.pas.model.NewsTypeData;
import com.daemon.pas.presenter.fragment.FragmentNewsItem;

import java.util.List;

/**
 * Created by Daemon on 2015/11/26.
 */
public class FragmentNewsAdapter extends FragmentPagerAdapter {
    public List<NewsTypeData.ChannellistEntity> lists;


    public FragmentNewsAdapter(FragmentManager fm, List<NewsTypeData.ChannellistEntity> lists) {
        super(fm);
        this.lists = lists;
    }

    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();
        bundle.putString(FragmentNewsItem.CHANNELID, lists.get(position).getChlid());
        bundle.putString(FragmentNewsItem.CHANNELNAME, lists.get(position).getChlname());

        FragmentNewsItem fragmentNewsItem = new FragmentNewsItem();
        fragmentNewsItem.setArguments(bundle);

        return fragmentNewsItem;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return lists.get(position).getChlname();
    }
}
