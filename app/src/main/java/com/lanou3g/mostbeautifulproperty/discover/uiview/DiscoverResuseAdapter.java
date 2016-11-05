package com.lanou3g.mostbeautifulproperty.discover.uiview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.bean.DiscoverBean;
import com.lanou3g.mostbeautifulproperty.view.stickyListHeaders.StickyListHeadersAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/5.
 */

public class DiscoverResuseAdapter extends BaseAdapter implements StickyListHeadersAdapter,SectionIndexer {
    private Context mContext;
    private List<DiscoverBean.DataBean.ProductsBean>dataList;

    public DiscoverResuseAdapter(Context context) {
        mContext = context;
        dataList = new ArrayList<>();
    }
    public void setDataListRefresh(List<DiscoverBean.DataBean.ProductsBean> list) {
        dataList.clear();
        dataList.addAll(list);
        notifyDataSetChanged();
    }

    public void setDataListLoad(List<DiscoverBean.DataBean.ProductsBean> list) {
        dataList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public long getHeaderId(int position) {
        return 0;
    }

    @Override
    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public DiscoverBean.DataBean.ProductsBean getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_discover_list,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final DiscoverBean.DataBean.ProductsBean info = getItem(position);
        viewHolder.mIdentity.setText(info.getDesigner().getLabel());
        viewHolder.mName.setText(info.getDesigner().getName());
        viewHolder.mTitle.setText(info.getName());
        Glide.with(mContext).load(info.getDesigner().getAvatar_url()).into(viewHolder.mPhoto);
        Glide.with(mContext).load(info.getCover_images().get(0)).into(viewHolder.mIv);
        return convertView;
    }
    private class ViewHolder {

        private final RadioButton mDisLike;
        private final RadioButton mLike;
        private final ImageView mIv;
        private final TextView mTitle;
        private final RadioGroup mRadioGroup;
        private final TextView mIdentity;
        private final TextView mName;
        private final ImageView mPhoto;

        public ViewHolder(View convertView) {
            mPhoto = (ImageView) convertView.findViewById(R.id.iv_discover_list_photo);
            mName = (TextView) convertView.findViewById(R.id.tv_discover_list_name);
            mIdentity = (TextView) convertView.findViewById(R.id.tv_discover_list_identity);
            mRadioGroup = (RadioGroup) convertView.findViewById(R.id.radio_group);
            mTitle = (TextView) convertView.findViewById(R.id.tv_discover_list_title);
            mIv = (ImageView) convertView.findViewById(R.id.iv_discover_list_title);
            mLike = (RadioButton) convertView.findViewById(R.id.iv_daily_list_like);
            mDisLike = (RadioButton) convertView.findViewById(R.id.iv_daily_list_dislike);


        }
    }

    @Override
    public Object[] getSections() {
        return new Object[0];
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return 0;
    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }


}
