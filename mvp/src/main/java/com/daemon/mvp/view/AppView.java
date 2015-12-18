package com.daemon.mvp.view;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Daemon on 2015/11/20.
 */
public abstract class AppView implements IView {

    private final SparseArray<View> mViews=new SparseArray<View>();

    View rootView;

    @Override
    public void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int rootLayoutId = getRootLayoutId();

        rootView = inflater.inflate(rootLayoutId, container, false);
    }


    protected abstract int getRootLayoutId();


    @Override
    public void destory() {

    }

    @Override
    public View getRootView() {
        return rootView;
    }


    //一些简化代码的方法
    public <T extends View> T bindView(int id){
        T view=(T)mViews.get(id);
        if(view==null){
            view= (T) rootView.findViewById(id);
            mViews.put(id,view);
        }
        return view;
    }

    public <T extends View> T get$(int id){
         return (T)bindView(id);
    }

    public void setOnClickListener(View.OnClickListener listener,int ... ids){
        if(ids==null){
          return;
        }
        for(int id:ids){
            get$(id).setOnClickListener(listener);
        }
    }



}
