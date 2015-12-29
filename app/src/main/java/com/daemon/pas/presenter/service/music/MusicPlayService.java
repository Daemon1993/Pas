package com.daemon.pas.presenter.service.music;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.daemon.pas.presenter.player.MusicPlayer;

/**
 * Created by Daemon on 2015/12/23.
 */
public class MusicPlayService extends Service  {


    private MusicPlayer mMusicPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mMusicPlayer=new MusicPlayer(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMusicPlayer.destory();
    }

}
