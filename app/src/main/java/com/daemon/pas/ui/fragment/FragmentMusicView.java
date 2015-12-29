package com.daemon.pas.ui.fragment;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.daemon.framework.dcustomview.AroundCircleView;
import com.daemon.framework.dcustomview.LrcView;
import com.daemon.framework.dutils.ImageLoaderSetting;
import com.daemon.mvp.view.AppView;
import com.daemon.pas.R;
import com.daemon.pas.model.MusicMainData;
import com.daemon.pas.presenter.fragment.FragmentMusic;
import com.daemon.pas.utils.BitmapBlurHelper;
import com.daemon.pas.utils.TimeUtils;
import com.daemon.pas.utils.ToastUtil;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Daemon on 2015/11/25.
 */
public class FragmentMusicView extends AppView {


    @Bind(R.id.ll_top)
    LinearLayout llTop;
    @Bind(R.id.iv_icon)
    public AroundCircleView ivIcon;
    @Bind(R.id.iv_previous)
    ImageView ivPrevious;
    @Bind(R.id.iv_play_pause)
    ImageView ivPlayPause;
    @Bind(R.id.iv_next)
    ImageView ivNext;
    @Bind(R.id.tv_song_name)
    TextView tvSongName;
    @Bind(R.id.tv_singer_name)
    TextView tvSingerName;
    @Bind(R.id.rl_bg)
    RelativeLayout rlBg;

    @Bind(R.id.rl_icon)
    RelativeLayout rlIcon;
    @Bind(R.id.rl_bottom)
    RelativeLayout rlBottom;
    @Bind(R.id.tv_current_time)
    TextView tvCurrentTime;
    @Bind(R.id.sb_progress)
    SeekBar sbProgress;
    @Bind(R.id.tv_end_time)
    TextView tvEndTime;
    @Bind(R.id.ll_seekbar)
    LinearLayout llSeekbar;
    @Bind(R.id.lv_content)
    LrcView lvContent;
    private ObjectAnimator animator;
    private long currentPlayTime;
    private ObjectAnimator animator1;


    @Override
    protected int getRootLayoutId() {
        return R.layout.fragment_music;
    }

    @Override
    public void destory() {
        super.destory();
        ButterKnife.unbind(this);

    }

    @Override
    public void initWeidget() {
        ButterKnife.bind(this, getRootView());

        //ivicon旋转
        animator = ObjectAnimator.ofFloat(ivIcon, "rotation", 0, 360);
        animator.setDuration(15000);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);

        //透明度动画
        animator1 = ObjectAnimator.ofFloat(rlBg, "alpha", 0.2f, 1.0f);
        animator1.setDuration(2000);
        animator1.setInterpolator(new AccelerateDecelerateInterpolator());


    }


    public void setSongName(MusicMainData.DataEntity.SongsEntity data) {

        tvSongName.setText(data.getName());
        tvSingerName.setText(data.getSingerName());

    }


    public void setImageRes(int ic_launcher) {
        ivIcon.setImageResource(ic_launcher);
    }

    public void setImageBitmap(Bitmap loadedImage) {
        ivIcon.setImageBitmap(loadedImage);
    }

    public void setRlBgBlur(BitmapDrawable drawable) {
        rlBg.setBackgroundDrawable(drawable);
        animator1.start();

    }

    public void setPlayPic() {
        ivPlayPause.setImageResource(R.mipmap.btn_playback_pause);
    }

    public void setPausePic() {
        ivPlayPause.setImageResource(R.mipmap.btn_playback_play);
    }


    public void setDefautImage() {
        ivIcon.setImageResource(R.mipmap.ic_launcher);
        rlBg.setBackgroundColor(Color.BLACK);
    }

    public void setSeekBarListener(FragmentMusic fragmentMusic) {
        sbProgress.setOnSeekBarChangeListener(fragmentMusic);
    }

    public void setSBSize(int duration) {
        tvEndTime.setText(TimeUtils.transformationMS(duration));
    }

    public void setCurrentTime(int size) {
        tvCurrentTime.setText(TimeUtils.transformationMS(size));

        //更新 歌词显示位置
        lvContent.setCurrentTime(size);
    }

    public void setSbProgress(int progress) {
        if (progress < 0) return;

        sbProgress.setProgress(progress);
    }

    public void setInitView() {
        sbProgress.setProgress(0);
        //setDefautImage();
        tvCurrentTime.setText("00:00");
        tvEndTime.setText("00:00");
        lvContent.setText("");
    }

    public void showMusicPic(String picUrl) {

        ImageLoader.getInstance().displayImage(picUrl, ivIcon, ImageLoaderSetting.defaultOptions, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                super.onLoadingStarted(imageUri, view);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                String message = null;
                switch (failReason.getType()) {
                    case IO_ERROR:
                        message = "下载错误";
                        break;
                    case DECODING_ERROR:
                        message = "图片无法显示";
                        break;
                    case NETWORK_DENIED:
                        message = "网络有问题，无法下载";
                        break;
                    case OUT_OF_MEMORY:
                        message = "图片太大无法显示";
                        break;
                    case UNKNOWN:
                        message = "未知的错误";
                        break;
                }
                ToastUtil.showToast(message);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                if (loadedImage != null) {
                    setImageBitmap(loadedImage);
                    Bitmap bitmap = BitmapBlurHelper.doBlurJniBitMap(loadedImage, 50, false);

                    if (bitmap == null) {
                        //获取背景图片失败  使用默认黑色背景 需要图片
                        return;
                    }
                    BitmapDrawable drawable = new BitmapDrawable(getRootView().getResources(), bitmap);
                    setRlBgBlur(drawable);
                }

            }
        });
    }

    public void animatorResume() {

        animator.start();
        animator.setCurrentPlayTime(currentPlayTime);
        setPlayPic();

    }

    public void animatorPause() {
        currentPlayTime = animator.getCurrentPlayTime();
        animator.cancel();
        setPausePic();
    }

    public void animatorRestart() {
        animator.cancel();
        animator.start();
        setPlayPic();
    }

    public void animatorEnd() {
        animator.end();
    }

    public void setLrcContent(String lrc_content) {

        lvContent.setLrcContent(lrc_content);
    }

    public void showProgress() {
        lvContent.setVisibility(View.GONE);
        ivIcon.setVisibility(View.VISIBLE);
    }

    public void showLrc() {
        lvContent.setVisibility(View.VISIBLE);
        ivIcon.setVisibility(View.GONE);
    }
}
