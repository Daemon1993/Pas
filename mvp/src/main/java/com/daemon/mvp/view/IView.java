package com.daemon.mvp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * View层接口
 * Created by Daemon on 2015/11/20.
 */
public interface IView {

    void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    View getRootView();


    void initWeidget();

    void destory();
}
