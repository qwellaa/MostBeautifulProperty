package com.lanou3g.mostbeautifulproperty.designer.uiview.designeradapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.bean.DesignerBean;
import com.lanou3g.mostbeautifulproperty.designer.uiview.VideoDetailActivity;
import com.wx.goodview.GoodView;

import de.hdodenhof.circleimageview.CircleImageView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by dllo on 16/11/1.
 */

public class DesignAdapter extends BaseAdapter {
    private Context mContext;


    private DesignerBean mDesignerBean;


    public void setDesignerBean(DesignerBean designerBean) {
        mDesignerBean = designerBean;

        notifyDataSetChanged();
    }

    public void setMoreDesignerBean(DesignerBean designerBean) {
        mDesignerBean.getList().addAll(designerBean.getList());
        notifyDataSetChanged();


    }


    public DesignAdapter(Context context) {
        mContext = context;


    }

    @Override
    public int getCount() {
        return mDesignerBean == null ? 0 : mDesignerBean.getList().size();
    }

    @Override
    public Object getItem(int position) {
        return mDesignerBean.getList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        DesignerBean.ListBean.VideoBean videoBean = mDesignerBean.getList().get(position).getVideo();
        int time = mDesignerBean.getList().get(position).getVideo().getDuration();
        final DesignerBean.ListBean listBean = mDesignerBean.getList().get(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_design, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.jcVideoPlayer.setUp(videoBean.getVideo().get(0)
                , JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
        Glide.with(mContext).load(videoBean.getThumbnail().get(0))
                .into(viewHolder.jcVideoPlayer.thumbImageView);
        Glide.with(mContext).load(mDesignerBean.getList().get(position).getU().getHeader().get(0)).
                into(viewHolder.cirImg);
        viewHolder.jcVideoPlayer.thumbImageView.setBackgroundColor(Color.WHITE);


        viewHolder.tvTime.setText(mDesignerBean.getList().get(position).getPasstime());
        viewHolder.tvTitle.setText(mDesignerBean.getList().get(position).getText());
        viewHolder.tvName.setText(mDesignerBean.getList().get(position).getU().getName());
        viewHolder.tvPlayTimes.setText(videoBean.getPlaycount() + "次播放");

        viewHolder.tvVideoTime.setText(toTime(time));
        viewHolder.tvUp.setText(listBean.getUp());
        viewHolder.tvDown.setText(listBean.getDown() + "");
        viewHolder.tvComment.setText(listBean.getComment());
        viewHolder.tvForward.setText(listBean.getForward() + "");
        Log.d("傻逼", "listBean.getNum():" + listBean.getNum());
        switch (listBean.getNum()) {
            case 0:
                viewHolder.upImagButton.setBackgroundResource(R.mipmap.ding_not_clicked);
                viewHolder.downImagButton.setBackgroundResource(R.mipmap.cai_not_clicked);
                viewHolder.tvUp.setTextColor(Color.GRAY);
                viewHolder.tvDown.setTextColor(Color.GRAY);
                viewHolder.downImagButton.setFocusable(true);
                viewHolder.upImagButton.setFocusable(true);
                break;

            case 1:
                viewHolder.upImagButton.setBackgroundResource(R.mipmap.ding_has_clicked);
                viewHolder.tvUp.setTextColor(Color.RED);
                viewHolder.downImagButton.setFocusable(false);
                viewHolder.upImagButton.setFocusable(false);

                break;

            case 2:
                viewHolder.downImagButton.setBackgroundResource(R.mipmap.cai_has_clicked);
                viewHolder.tvDown.setTextColor(Color.RED);
                viewHolder.upImagButton.setFocusable(false);
                viewHolder.downImagButton.setFocusable(false);

                break;


        }
        final GoodView goodView = new GoodView(mContext);
        final ViewHolder finalViewHolder = viewHolder;


        viewHolder.upImagButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                if (finalViewHolder.upImagButton.isFocusable()) {

                    finalViewHolder.upImagButton.setBackgroundResource(R.mipmap.ding_has_clicked);

                    finalViewHolder.downImagButton.setClickable(false);
                    finalViewHolder.tvUp.setText("" + ((Integer.decode(listBean.getUp())) + 1));
                    listBean.setNum(1);
                    goodView.setText("+1");
                    goodView.setTranslateY(finalViewHolder.upImagButton.getBottom(), finalViewHolder.upImagButton.getTop());
                    finalViewHolder.tvUp.setTextColor(Color.RED);
                    goodView.show(v);
                    finalViewHolder.upImagButton.setClickable(false);
                }
            }
        });

        viewHolder.downImagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalViewHolder.downImagButton.isFocusable()) {
                    finalViewHolder.downImagButton.setBackgroundResource(R.mipmap.cai_has_clicked);

                    goodView.setText("+1");
                    goodView.setDistance(1);
                    finalViewHolder.tvDown.setText("" + (listBean.getDown() - 1));
                    finalViewHolder.tvDown.setTextColor(Color.RED);
                    goodView.show(v);
                    listBean.setNum(2);
                    finalViewHolder.upImagButton.setClickable(false);
                    finalViewHolder.downImagButton.setClickable(false);
                }
            }
        });
        viewHolder.forwardLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        viewHolder.commentLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent videoIntent = new Intent(mContext, VideoDetailActivity.class);
                videoIntent.putExtra("video",mDesignerBean);
                videoIntent.putExtra("position",position);
                mContext.startActivity(videoIntent);



            }
        });

        return convertView;
    }


    private String toTime(int time) {

        int minute = time / 60;
        int second = time % 60;
        minute %= 60;
        return String.format("%02d:%02d", minute, second);
    }


    class ViewHolder {
        private TextView tvName;
        private TextView tvTime;
        private TextView tvTitle;
        private TextView tvPlayTimes;
        private TextView tvVideoTime;
        private TextView tvUp;
        private TextView tvDown;
        private TextView tvForward;
        private TextView tvComment;
        private CircleImageView cirImg;
        private JCVideoPlayerStandard jcVideoPlayer;
        private ImageButton upImagButton;
        private ImageButton downImagButton;
        private LinearLayout forwardLL;
        private LinearLayout commentLL;


        public ViewHolder(View view) {
            View viewVideo =  view.findViewById(R.id.include_item_design);


            tvName = (TextView) viewVideo.findViewById(R.id.fragment_design_item_tv_username);
            tvTime = (TextView) viewVideo.findViewById(R.id.fragment_design_item_tv_time);
            tvTitle = (TextView) viewVideo.findViewById(R.id.fragment_design_item_tv_titlce);
            jcVideoPlayer = (JCVideoPlayerStandard) viewVideo.findViewById(R.id.fragment_design_videoplayer);
            cirImg = (CircleImageView) viewVideo.findViewById(R.id.fragment_design_item_user_hander_img);
            tvPlayTimes = (TextView) viewVideo.findViewById(R.id.item_design_play_times_tv);
            tvVideoTime = (TextView) viewVideo.findViewById(R.id.item_design_video_time_tv);
            tvUp = (TextView) viewVideo.findViewById(R.id.item_design_up_tv);
            tvDown = (TextView) viewVideo.findViewById(R.id.item_design_down_tv);
            tvForward = (TextView) viewVideo.findViewById(R.id.item_design_forward_tv);
            tvComment = (TextView) viewVideo.findViewById(R.id.item_design_comment_tv);
            upImagButton = (ImageButton) viewVideo.findViewById(R.id.item_design_up_img);
            downImagButton = (ImageButton) viewVideo.findViewById(R.id.item_design_down_img);
            forwardLL = (LinearLayout) viewVideo.findViewById(R.id.item_design_forward_linear);
            commentLL = (LinearLayout) viewVideo.findViewById(R.id.item_design_comment_linear);





        }
    }
}
