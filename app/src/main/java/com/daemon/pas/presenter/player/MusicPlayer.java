package com.daemon.pas.presenter.player;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.support.v4.content.LocalBroadcastManager;

import com.daemon.framework.dutils.DLog;
import com.daemon.pas.common.AppRunCache;

import java.io.IOException;
import java.util.List;

/**
 * Created by Daemon on 2015/12/25.
 */
public class MusicPlayer implements BaseMusicPlayer, MediaPlayer.OnPreparedListener {


    public static final int State_Play = 0;
    public static final int State_Pasue = 1;
    public static final int State_Stop = 2;
    private Intent intent_update;

    private MediaPlayer mediaPlayer;

    //播放的music index 初始状态
    private int play_index = 0;

    //当前音乐状态
    private int Music_State;

    private List<String> musicList;

    private String path;
    private LocalBroadcastManager mLocalBroadcastManager;
    private IntentFilter intentFilter;
    private MusicPlayBroadReceiver myReceiver;

<<<<<<< HEAD
    private boolean isDestroy=false;

=======
>>>>>>> origin/master

    public MusicPlayer(Context context) {

        Music_State = State_Stop;
        mediaPlayer = new MediaPlayer();
<<<<<<< HEAD

=======
>>>>>>> origin/master
        musicList = AppRunCache.musicList;

        intentFilter = new IntentFilter();
        intentFilter.addAction(IntentFilterUtils.Music_Pause);
        intentFilter.addAction(IntentFilterUtils.Music_Resume);
        intentFilter.addAction(IntentFilterUtils.Music_Next);
        intentFilter.addAction(IntentFilterUtils.Music_Previous);
        intentFilter.addAction(IntentFilterUtils.Music_Play_Pause_Onlcik);
        intentFilter.addAction(IntentFilterUtils.Progress);
        intentFilter.addAction(IntentFilterUtils.Music_Reset);

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(context);

        myReceiver = new MusicPlayBroadReceiver();
        mLocalBroadcastManager.registerReceiver(myReceiver, intentFilter);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //自动播放结束后 下一首 先通知
                DLog.e("setOnCompletionListener");
                next();

            }
        });

        DLog.e("onCreate");

        mediaPlayer.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            @Override
            public void onSeekComplete(MediaPlayer mediaPlayer) {
                DLog.e("setOnSeekCompleteListener ");
                if (Music_State == State_Play) {
                    mediaPlayer.start();
                }
            }
        });


        intent_update = new Intent(IntentFilterUtils.Progress_Update);
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                intent_update.putExtra(IntentFilterUtils.Progress_Update, mediaPlayer.getCurrentPosition());
                //DLog.e("setOnBufferingUpdateListener " + mediaPlayer.getCurrentPosition() + "  " + i);
                mLocalBroadcastManager.sendBroadcast(intent_update);

            }
        });

        play();

    }


    /**
     * 对外点击播放按钮提供的方法
     */
    public void actionPlay() {
        switch (Music_State) {
            case State_Play:
                pause();
                break;
            case State_Pasue:
                resume();
                break;
            case State_Stop:
                Music_State = State_Play;
                if(mediaPlayer.getCurrentPosition()!=0){
                    //说明是拖拽过的
                    mediaPlayer.start();
                    sendUIAnimatorPlay();
                }else {
                    play();
                }
                break;
        }
    }


    @Override
    public void play() {

        if(musicList==null || musicList.size()==0){
            return;
        }

        path = musicList.get(play_index);

        try {
            mediaPlayer.reset();
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepareAsync();

            mediaPlayer.setOnPreparedListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        DLog.e("play " + play_index);

    }

    @Override
    public void pause() {

        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            Music_State = State_Pasue;

            mLocalBroadcastManager.sendBroadcast(new Intent(IntentFilterUtils.Music_Pause));
        }
        DLog.e("pause " + musicList.size() + "  -> " + play_index);

    }

    @Override
    public void resume() {
        if (Music_State == State_Pasue) {
            mediaPlayer.start();
            Music_State = State_Play;

            mLocalBroadcastManager.sendBroadcast(new Intent(IntentFilterUtils.Music_Resume));
        }
        DLog.e("resume " + musicList.size() + "  -> " + play_index);
    }

    @Override
    public void seekTo(int progress) {
        DLog.e("接受 " + progress);
        mediaPlayer.seekTo(progress * mediaPlayer.getDuration() / 100);

    }

    /**
     * 上一首
     */
    public void previous() {



        if (play_index == 0) {
            sendNoPrevious();
            return;
        } else {
            play_index--;
        }
        Music_State=State_Play;

        play();
        sendCurrentPosition();
        DLog.e("previous " + musicList.size() + "  -> " + play_index);
    }


    /**
     * 下一首
     */
    public void next() {

        Music_State=State_Play;
        if (play_index == musicList.size() - 1) {
            play_index = 0;
        } else {
            play_index++;
        }

        play();

        sendCurrentPosition();

        DLog.e("next " + musicList.size() + "  -> " + play_index);

    }

    /**
     * 当前显示的数据 停止上一个动画
     */
    private void sendCurrentPosition() {
        Intent intent = new Intent(IntentFilterUtils.Position);
        intent.putExtra(IntentFilterUtils.Index, play_index);
        mLocalBroadcastManager.sendBroadcast(intent);
    }

    private void reset() {
        play_index = 0;
        musicList = AppRunCache.musicList;

        sendCurrentPosition();

        play();

    }


    @Override
    public void destory() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        mLocalBroadcastManager.unregisterReceiver(myReceiver);
    }

    /**
     * 通知没有上一首了
     */
    private void sendNoPrevious() {
        Intent intent = new Intent(IntentFilterUtils.Music_Previous_NO);
        mLocalBroadcastManager.sendBroadcast(intent);
    }


    /**
     * 通知 ui  改变  动画开始 显示当前歌曲信息
     */
    private void sendUIAnimatorPlay() {
        //缓存完毕 发送通知 动画 做出改变
        Intent intent = new Intent(IntentFilterUtils.Music_Prepared_OK);
        intent.putExtra(IntentFilterUtils.Index, play_index);
        intent.putExtra(IntentFilterUtils.Music_Size, mediaPlayer.getDuration());
        mLocalBroadcastManager.sendBroadcast(intent);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

        if (Music_State != State_Stop) {
            //只要缓存完毕 就开始放歌 不不管现在是暂停还是播放 只要下一首就继续播放
            mediaPlayer.start();

            sendUIAnimatorPlay();

            Music_State = State_Play;
            DLog.e("onPrepared pay" + musicList.size() + "  -> " + play_index);

        }else {
            //只要缓冲完了 就将真个音乐的size传送
            sendMusicSize();
<<<<<<< HEAD
            Music_State=State_Pasue;
=======
>>>>>>> origin/master
            DLog.e("onPrepared stop" + musicList.size() + "  -> " + play_index);
        }


    }

<<<<<<< HEAD


=======
>>>>>>> origin/master
    private void sendMusicSize() {
        //有位置就播放 没有就显示大小
        Intent intent = new Intent(IntentFilterUtils.Music_Prepared_OK);
        intent.putExtra(IntentFilterUtils.Music_Size, mediaPlayer.getDuration());
        mLocalBroadcastManager.sendBroadcast(intent);
    }

    class MusicPlayBroadReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case IntentFilterUtils.Music_Play_Pause_Onlcik:

                    DLog.e("Music_Play_Pause_Onlcik");
                    actionPlay();
                    break;

                case IntentFilterUtils.Music_Next:
                    DLog.e("Music_Next");
                    next();
                    break;
                case IntentFilterUtils.Music_Previous:
                    DLog.e("Music_Previous");
                    previous();
                    break;
                case IntentFilterUtils.Music_Reset:
                    reset();
                    break;
                case IntentFilterUtils.Progress:
                    int progress = intent.getIntExtra(IntentFilterUtils.Progress, -1);
                    seekTo(progress);
                    break;
            }
        }
    }


}
