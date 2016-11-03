package com.lanou3g.mostbeautifulproperty.magazine.uiview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.bean.UserFeedbackBean;

import java.util.List;

/**
 *
 */

public class UserFeedbackAdapter extends BaseAdapter{

    private Context mContext;
    private List<UserFeedbackBean> mList;

    public UserFeedbackAdapter(Context context) {
        mContext = context;
    }

    public void setList(List<UserFeedbackBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FeedbackViewHoledr viewHoledr = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_user_feedback, null);
            viewHoledr = new FeedbackViewHoledr(convertView);
            convertView.setTag(viewHoledr);
        } else {
            viewHoledr = (FeedbackViewHoledr) convertView.getTag();
        }

        viewHoledr.mTvTime.setText(mList.get(position).getTime());
        viewHoledr.mTvBody.setText(mList.get(position).getBody());

        return convertView;
    }

    private class FeedbackViewHoledr {

        private final TextView mTvBody;
        private final TextView mTvTime;

        public FeedbackViewHoledr(View view) {
            mTvBody = (TextView) view.findViewById(R.id.item_feedback_body);
            mTvTime = (TextView) view.findViewById(R.id.item_feedback_time);
        }
    }
}
