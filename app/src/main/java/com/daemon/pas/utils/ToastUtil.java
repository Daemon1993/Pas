package com.daemon.pas.utils;

import android.widget.Toast;

import com.daemon.pas.MyApplication;


public class ToastUtil {

	private static Toast mToast;
	
    public static void showToast(String text) {
        if(mToast == null) {  
            mToast = Toast.makeText(MyApplication.getInstance().getApplicationContext(), text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);    
            mToast.setDuration(Toast.LENGTH_SHORT);  
        }  
        mToast.show();  
    }  
      
    public static void cancelToast() {  
    	
            if (mToast != null) {  
                mToast.cancel();  
            }  
        }  
      
}
