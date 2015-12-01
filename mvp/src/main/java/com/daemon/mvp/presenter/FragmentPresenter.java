package com.daemon.mvp.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daemon.mvp.model.CloseView;
import com.daemon.mvp.view.IView;

import de.greenrobot.event.EventBus;

/**
 * Created by Daemon on 2015/11/20.
 */
public abstract class FragmentPresenter <T extends IView> extends Fragment {

    public T iView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

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

    public void onEventMainThread(CloseView event) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        iView.destory();
        iView=null;
        EventBus.getDefault().unregister(this);

    }

    protected abstract Class<T> getIViewClass();


}
