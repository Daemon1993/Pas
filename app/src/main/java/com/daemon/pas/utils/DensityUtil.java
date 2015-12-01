package com.daemon.pas.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

public class DensityUtil {

	 
	
	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	
	
	
	public static DisplayMetrics getDisplayMetrics(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);
		return dm;
	}
	
	/**
	  * 将px值转换为sp值，保证文字大小不变
	  * 
	  * @param pxValue
	  * @param fontScale（DisplayMetrics类中属性scaledDensity）
	  * @return
	  */
	 public static int px2sp(Context context,float pxValue) {
		 final float scale = context.getResources().getDisplayMetrics().density;
	  return (int) (pxValue / scale + 0.5f);
	 }

	 /**
	  * 将sp值转换为px值，保证文字大小不变
	  * 
	  * @param spValue
	  * @param fontScale（DisplayMetrics类中属性scaledDensity）
	  * @return
	  */
	 public static int sp2px(Context context,float spValue) {
		 final float scale = context.getResources().getDisplayMetrics().density;
	  return (int) (spValue * scale + 0.5f);
	 }
	 
	/**
	 * 获取状态栏高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getStatuBarHeight(Context context) {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, sbar = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			sbar = context.getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			// e1.printStackTrace();
		}
		return sbar;
	}
}