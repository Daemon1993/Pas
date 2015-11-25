package com.daemon.pas.presenter.fragment;

import com.daemon.mvp.presenter.FragmentPresenter;
import com.daemon.pas.ui.fragment.FragmentNewsView;

/**
 * Created by Daemon on 2015/11/25.
 */
public class FragmentNews extends FragmentPresenter<FragmentNewsView> {
    @Override
    protected Class<FragmentNewsView> getIViewClass() {
        return FragmentNewsView.class;
    }

    @Override
    public void bindEvenListener() {

        super.bindEvenListener();
    }

}
