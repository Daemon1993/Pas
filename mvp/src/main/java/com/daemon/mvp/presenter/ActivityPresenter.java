package com.daemon.mvp.presenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.daemon.mvp.R;
import com.daemon.mvp.model.CloseView;
import com.daemon.mvp.utils.AppManager;
import com.daemon.mvp.view.IView;

import de.greenrobot.event.EventBus;

/**
 * Created by Daemon on 2015/11/20.
 */
public abstract class ActivityPresenter<T extends IView> extends AppCompatActivity {

    /**
     * Presenter 持有View层的引用 泛型支持直接引用对象的问题
     */
    public T iView;
    private ProgressBar progressBar;
    private RelativeLayout mActivityLayout;

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

        progressBar=new ProgressBar(this);

        //为了灵活加入全局ProgressBar 这里自顶一个RelativeLayout
        mActivityLayout = new RelativeLayout(this);

        //加入 布局文件
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        mActivityLayout.addView(iView.getRootView(),layoutParams);

        iView.getRootView().setId(R.id.rootId);

        //加入 ProgressBar
        RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        layoutParams1.addRule(RelativeLayout.CENTER_IN_PARENT,R.id.rootId);
        mActivityLayout.addView(progressBar,layoutParams1);
        hideLoadingView();

        setContentView(mActivityLayout);

        EventBus.getDefault().register(this);

        iView.initWeidget();

        bindEventListener();

    }

    /**
     * 显示进度加载
     */
    public void showLoadingView(){
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏进度加载
     */
    public void hideLoadingView(){
        progressBar.setVisibility(View.GONE);
    }

    public void changeLoadingState(){
        if(progressBar.getVisibility()==View.GONE){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }

    /**
     * 监听事件
     */
    protected void bindEventListener() {

    }

    public void onEventMainThread(CloseView event) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iView.destory();
        iView=null;

        EventBus.getDefault().unregister(this);

        AppManager.getAppManager().finishActivity(this);
    }



    public abstract Class<T> getIViewClass();



}
