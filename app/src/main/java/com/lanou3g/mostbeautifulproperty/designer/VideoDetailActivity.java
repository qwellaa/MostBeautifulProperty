package com.lanou3g.mostbeautifulproperty.designer;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseActivity;
import com.lanou3g.mostbeautifulproperty.bean.DesignerBean;
import com.wx.goodview.GoodView;

import de.hdodenhof.circleimageview.CircleImageView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by dllo on 16/11/4.
 */

public class VideoDetailActivity extends BaseActivity {
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
    private DesignerBean mBean;
    private int mPosition;
    private DesignerBean.ListBean.VideoBean mVideoBean;
    private DesignerBean.ListBean mListBean;
    private int mTime;

    @Override
    protected int setLayout() {
        return R.layout.videodetailactivity;
    }

    @Override
    protected void initView() {
        View viewInclude =  findViewById(R.id.include_item_video_detail);
        tvName = (TextView) viewInclude.findViewById(R.id.fragment_design_item_tv_username);
        tvTime = (TextView) viewInclude.findViewById(R.id.fragment_design_item_tv_time);
        tvTitle = (TextView) viewInclude.findViewById(R.id.fragment_design_item_tv_titlce);
        jcVideoPlayer = (JCVideoPlayerStandard) viewInclude.findViewById(R.id.fragment_design_videoplayer);
        cirImg = (CircleImageView) viewInclude.findViewById(R.id.fragment_design_item_user_hander_img);
        tvPlayTimes = (TextView) viewInclude.findViewById(R.id.item_design_play_times_tv);
        tvVideoTime = (TextView) viewInclude.findViewById(R.id.item_design_video_time_tv);
        tvUp = (TextView) viewInclude.findViewById(R.id.item_design_up_tv);
        tvDown = (TextView) viewInclude.findViewById(R.id.item_design_down_tv);
        tvForward = (TextView) viewInclude.findViewById(R.id.item_design_forward_tv);
        tvComment = (TextView) viewInclude.findViewById(R.id.item_design_comment_tv);
        upImagButton = (ImageButton) viewInclude.findViewById(R.id.item_design_up_img);
        downImagButton = (ImageButton) viewInclude.findViewById(R.id.item_design_down_img);
        forwardLL = (LinearLayout) viewInclude.findViewById(R.id.item_design_forward_linear);
        commentLL = (LinearLayout) viewInclude.findViewById(R.id.item_design_comment_linear);






    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mBean = (DesignerBean) intent.getSerializableExtra("video");
        mPosition = intent.getIntExtra("position",1);
        mVideoBean = mBean.getList().get(mPosition).getVideo();
        mTime = mBean.getList().get(mPosition).getVideo().getDuration();
        mListBean = mBean.getList().get(mPosition);
        initSetDetail();



    }
    private String toTime(int time) {

        int minute = time / 60;
        int second = time % 60;
        minute %= 60;
        return String.format("%02d:%02d", minute, second);
    }


    private void initSetDetail() {


        jcVideoPlayer.setUp(mVideoBean.getVideo().get(0)
                , JCVideoPlayer.SCREEN_LAYOUT_LIST, "");
        Glide.with(this).load(mVideoBean.getThumbnail().get(0))
                .into(jcVideoPlayer.thumbImageView);
        Glide.with(this).load(mBean.getList().get(mPosition).getU().getHeader().get(0)).
                into(cirImg);
        jcVideoPlayer.thumbImageView.setBackgroundColor(Color.WHITE);


        tvTime.setText(mBean.getList().get(mPosition).getPasstime());
        tvTitle.setText(mBean.getList().get(mPosition).getText());
        tvName.setText(mBean.getList().get(mPosition).getU().getName());
        tvPlayTimes.setText(mVideoBean.getPlaycount() + "次播放");

        tvVideoTime.setText(toTime(mTime));
        tvUp.setText(mListBean.getUp());
        tvDown.setText(mListBean.getDown() + "");
        tvComment.setText(mListBean.getComment());
        tvForward.setText(mListBean.getForward() + "");

        switch (mListBean.getNum()) {
            case 0:
                upImagButton.setBackgroundResource(R.mipmap.ding_not_clicked);
                downImagButton.setBackgroundResource(R.mipmap.cai_not_clicked);
                tvUp.setTextColor(Color.GRAY);
                tvDown.setTextColor(Color.GRAY);
                downImagButton.setFocusable(true);
                upImagButton.setFocusable(true);
                break;

            case 1:
               upImagButton.setBackgroundResource(R.mipmap.ding_has_clicked);
                tvUp.setTextColor(Color.RED);
                downImagButton.setFocusable(false);
                upImagButton.setFocusable(false);

                break;

            case 2:
                downImagButton.setBackgroundResource(R.mipmap.cai_has_clicked);
                tvDown.setTextColor(Color.RED);
                upImagButton.setFocusable(false);
                downImagButton.setFocusable(false);

                break;


        }
        final GoodView goodView = new GoodView(this);



        upImagButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (upImagButton.isFocusable()) {

                    upImagButton.setBackgroundResource(R.mipmap.ding_has_clicked);

                    downImagButton.setClickable(false);
                    tvUp.setText("" + ((Integer.decode(mListBean.getUp())) + 1));
                    mListBean.setNum(1);
                    goodView.setText("+1");
                    goodView.setTranslateY(upImagButton.getBottom(), upImagButton.getTop());
                    tvUp.setTextColor(Color.RED);
                    goodView.show(v);
                    upImagButton.setClickable(false);
                }
            }
        });

       downImagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (downImagButton.isFocusable()) {
                    downImagButton.setBackgroundResource(R.mipmap.cai_has_clicked);

                    goodView.setText("+1");
                    goodView.setDistance(1);
                    tvDown.setText("" + (mListBean.getDown() - 1));
                    tvDown.setTextColor(Color.RED);
                    goodView.show(v);
                    mListBean.setNum(2);
                     upImagButton.setClickable(false);
                    downImagButton.setClickable(false);
                }
            }
        });


    }
}
