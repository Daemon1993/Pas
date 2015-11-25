package com.daemon.mvp.presenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.daemon.mvp.utils.AppManager;
import com.daemon.mvp.view.IView;

/**
 * Created by Daemon on 2015/11/20.
 */
public abstract class ActivityPresenter<T extends IView> extends AppCompatActivity {

    /**
     * Presenter 持有View层的引用 泛型支持直接引用对象的问题
     */
    public T iView;

    /**
     * 初始化 获取V层对象
     */
    public ActivityPresenter() {
        try {
            iView=getIViewClass().newInstance();
        } catch (InstantiationException e) {
           throw new RuntimeException("create IView error");
        } catch (IllegalAccessException e) {
            throw new RuntimeException("create IView error");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        iView.create(getLayoutInflater(), null, savedInstanceState);

        AppManager.getAppManager().addActivity(this);
        setContentView(iView.getRootView());
        iView.initWeidget();

        bindEventListener();

    }

    /**
     * 监听事件
     */
    protected void bindEventListener() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        iView=null;
        AppManager.getAppManager().finishActivity(this);
    }



    public abstract Class<T> getIViewClass();




}
