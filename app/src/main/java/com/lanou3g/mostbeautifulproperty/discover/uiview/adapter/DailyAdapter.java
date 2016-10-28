package com.lanou3g.mostbeautifulproperty.discover.uiview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.bean.DailyBean;
import com.lanou3g.mostbeautifulproperty.view.stickyListHeaders.StickyListHeadersAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 *
 */

public class DailyAdapter extends BaseAdapter implements StickyListHeadersAdapter, SectionIndexer{

    private Context mContext;
    private List<DailyBean.DataBean.ProductsBean> dataList;
    private SimpleDateFormat mFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();

    public DailyAdapter(Context context) {
        mContext = context;
        dataList = new ArrayList<>();
    }

    public void setDataListRefresh(List<DailyBean.DataBean.ProductsBean> list) {
        dataList.clear();
        dataList.addAll(list);
        notifyDataSetChanged();
    }

    public void setDataListLoad(List<DailyBean.DataBean.ProductsBean> list) {
        dataList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public DailyBean.DataBean.ProductsBean getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.discover_item_list, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        DailyBean.DataBean.ProductsBean info = (DailyBean.DataBean.ProductsBean) getItem(position);
        viewHolder.mTvUserName.setText(info.getDesigner().getName());
        viewHolder.mTvDesigner.setText(info.getDesigner().getLabel());
        viewHolder.mTvTitle.setText(info.getName());

        Glide.with(mContext).load(info.getDesigner().getAvatar_url()).into(viewHolder.mIvUserHead);
        Glide.with(mContext).load(info.getCover_images().get(0)).into(viewHolder.mIvBody);
        return convertView;
    }

    private class ViewHolder {

        private final TextView mTvDesigner;
        private final TextView mTvUserName;
        private final CircleImageView mIvUserHead;
        private final TextView mTvTitle;
        private final ImageView mIvBody;

        public ViewHolder(View view) {
            mTvDesigner = (TextView) view.findViewById(R.id.tv_discover_list_identity);
            mTvUserName = (TextView) view.findViewById(R.id.tv_discover_list_name);
            mIvUserHead = (CircleImageView) view.findViewById(R.id.iv_discover_list_photo);
            mTvTitle = (TextView) view.findViewById(R.id.tv_discover_list_title);
            mIvBody = (ImageView) view.findViewById(R.id.iv_discover_list_title);
        }
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        ViewHeadHolder viewHeadHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_sticky_header, null);
            viewHeadHolder = new ViewHeadHolder(convertView);
            convertView.setTag(viewHeadHolder);
        } else {
            viewHeadHolder = (ViewHeadHolder) convertView.getTag();
        }
        String strDate = mFormat.format(new Date(getItem(position).getPublish_at()));
        viewHeadHolder.mTvHeader.setText(strDate);

        return convertView;
    }

    private class ViewHeadHolder {

        private final TextView mTvHeader;

        public ViewHeadHolder(View view) {
            mTvHeader = (TextView) view.findViewById(R.id.header);
        }
    }

    @Override
    public long getHeaderId(int position) {
        if (position < getCount()) {
            return getItem(position).getPublish_at();
        }
        return 0;
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return 0;
    }

    @Override
    public int getSectionForPosition(int position) {
        if (position < getCount()) {
            return (int) getItem(position).getPublish_at();
        }
        return 0;
    }
}
