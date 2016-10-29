package com.lanou3g.mostbeautifulproperty.mine.uiview.setting;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseActivity;

/**
 *
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mLlPersonal;
    private SeekBar mSbAlerts;
    private LinearLayout mLlClearCache;
    private LinearLayout mLlUserFeedback;
    private LinearLayout mLlGivePraise;
    private LinearLayout mLlRecommendFriend;
    private LinearLayout mLlAboutUs;
    private LinearLayout mLlStartVideo;
    private LinearLayout mLlExitLogin;
    private ImageView mIvReturn;
    private TextView mTvCache;

    @Override
    protected int setLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        mLlPersonal = bindView(R.id.ll_setting_personal);
        mSbAlerts = bindView(R.id.seek_bar_setting_alerts);
        mLlClearCache = bindView(R.id.ll_setting_clear_cache);
        mLlUserFeedback = bindView(R.id.ll_setting_user_feedback);
        mLlGivePraise = bindView(R.id.ll_setting_give_praise);
        mLlRecommendFriend = bindView(R.id.ll_setting_recommend_friend);
        mLlAboutUs = bindView(R.id.ll_setting_about_us);
        mLlStartVideo = bindView(R.id.ll_setting_start_video);
        mLlExitLogin = bindView(R.id.ll_setting_exit_login);
        mIvReturn = bindView(R.id.btn_include_setting_return);

        mTvCache = bindView(R.id.tv_setting_cache);
    }

    @Override
    protected void initData() {
        initSetOnClick();
    }

    private void initSetOnClick() {
        mLlPersonal.setOnClickListener(this);
        mSbAlerts.setOnClickListener(this);
        mLlClearCache.setOnClickListener(this);
        mLlUserFeedback.setOnClickListener(this);
        mLlGivePraise.setOnClickListener(this);
        mLlRecommendFriend.setOnClickListener(this);
        mLlAboutUs.setOnClickListener(this);
        mLlStartVideo.setOnClickListener(this);
        mLlExitLogin.setOnClickListener(this);
        mIvReturn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_setting_personal:
                break;
            case R.id.ll_setting_clear_cache:
                break;
            case R.id.ll_setting_user_feedback:
                break;
            case R.id.ll_setting_give_praise:
                break;
            case R.id.ll_setting_recommend_friend:
                break;
            case R.id.ll_setting_about_us:
                break;
            case R.id.ll_setting_start_video:
                break;
            case R.id.ll_setting_exit_login:
                break;
            case R.id.seek_bar_setting_alerts:
                break;
            case R.id.btn_include_setting_return:
                finish();
                break;
        }
    }
}
