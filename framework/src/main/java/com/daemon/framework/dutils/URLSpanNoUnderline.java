package com.daemon.framework.dutils;

import android.content.Context;
import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

public class URLSpanNoUnderline extends ClickableSpan {
	private Context context;

	private String color;

	private String action;
	public URLSpanNoUnderline(Context context, String color, String action) {
		super();
		this.context=context;
		this.color=color;
		this.action=action;
		
	}

	@Override
	public void updateDrawState(TextPaint ds) {
		ds.setUnderlineText(false);
		ds.setColor(Color.parseColor(color));
	}

	@Override
	public void onClick(View widget) {
		
		switch (action) {


		default:
			break;
		}
		//ToastUtil.showToast("点击跳转");
	}
}