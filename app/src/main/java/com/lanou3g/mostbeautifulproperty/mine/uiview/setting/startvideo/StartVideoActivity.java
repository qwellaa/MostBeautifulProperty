package com.lanou3g.mostbeautifulproperty.mine.uiview.setting.startvideo;

import android.net.Uri;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseActivity;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 *
 */

public class StartVideoActivity extends BaseActivity{

    private VideoView mVideoView;
    private CircleImageView mIvReturn;

    @Override
    protected int setLayout() {
        return R.layout.activity_start_video;
    }

    @Override
    protected void initView() {
        mVideoView = bindView(R.id.start_video);
        mIvReturn = bindView(R.id.start_video_return);
    }

    @Override
    protected void initData() {


        mIvReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        MediaController controller = new MediaController(this);
        mVideoView.setMediaController(controller);
        mVideoView.setVideoURI(Uri.parse("android.resource://com.lanou3g.mostbeautifulproperty/" + R.raw.video));
        mVideoView.start();
    }
}
