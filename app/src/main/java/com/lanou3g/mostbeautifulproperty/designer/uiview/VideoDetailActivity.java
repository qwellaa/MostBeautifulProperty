package com.lanou3g.mostbeautifulproperty.designer.uiview;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseActivity;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseViewHolder;
import com.lanou3g.mostbeautifulproperty.baseclass.CurrentAdapter;
import com.lanou3g.mostbeautifulproperty.bean.DesignViedeDedailBean;
import com.lanou3g.mostbeautifulproperty.bean.DesignerBean;
import com.lanou3g.mostbeautifulproperty.designer.presenter.DesignerPresenter;
import com.lanou3g.mostbeautifulproperty.okhttp.URLValues;
import com.wx.goodview.GoodView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

import static com.lanou3g.mostbeautifulproperty.R.id.video_detail_comment_img;

/**
 * Created by dllo on 16/11/4.
 */

public class VideoDetailActivity extends BaseActivity implements IDesignerView<DesignViedeDedailBean> {
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
    private ListView mHotCommentListView;
    private CurrentAdapter<DesignViedeDedailBean.HotBean.ListBean> hotAdapter;
    private ImageView mCommentDetailImg;
    private SensorManager mSensorManager;
    private JCVideoPlayer.JCAutoFullscreenListener sensorEventListner;

    @Override
    protected int setLayout() {
        return R.layout.videodetailactivity;
    }

    @Override
    protected void initView() {
        //View viewInclude =  findViewById(R.id.include_item_video_detail);
        View viewTop = LayoutInflater.from(this).inflate(R.layout.videodetail_top, null);
        View view = viewTop.findViewById(R.id.include_item_video_detail);
        tvName = bindView(R.id.fragment_design_item_tv_username, view);
        tvTime = bindView(R.id.fragment_design_item_tv_time, view);
        tvTitle = bindView(R.id.fragment_design_item_tv_titlce, view);
        jcVideoPlayer = bindView(R.id.fragment_design_videoplayer, view);
        cirImg = bindView(R.id.fragment_design_item_user_hander_img, view);
        tvPlayTimes = bindView(R.id.item_design_play_times_tv, view);
        tvVideoTime = bindView(R.id.item_design_video_time_tv, view);
        tvUp = bindView(R.id.item_design_up_tv, view);
        tvDown = bindView(R.id.item_design_down_tv, view);
        tvForward = bindView(R.id.item_design_forward_tv, view);
        tvComment = bindView(R.id.item_design_comment_tv, view);
        upImagButton = bindView(R.id.item_design_up_img, view);
        downImagButton = bindView(R.id.item_design_down_img, view);
        forwardLL = bindView(R.id.item_design_forward_linear, view);
        commentLL = bindView(R.id.item_design_comment_linear, view);
        mHotCommentListView = bindView(R.id.video_detail_listview);
        mHotCommentListView.addHeaderView(viewTop);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensorEventListner = new JCVideoPlayer.JCAutoFullscreenListener();
        //评论详情中布局






    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        mBean = (DesignerBean) intent.getSerializableExtra("video");
        mPosition = intent.getIntExtra("position", 1);
        mVideoBean = mBean.getList().get(mPosition).getVideo();
        mTime = mBean.getList().get(mPosition).getVideo().getDuration();
        mListBean = mBean.getList().get(mPosition);
        initSetDetail();
        DesignerPresenter presenter = new DesignerPresenter(this);
        presenter.startRequest(URLValues.getVideoDetailUrl(mListBean.getId()), DesignViedeDedailBean.class);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(sensorEventListner, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onBackPressed() {


        if (JCVideoPlayer.backPress()) {
            return;
        }



         super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(sensorEventListner);
        JCVideoPlayer.releaseAllVideos();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
        }
        return super.onOptionsItemSelected(item);
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

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void onResponse(DesignViedeDedailBean designViedeDedailBean) {
        List<DesignViedeDedailBean.HotBean.ListBean> dedailList = designViedeDedailBean.getHot().getList();
        List<DesignViedeDedailBean.NormalBean.ListBean> listBeen = designViedeDedailBean.getNormal().getList();
        mHotCommentListView.setAdapter(hotAdapter = new CurrentAdapter<DesignViedeDedailBean.HotBean.ListBean>(this, dedailList, R.layout.itemvideo_detail) {
            @Override
            public void convert(BaseViewHolder helper, final DesignViedeDedailBean.HotBean.ListBean item) {

                helper.setIamgeGlide(R.id.video_detail_comment_title_circle, item.getUser().getProfile_image());
                helper.setText(R.id.video_detail_username_tv, item.getUser().getUsername());
                helper.setText(R.id.video_detail_up_tv, item.getLike_count() + "");
                if (item.getType().equals("text")) {
                    helper.setViewVisible(video_detail_comment_img,View.GONE);
                    helper.setText(R.id.video_detail_comment_tv, item.getContent());
                } else if (item.getType().equals("image")) {

                    helper.setText(R.id.video_detail_comment_tv, item.getContent());
                    helper.setIamgeGlide(R.id.video_detail_comment_img, item.getImage().getThumbnail().get(0));
                    helper.setViewVisible(R.id.video_detail_comment_img,View.VISIBLE);
                    helper.setOnClickImg(R.id.video_detail_comment_img, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent commentImgIntent = new Intent(VideoDetailActivity.this,ImgDetailActivity.class);
                            commentImgIntent.putExtra("commentURL",item.getImage().getImages().get(0));
                            startActivity(commentImgIntent);

                        }
                    });


                }


            }
        });

//


    }

    @Override
    public void onError() {

    }

}
