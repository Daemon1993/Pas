package com.daemon.pas.presenter.fragment;

import com.daemon.mvp.presenter.FragmentPresenter;
import com.daemon.pas.ui.fragment.FragmentPicView;

/**
 * Created by Daemon on 2015/11/25.
 */
public class FragmentPic extends FragmentPresenter<FragmentPicView> {
    @Override
    protected Class<FragmentPicView> getIViewClass() {
        return FragmentPicView.class;
    }


}
