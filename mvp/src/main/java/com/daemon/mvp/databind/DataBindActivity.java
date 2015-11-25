package com.daemon.mvp.databind;

import android.os.Bundle;

import com.daemon.mvp.model.IModel;
import com.daemon.mvp.presenter.ActivityPresenter;
import com.daemon.mvp.view.IView;

/**
 * Created by Daemon on 2015/11/20.
 */
public abstract class DataBindActivity<T extends IView> extends ActivityPresenter<T> {


    DataBinder mBinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinder=getDataBinder();
    }

    protected abstract DataBinder getDataBinder();

    /**
     * data变化
     * @param data
     * @param <D>
     */
    public <D extends IModel> void notifyModelChanged(D data){
        if(mBinder!=null){
            mBinder.viewBindModel(iView,data);
        }
    }

}
