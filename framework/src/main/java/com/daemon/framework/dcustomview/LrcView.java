package com.daemon.framework.dcustomview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.daemon.framework.dutils.DLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daemon on 2015/12/28.
 */
public class LrcView extends TextView {
    private Paint currentPaint;

    private String lrcAllContent;
    private List<LrcContent> lrc_lists;
    private int width;
    private int height;
    private Paint currentPaint1;
    private float textHeight = 50;

    
    class LrcContent {
        public int lrcTime;
        public String lrcContent;

        @Override
        public String toString() {
            return "LrcContent{" +
                    "lrcTime=" + lrcTime +
                    ", lrcContent='" + lrcContent + '\'' +
                    '}';
        }
    }


    public LrcView(Context context) {
        this(context, null);
    }

    public LrcView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LrcView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LrcView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    /**
     * 设置歌词
     *
     * @param lrcContent
     */
    public void setLrcContent(String lrcContent) {
        this.lrcAllContent = lrcContent;

        //解析歌词 抽取title 歌手

        lrcAllContent = lrcAllContent.replace("[", "");
        lrcAllContent = lrcAllContent.replace("]", "@");                                 //每一句话的分隔符
        lrcAllContent = lrcAllContent.replaceAll("<[0-9]{0,5}>", "");                    //去掉每个字的时间标签,这里用到了正则表达式
        lrcAllContent = lrcAllContent.replaceAll("\\n", "@");                    //去掉每个字的时间标签,这里用到了正则表达式

        DLog.e(lrcAllContent);

        lrc_lists = new ArrayList<LrcContent>();

        String[] lrcArray = lrcAllContent.split("@");

        //分离 时间 content
        for (int i = 0; i < lrcArray.length; i++) {
            int time = praseLrc(lrcArray[i]);
            LrcContent lrcContent1 = new LrcContent();
            if (time == -1) {
                //下一个
                continue;
            } else {
                if (i + 1 > lrcArray.length - 1) {
                    break;
                }

                lrcContent1.lrcContent = lrcArray[i + 1];
                lrcContent1.lrcTime = time;
                lrc_lists.add(lrcContent1);
            }
        }

        //显示出来
        invalidate();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        if(lrc_lists==null || lrc_lists.size()==0){
            return;
        }
        int currentY = height / 2;

        //上面的
        for (int i = 10 - 1; i >= 0; i--) {
            currentY-=textHeight;
            canvas.drawText(lrc_lists.get(i).lrcContent, width / 2, currentY, currentPaint1);
        }

        currentY = height / 2;

        canvas.drawText(lrc_lists.get(10).lrcContent, width / 2, currentY, currentPaint);

        for (int i = 11; i <lrc_lists.size(); i++) {
            currentY +=textHeight;
            canvas.drawText(lrc_lists.get(i).lrcContent, width / 2, currentY, currentPaint1);
        }


    }

    /**
     * 如果不是时间 就捕获异常 然后下一个 说明是前面没有用的数据
     *
     * @param s
     */
    private int praseLrc(String s) {

        return transform2Int(s);

    }

    /**
     * 转换时间
     *
     * @param s
     */
    private int transform2Int(String s) {
        s = s.replace(".", ":");
        String[] sArray = s.split(":");

        try {
            if (sArray.length == 3) {
                DLog.e(sArray[0] + "  " + sArray[1] + " " + sArray[2]);

                int min = Integer.parseInt(sArray[0].trim());
                int second = Integer.parseInt(sArray[1].trim());
                int mills = Integer.parseInt(sArray[2].trim());
                return (min * 60 + second) * 1000 + mills;
            } else {
                return -1;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
    }


    private void initView() {

        setFocusable(true);

        currentPaint = new Paint();
        currentPaint.setAntiAlias(true);
        currentPaint.setTextAlign(Paint.Align.CENTER);
        currentPaint.setColor(Color.parseColor("#FF34A350"));
        currentPaint.setTextSize(25);

        currentPaint1 = new Paint();
        currentPaint1.setAntiAlias(true);
        currentPaint1.setTextAlign(Paint.Align.CENTER);
        currentPaint1.setColor(Color.parseColor("#FF4081"));
        currentPaint1.setTextSize(20);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        this.width = w;
        this.height = h;
    }


}
