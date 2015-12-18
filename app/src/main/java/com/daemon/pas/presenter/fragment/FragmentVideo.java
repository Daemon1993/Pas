package com.daemon.pas.presenter.fragment;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;

import com.daemon.framework.dutils.WeakHandler;
import com.daemon.mvp.presenter.FragmentPresenter;
import com.daemon.pas.ui.fragment.FragmentVideoView;

/**
 * Created by Daemon on 2015/11/25.
 */
public class FragmentVideo extends FragmentPresenter<FragmentVideoView> {
    public static String Title="视频";
    private int progress;

    WeakHandler weakHandler=new WeakHandler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            if(msg.what==1){
                iView.setAVCProgress(progress);
            }

            return false;
        }
    });
    private boolean falg=true;

    @Override
    protected Class<FragmentVideoView> getIViewClass() {
        return FragmentVideoView.class;
    }


    @Override
    public void bindEvenListener() {
        super.bindEvenListener();

        progress=0;

        new Thread(new Runnable() {


            @Override
            public void run() {

                while(falg) {

                    if(progress>100){
                        progress=0;
                    }
                    SystemClock.sleep(1000);
                    progress += 5;

                    weakHandler.sendEmptyMessage(1);

                }
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        falg=false;
    }
}
