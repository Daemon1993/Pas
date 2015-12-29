package com.daemon.pas.presenter.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.SeekBar;

import com.daemon.framework.dutils.DLog;
import com.daemon.framework.dutils.JsonObjectUtils;
import com.daemon.framework.okhttp.DOkHttp;
import com.daemon.mvp.presenter.FragmentPresenter;
import com.daemon.pas.R;
import com.daemon.pas.common.API;
import com.daemon.pas.common.AppRunCache;
import com.daemon.pas.common.BaseRequest;
import com.daemon.pas.model.MusicMainData;
import com.daemon.pas.model.MusicPlayData;
import com.daemon.pas.presenter.MainActivityInterface;
import com.daemon.pas.presenter.activity.MainActivity;
import com.daemon.pas.presenter.player.IntentFilterUtils;
import com.daemon.pas.presenter.service.music.MusicPlayService;
import com.daemon.pas.ui.fragment.FragmentMusicView;
import com.daemon.pas.utils.ToastUtil;
import com.google.gson.JsonSyntaxException;
import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;
import com.squareup.okhttp.Request;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Daemon on 2015/11/25.
 */
public class FragmentMusic extends FragmentPresenter<FragmentMusicView> implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    public static String Title = "音乐";
    private List<MusicMainData.DataEntity.SongsEntity> lists;


    private int type;
    private MainActivityInterface mListener;

    private MusicPlayData.DataEntity music_Play_Current_picData;
    private int current_index_playing = 0;

    private DB snappydb;
    private String old_json;
    private MusicPlayBroadReceiver myReceiver;
    private LocalBroadcastManager mLocalBroadcastManager;
    private IntentFilter intentFilter;

    private Intent service;


    //是否拖动
    private boolean isChange;
    private int duration;
    private boolean isFirst = true;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mListener = (MainActivityInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement MainAFInterface");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    protected Class<FragmentMusicView> getIViewClass() {
        return FragmentMusicView.class;
    }


    @Override
    public void bindEvenListener() {
        super.bindEvenListener();

        lists = new ArrayList<MusicMainData.DataEntity.SongsEntity>();

        try {
            snappydb = DBFactory.open(getActivity());
            old_json = snappydb.get(AppRunCache.Music_Init_Data);
        } catch (SnappydbException e) {
            e.printStackTrace();
        } finally {
            try {
                snappydb.close();
            } catch (SnappydbException e) {
                e.printStackTrace();
            }
        }

        initView();

        iView.setOnClickListener(this, R.id.iv_previous, R.id.iv_next, R.id.iv_play_pause, R.id.iv_icon, R.id.lv_content);

        iView.setSeekBarListener(this);

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(getContext());

        intentFilter = new IntentFilter();
        intentFilter.addAction(IntentFilterUtils.Music_Prepared_OK);
        intentFilter.addAction(IntentFilterUtils.Music_Pause);
        intentFilter.addAction(IntentFilterUtils.Music_Resume);
        intentFilter.addAction(IntentFilterUtils.Progress_Update);
        intentFilter.addAction(IntentFilterUtils.Position);

        //广播注册
        myReceiver = new MusicPlayBroadReceiver();
        mLocalBroadcastManager.registerReceiver(myReceiver, intentFilter);


        //启动服务
        service = new Intent();
        service.setClass(getActivity(), MusicPlayService.class);

        if (TextUtils.isEmpty(old_json)) {
            getData(type);
        } else {
            praseDataByJson(old_json, type);
        }

    }

    private void getData(final int type) {

        String url = API.Muisc_Recommend + type;

        Request request
                = new Request.Builder()
                .tag(this)
                .url(url)
                .get().build();

        DOkHttp.getInstance().getData4Server(request, new DOkHttp.MyCallBack() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String json) {
                praseDataByJson(json, type);
            }
        });


    }

    private void praseDataByJson(String json, int type) {

        try {
            MusicMainData musicMainData = DOkHttp.getInstance().getGson().fromJson(json, MusicMainData.class);
            if (musicMainData.getData() != null && musicMainData.getData().getSongs() != null && musicMainData.getData().getSongs().size() > 0) {
                AppRunCache.musicList.clear();
                for (int i = 0; i < musicMainData.getData().getSongs().size(); i++) {
                    if (musicMainData.getData().getSongs().get(i).getUrlList() != null && musicMainData.getData().getSongs().get(i).getUrlList().size() > 0) {
                        lists.add(musicMainData.getData().getSongs().get(i));
                        AppRunCache.musicList.add(lists.get(i).getUrlList().get(0).getUrl());
                    }

                }

                if (isFirst) {
                    getActivity().startService(service);
                }

                if (type == 1) {
                    Intent intent0 = new Intent(IntentFilterUtils.Music_Reset);
                    mLocalBroadcastManager.sendBroadcast(intent0);
                }


                iView.setSongName(lists.get(current_index_playing));

                if (type == 0) {
                    //获取第一首歌的播放数据
                    showSongMsg(lists.get(current_index_playing));
                }
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            ToastUtil.showToast("praseDataByJson Error");
        }

    }

    /**
     * 显示歌曲信息 还未播放  点击之后  或者service通知才开始获取图片信息
     *
     * @param data
     */
    private void showSongMsg(final MusicMainData.DataEntity.SongsEntity data) {


        //获取歌词
        String url_lrc = API.Music_Get_Lrc1 + "&duration_ms=" + data.getUrlList().get(0).getDuration() + "&title=" + data.getName() + "&song_id=" + data.getSongId()
                + "&artist=" + data.getSingerName();

        getLrc(url_lrc);


        String url = API.Music_Play + data.getSingerId() + "&song_id=" + data.getSongId();

        Request request
                = new Request.Builder()
                .tag(this)
                .url(url)
                .get().build();

        DOkHttp.getInstance().getData4Server(request, new DOkHttp.MyCallBack() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String json) {

                try {
                    MusicPlayData musicPlayData = DOkHttp.getInstance().getGson().fromJson(json, MusicPlayData.class);

                    if (musicPlayData.getData() != null && musicPlayData.getData().size() > 0) {

                        music_Play_Current_picData = musicPlayData.getData().get(0);

                        if (musicPlayData.getData().get(0).getPicUrls() == null || musicPlayData.getData().get(0).getPicUrls().size() == 0) {
                            musicPlayData.getData().get(0).setPicUrls(new ArrayList<MusicPlayData.DataEntity.PicUrlsEntity>());
                            musicPlayData.getData().get(0).getPicUrls().add(new MusicPlayData.DataEntity.PicUrlsEntity());
                        }
                        showPic(music_Play_Current_picData);

                    } else {
                        throw new RuntimeException("123");
                    }
                } catch (JsonSyntaxException e) {
                    e.printStackTrace();
                    ToastUtil.showToast(BaseRequest.Error_Msg);
                }

            }
        });

    }


    /**
     * 获取歌词
     *
     * @param url_lrc
     */
    private void getLrc(String url_lrc) {

        DLog.e(url_lrc);

        Request request = new Request.Builder()
                .get()
                .url(url_lrc)
                .tag(this)
                .build();

        DOkHttp.getInstance().getData4Server(request, new DOkHttp.MyCallBack() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String json) {
                try {
                    JSONArray data = JsonObjectUtils.getArray(json, "data");

                    if (data == null || data.length() == 0) {
                        throw new RuntimeException("123");
                    }
                    JSONObject jsonObject = data.getJSONObject(0);

                    String lrcid = jsonObject.getString("_id");
                    int type = jsonObject.getInt("type");
                    String singer_name = jsonObject.getString("singer_name");
                    String song_name = jsonObject.getString("song_name");

                    getLrc2(lrcid, type, singer_name, song_name);
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtil.showToast("暂无歌词");
                }
            }
        });

    }

    /**
     * 获取歌词第二步
     *
     * @param lrcid
     * @param type
     * @param singer_name
     * @param song_name
     */
    private void getLrc2(String lrcid, int type, String singer_name, String song_name) {

        String url = API.getMusic_Get_Lrc2 + "&lrcid=" + lrcid + "&title=" + song_name + "&artist=" + singer_name;
        if (type != -1) {
            url += "&type=" + type;
        }

        DLog.e(url);

        Request request = new Request.Builder()
                .get()
                .url(url)
                .tag(this)
                .build();

        DOkHttp.getInstance().getData4Server(request, new DOkHttp.MyCallBack() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String json) {
                try {
                    String data = JsonObjectUtils.getString(json, "data");

                    if (TextUtils.isEmpty(data)) {
                        throw new RuntimeException("123");
                    }

                    String lrc_content = JsonObjectUtils.getString(data, "content");

                    showLrc(lrc_content);

                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtil.showToast("拿不到一点歌词");
                }
            }
        });

    }

    /**
     * 显示歌词
     *
     * @param lrc_content
     */
    private void showLrc(String lrc_content) {
        DLog.e(lrc_content);
        iView.setLrcContent(lrc_content);
    }

    /**
     * 显示对于歌曲的图片
     *
     * @param music_play_current_picData
     */
    private void showPic(final MusicPlayData.DataEntity music_play_current_picData) {

        //不是第一次 就获取图片之后 开始动画
        if (!isFirst) {
            animatorRestart();
            isFirst = false;
        }
        isFirst = false;

        iView.showMusicPic(music_play_current_picData.getPicUrls().get(0).getPicUrl());

    }

    public void changeData() {

        type = 1;
        initView();

        getData(type);
    }

    private void initView() {

        current_index_playing = 0;
        lists.clear();

        iView.setInitView();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_previous:

                Intent intent = new Intent(IntentFilterUtils.Music_Previous);
                mLocalBroadcastManager.sendBroadcast(intent);
                DLog.e("iv_previous");


                break;

            case R.id.iv_next:
                Intent intent1 = new Intent(IntentFilterUtils.Music_Next);
                mLocalBroadcastManager.sendBroadcast(intent1);
                DLog.e("iv_next");


                break;

            case R.id.iv_play_pause:
                Intent intent0 = new Intent(IntentFilterUtils.Music_Play_Pause_Onlcik);
                mLocalBroadcastManager.sendBroadcast(intent0);
                DLog.e("iv_play_pause");

                break;

            case R.id.iv_icon:
                iView.showLrc();
                break;
            case R.id.lv_content:
                iView.showProgress();

                break;
        }
    }


    /**
     * 恢复
     */
    private void animatorResume() {
        iView.animatorResume();
    }

    /**
     * 暂停
     */
    private void animatorPause() {
        iView.animatorPause();
    }

    /**
     * 取消 重新开始
     */
    private void animatorRestart() {
        iView.animatorRestart();
    }

    /**
     * 结束动画 位置复原 新的开始
     */
    private void animatorEnd() {
        iView.animatorEnd();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        if (isChange) {
            iView.setCurrentTime(seekBar.getProgress() * duration / 100);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        isChange = true;

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (isChange) {
            Intent intent = new Intent(IntentFilterUtils.Progress);
            intent.putExtra(IntentFilterUtils.Progress, seekBar.getProgress());

            mLocalBroadcastManager.sendBroadcast(intent);
            DLog.e("拖动 " + seekBar.getProgress());
            isChange = false;
        }
    }


    class MusicPlayBroadReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case IntentFilterUtils.Music_Prepared_OK:

                    int index = intent.getIntExtra(IntentFilterUtils.Index, -1);
                    duration = intent.getIntExtra(IntentFilterUtils.Music_Size, 0);

                    iView.setSBSize(duration);

                    if (index == -1) return;

                    //如果type==1说明是换一批之后的
                    if (isFirst) {
                        //第一次进来 图片已经加载完毕
                        animatorRestart();
                    } else {
                        current_index_playing = index;
                        showSongMsg(lists.get(current_index_playing));
                    }

                    break;
                case IntentFilterUtils.Music_Pause:
                    animatorPause();
                    break;
                case IntentFilterUtils.Music_Resume:
                    animatorResume();
                    break;
                case IntentFilterUtils.Position:

                    //暂停当前动画
                    animatorEnd();
                    iView.setInitView();

                    int index1 = intent.getIntExtra(IntentFilterUtils.Index, -1);

                    if (index1 == -1) return;

                    current_index_playing = index1;
                    iView.setSongName(lists.get(current_index_playing));

                    //显示默认图片
                    iView.setDefautImage();


                    break;
                case IntentFilterUtils.Progress_Update:

                    int progress = intent.getIntExtra(IntentFilterUtils.Progress_Update, -1);

                    try {
                        if (!isChange) {
                            //更新当前播放位置的显示
                            iView.setCurrentTime(progress);
                            iView.setSbProgress(progress * 100 / duration);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;
            }
        }
    }
}
