package com.daemon.framework.dutils;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 通用的BaseAdapter
 * ListView  GridView 统一使用
 * Created by Daemon on 2015/10/12.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {

    private List<T> mDatas;

    private Context mContext;

    private LayoutInflater mLayoutInflater;

    private int layoutId;

    public MyBaseAdapter(List<T> mDatas, Context mContext, int layoutId) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //初始化ViewHolder,使用通用的ViewHolder，一样代码就搞定ViewHolder的初始化咯
        MyViewHolder holder = MyViewHolder.get(mContext, convertView, parent, layoutId, position);//layoutId就是单个item的布局

        convert(holder, getItem(position));

        return holder.getConvertView(); //这一行的代码要注意了
    }

    //将convert方法公布出去 子类实现
    public abstract void convert(MyViewHolder holder, T t);

}


class MyViewHolder {
    private SparseArray<View> mViews;
    private int mPosition;
    private View mCurrentView;

    public MyViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mCurrentView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        mCurrentView.setTag(this);
    }

    public static MyViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new MyViewHolder(context, parent, layoutId, position);
        } else {
            MyViewHolder holder = (MyViewHolder) convertView.getTag();
            holder.mPosition = position; //ViewHolder是复用的，position更新一下
            return holder;
        }
    }

    /*
   通过viewId获取控件
   */
    //使用的是泛型T,返回的是View的子类
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        //复用
        if (view == null) {
            view = mCurrentView.findViewById(viewId);
            mViews.put(viewId, view);
        }

        return (T) view;
    }

    public View getConvertView() {
        return mCurrentView;
    }

}