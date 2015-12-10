package com.daemon.pas.presenter.fragment;

import com.daemon.mvp.presenter.FragmentPresenter;
import com.daemon.pas.ui.fragment.FragmentVideoView;

/**
 * Created by Daemon on 2015/11/25.
 */
public class FragmentVideo extends FragmentPresenter<FragmentVideoView> {
    public static String Title="视频";

    @Override
    protected Class<FragmentVideoView> getIViewClass() {
        return FragmentVideoView.class;
    }


}
