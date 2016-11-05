package com.lanou3g.mostbeautifulproperty.baseclass;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by dllo on 16/10/22.
 */

public class BaseViewHolder {
    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private Context mContext;


    private BaseViewHolder(Context context, ViewGroup parent, int layoutId,
                           int position) {
        this.mContext = context;
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        // setTag
        mConvertView.setTag(this);
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static BaseViewHolder get(Context context, View convertView,
                                     ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new BaseViewHolder(context, parent, layoutId, position);
        }
        return (BaseViewHolder) convertView.getTag();
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public BaseViewHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public BaseViewHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param
     * @return
     */
//    public BaseViewHolder setImageBitmap(int viewId, Bitmap bm) {
//        ImageView view = getView(viewId);
//        view.setImageBitmap(bm);
//        return this;
//
//    }
    public BaseViewHolder setIamgeGlide(int viewId, String imgUrl) {
        ImageView view = getView(viewId);
        Glide.with(mContext).load(imgUrl).into(view);
        return this;
    }

    public BaseViewHolder setViewVisible(int viewId, int visible) {
        ImageView view = getView(viewId);
        view.setVisibility(visible);
        return this;

    }
    public BaseViewHolder setOnClickImg(int viewId, View.OnClickListener listener){
        ImageView view  = getView(viewId);
        view.setOnClickListener(listener);

        return this;
    }


    public int getPosition() {
        return mPosition;
    }

}
