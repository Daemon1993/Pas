package com.daemon.pas.utils;

import android.graphics.Bitmap;

import com.daemon.framework.dutils.DLog;


/**
 * Created by Daemon on 2015/12/21.
 */
public class BitmapBlurHelper {

    public static Bitmap doBlurJniArray(Bitmap sentBitmap, int radius, boolean canReuseInBitmap) {

        long startTime = System.currentTimeMillis();

        Bitmap bitmap;
        if (canReuseInBitmap) {
            bitmap = sentBitmap;
        } else {
            bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
        }

        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);
        // Jni array calculate
        ImageBlur.blurIntArray(pix, w, h, radius);

        bitmap.setPixels(pix, 0, w, 0, 0, w, h);

        DLog.e("doBlurJniArray time " + (System.currentTimeMillis() - startTime) + "");

        return (bitmap);
    }

    public static Bitmap doBlurJniBitMap(Bitmap sentBitmap, int radius, boolean canReuseInBitmap) {
        Bitmap bitmap;
        try {
            long startTime = System.currentTimeMillis();

            if (canReuseInBitmap) {
                bitmap = sentBitmap;
            } else {
                bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
            }

            if (radius < 1) {
                return (null);
            }
            // Jni bitmap calculate
            ImageBlur.blurBitMap(bitmap, radius);
            DLog.e("doBlurJniBitMap time " + (System.currentTimeMillis() - startTime) + "");
            return (bitmap);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
