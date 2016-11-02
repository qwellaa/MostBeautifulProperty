package com.lanou3g.mostbeautifulproperty.designer.uiview.designeradapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.bean.DesignerBean;

import de.hdodenhof.circleimageview.CircleImageView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by dllo on 16/11/1.
 */

public class DesignAdapter  extends BaseAdapter{
    private Context mContext;


    private DesignerBean mDesignerBean;

    public void setDesignerBean(DesignerBean designerBean) {
        mDesignerBean = designerBean;
    }

    public DesignAdapter(Context context) {
        mContext = context;

    }

    @Override
    public int getCount() {
        return mDesignerBean == null? 0: mDesignerBean.getList().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_design,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.jcVideoPlayer.setUp( mDesignerBean.getList().get(position).getVideo().getVideo().get(0)
                , JCVideoPlayer.SCREEN_LAYOUT_LIST,"");
        Glide.with(mContext).load(mDesignerBean.getList().get(position).getVideo().getThumbnail().get(0))
                .into(viewHolder.jcVideoPlayer.thumbImageView);
        Glide.with(mContext).load(mDesignerBean.getList().get(position).getU().getHeader().get(0)).
                into(viewHolder.cirImg);
        viewHolder.tvTime.setText(mDesignerBean.getList().get(position).getPasstime());
        viewHolder.tvTitle.setText(mDesignerBean.getList().get(position).getText());
        viewHolder.tvName.setText(mDesignerBean.getList().get(position).getU().getName());
        return convertView;
    }

    class ViewHolder{
        private TextView tvName;
        private TextView tvTime;
        private TextView tvTitle;
        private CircleImageView cirImg;
        private JCVideoPlayerStandard jcVideoPlayer;
        public  ViewHolder(View view){
            tvName = (TextView) view.findViewById(R.id.fragment_design_item_tv_username);
            tvTime = (TextView) view.findViewById(R.id.fragment_design_item_tv_time);
            tvTitle = (TextView) view.findViewById(R.id.fragment_design_item_tv_titlce);
            jcVideoPlayer = (JCVideoPlayerStandard) view.findViewById(R.id.fragment_design_videoplayer);
            cirImg = (CircleImageView) view.findViewById(R.id.fragment_design_item_user_hander_img);




        }
    }
}
