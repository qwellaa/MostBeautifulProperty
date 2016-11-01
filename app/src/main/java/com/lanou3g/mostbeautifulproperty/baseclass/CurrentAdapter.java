package com.lanou3g.mostbeautifulproperty.baseclass;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;




/**
 * Created by dllo on 16/10/22.
 */

public abstract class CurrentAdapter<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;
    public CurrentAdapter(Context context, List<T> mDatas, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
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
        final BaseViewHolder viewHolder = getViewHolder(position, convertView,
                parent);
        convert(viewHolder, getItem(position));

        return viewHolder.getConvertView();

    }
    public abstract void convert(BaseViewHolder helper, T item);

    private BaseViewHolder getViewHolder(int position, View convertView,
                                         ViewGroup parent) {
        return BaseViewHolder.get(mContext, convertView, parent, mItemLayoutId,
                position);


    }

}
