package com.lanou3g.mostbeautifulproperty.designer.uiview;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseActivity;

/**
 * Created by dllo on 16/11/5.
 */

public class ImgDetailActivity extends BaseActivity implements  View.OnClickListener {

    private ImageView mDeTailImageView;

    @Override
    protected int setLayout() {
        return R.layout.imgdetail_activity;
    }

    @Override
    protected void initView() {
        mDeTailImageView = bindView(R.id.imagedetail_comment_img);
        mDeTailImageView.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String imgUrl = intent.getStringExtra("commentURL");
        Glide.with(this).load(imgUrl).into(mDeTailImageView);
    }




    @Override
    public void onClick(View v) {
        this.finish();
    }
}
