package com.daemon.pas.presenter.player;

/**
 * Created by Daemon on 2015/12/25.
 */
public interface BaseMusicPlayer {
    /**
     * 播放
     */
    void play();


    void pause();

    void resume();


    void seekTo(int progress);

    void destory();
}
