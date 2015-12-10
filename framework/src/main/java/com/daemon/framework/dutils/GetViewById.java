package com.daemon.framework.dutils;

import android.view.View;
import android.view.Window;

/**
 * 泛型获取控件  减少findviewbyid使用
 * Created by h2h on 2015/10/31.
 */
public class GetViewById {

    /**
     * Fragment
     * @param view
     * @param viewId
     * @param <T>
     * @return
     */
    public static <T> T $(View view,int viewId) {
        return (T) view.findViewById(viewId);
    }

    /**
     * Activity
     * @param view
     * @param viewId
     * @param <T>
     * @return
     */
    public static <T> T $(Window view,int viewId) {
        return (T) view.findViewById(viewId);
    }

}
