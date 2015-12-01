package com.daemon.pas.presenter.fragment;

import com.daemon.mvp.presenter.FragmentPresenter;
import com.daemon.pas.ui.fragment.FragmentMusicView;

/**
 * Created by Daemon on 2015/11/25.
 */
public class FragmentMusic extends FragmentPresenter<FragmentMusicView> {
    @Override
    protected Class<FragmentMusicView> getIViewClass() {
        return FragmentMusicView.class;
    }

}
