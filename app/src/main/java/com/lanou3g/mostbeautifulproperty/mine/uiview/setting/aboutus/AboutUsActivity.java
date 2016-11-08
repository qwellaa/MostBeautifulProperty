package com.lanou3g.mostbeautifulproperty.mine.uiview.setting.aboutus;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseActivity;
import com.lanou3g.mostbeautifulproperty.view.BounceScrollView;

/**
 *
 */

public class AboutUsActivity extends BaseActivity implements BounceScrollView.OnScrollViewListener {

    private TextView mTvBody;
    private TextView mTvTitle;
    private ImageView mIvReturn;
    private BounceScrollView mBounceScrollView;

    @Override
    protected int setLayout() {
        return R.layout.activity_about_us;
    }

    @Override
    protected void initView() {
        mBounceScrollView = bindView(R.id.about_us_bounce_scroll);
        mTvBody = bindView(R.id.tv_about_us_body);
        mTvTitle = bindView(R.id.tv_include_setting_title);
        mIvReturn = bindView(R.id.btn_include_setting_return);
    }

    @Override
    protected void initData() {
        mBounceScrollView.setListener(this);
        mTvTitle.setText("关于我们");
        mTvBody.setText(R.string.aboutUs);

        mIvReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onSlide() {

    }

    @Override
    public void onDecline() {

    }
}
