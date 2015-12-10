package com.daemon.framework.dutils;

import android.util.SparseArray;
import android.view.View;

/**
 * ViewHolder的简化写法
 * @author Administrator
 *
 */
public class ViewHolderUtils {
	
	    @SuppressWarnings("unchecked")
	    public static <T extends View> T get(View view, int id) {
	        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();    //稀疏数组 的Viewholder  取代HashMap   这里是复用
	        if (viewHolder == null) {
	            viewHolder = new SparseArray<View>();
	            view.setTag(viewHolder);
	        }
	        View childView = viewHolder.get(id);           //在当前view里面查找这个 id   这里也是复用   
	        if (childView == null) {
	            childView = view.findViewById(id);
	            viewHolder.put(id, childView);
	        }
	        return (T) childView;
	    }
}
