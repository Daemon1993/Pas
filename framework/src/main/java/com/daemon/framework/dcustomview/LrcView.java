package com.daemon.framework.dcustomview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.daemon.framework.R;
import com.daemon.framework.dutils.DLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daemon on 2015/12/28.
 */
public class LrcView extends TextView {
    private  int textColor;
    private Paint currentPaint;

    private String lrcAllContent;
    private List<LrcContent> lrc_lists;
    private int width;
    private int height;

    private  static final int DEFAULTTEXTSIZE=24;
    private static final int DEFAULT_TEXT_COLOR = Color.WHITE;

    private float textSize ;

    public  int currentIndex;

    private int currentTime;

    public void setCurrentTime(int size) {
        currentTime=size;
        for(int i=0 ; i<lrc_lists.size() ;i++){
            if(currentTime<=lrc_lists.get(i).lrcTime){
                currentIndex=i;
                invalidate();
                break;
            }
        }
    }


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


        TypedArray a=context.obtainStyledAttributes(attrs, R.styleable.LrcView,defStyleAttr,0);

        textSize=a.getDimensionPixelSize(R.styleable.LrcView_text_size,DEFAULTTEXTSIZE);

        textColor=a.getColor(R.styleable.LrcView_text_color,DEFAULT_TEXT_COLOR);

        DLog.e("textSize "+textSize);

        a.recycle();

        initView();
    }


    /**
     * 设置歌词
     *
     * @param lrcContent
     */
    public void setLrcContent(String lrcContent) {

        setText("");

        if(TextUtils.isEmpty(lrcContent)){
            lrcAllContent="暂无歌词";
            lrc_lists=null;

            invalidate();
            return;
        }

        this.lrcAllContent = lrcContent;


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

        currentIndex=0;

        //显示出来
        invalidate();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int currentY = height / 2;

        if(lrc_lists==null || lrc_lists.size()==0){
            //画上暂无歌词
            currentPaint.setAlpha(255);
            currentY = height / 2;
            canvas.drawText(lrcAllContent, width / 2, currentY, currentPaint);

            return;
        }


        //上面的
        currentPaint.setAlpha(100);
        for (int i = currentIndex - 1; i >= 0; i--) {
            currentY-= textSize;

            canvas.drawText(lrc_lists.get(i).lrcContent, width / 2, currentY, currentPaint);
        }

        //中间
        currentPaint.setAlpha(255);
        currentY = height / 2;
        canvas.drawText(lrc_lists.get(currentIndex).lrcContent, width / 2, currentY, currentPaint);

        //下面
        currentPaint.setAlpha(100);
        for (int i = currentIndex+1; i <lrc_lists.size(); i++) {
            currentY += textSize;
            canvas.drawText(lrc_lists.get(i).lrcContent, width / 2, currentY, currentPaint);
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
        currentPaint.setColor(textColor);

        currentPaint.setTextSize(textSize);

        textSize+=10;  //用于每行间距 +10

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);
        this.width = w;
        this.height = h;

        DLog.e("w "+w +"  h "+h);
    }





}
