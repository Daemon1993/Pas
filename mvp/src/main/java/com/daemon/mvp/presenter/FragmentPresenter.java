package com.daemon.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daemon.mvp.view.IView;

/**
 * Created by Daemon on 2015/11/20.
 */
public abstract class FragmentPresenter <T extends IView> extends Fragment {

    public T iView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            iView=getIViewClass().newInstance();
        } catch (java.lang.InstantiationException e) {
            throw new RuntimeException("create IView error");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("create IView error");
        }

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        iView.create(inflater, container, savedInstanceState);
        Log.e("Daemon","onCreateView");

        return iView.getRootView();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("Daemon","onActivityCreated");
        iView.initWeidget();

        bindEvenListener();
    }

    /**
     * 监听事件
     */
    public void bindEvenListener() {

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        iView = null;
    }

    protected abstract Class<T> getIViewClass();


}
