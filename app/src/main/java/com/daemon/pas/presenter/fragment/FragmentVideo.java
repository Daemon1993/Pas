package com.daemon.pas.presenter.fragment;

import com.daemon.mvp.presenter.FragmentPresenter;
import com.daemon.pas.ui.fragment.FragmentVideoView;

/**
 * Created by Daemon on 2015/11/25.
 */
public class FragmentVideo extends FragmentPresenter<FragmentVideoView> {
    @Override
    protected Class<FragmentVideoView> getIViewClass() {
        return FragmentVideoView.class;
    }


}
