package com.lanou3g.mostbeautifulproperty.mine.uiview.setting;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseActivity;
import com.lanou3g.mostbeautifulproperty.mine.uiview.setting.clearcache.DataCleanManager;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 *
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mLlPersonal;
    private Switch mStAlerts;
    private LinearLayout mLlClearCache;
    private LinearLayout mLlUserFeedback;
    private LinearLayout mLlGivePraise;
    private LinearLayout mLlRecommendFriend;
    private LinearLayout mLlAboutUs;
    private LinearLayout mLlStartVideo;
    private LinearLayout mLlExitLogin;
    private ImageView mIvReturn;
    private TextView mTvCache;
    private Platform mQq;
    private Platform mWeibo;
    private CircleImageView mIvHeadIcon;

    @Override
    protected int setLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        mLlPersonal = bindView(R.id.ll_setting_personal);
        mStAlerts = bindView(R.id.switch_setting_alerts);
        mLlClearCache = bindView(R.id.ll_setting_clear_cache);
        mLlUserFeedback = bindView(R.id.ll_setting_user_feedback);
        mLlGivePraise = bindView(R.id.ll_setting_give_praise);
        mLlRecommendFriend = bindView(R.id.ll_setting_recommend_friend);
        mLlAboutUs = bindView(R.id.ll_setting_about_us);
        mLlStartVideo = bindView(R.id.ll_setting_start_video);
        mLlExitLogin = bindView(R.id.ll_setting_exit_login);
        mIvReturn = bindView(R.id.btn_include_setting_return);

        mIvHeadIcon = bindView(R.id.iv_setting_head_image);
        mTvCache = bindView(R.id.tv_setting_cache);
    }

    @Override
    protected void initData() {
        initSetOnClick();

        try {
            // 获取缓存大小
            String file = DataCleanManager.getTotalCacheSize(this);
            mTvCache.setText(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

        mQq = ShareSDK.getPlatform(QQ.NAME);
        mWeibo = ShareSDK.getPlatform(SinaWeibo.NAME);

        if (mQq.isAuthValid()) {
            mLlPersonal.setVisibility(View.VISIBLE);
            mLlExitLogin.setVisibility(View.VISIBLE);
        } else if (mWeibo.isAuthValid()){
            mLlPersonal.setVisibility(View.VISIBLE);
            mLlExitLogin.setVisibility(View.VISIBLE);
        } else {
            mLlPersonal.setVisibility(View.GONE);
            mLlExitLogin.setVisibility(View.GONE);
        }

        Intent intent = getIntent();
        String headIcon = intent.getStringExtra("headIcon");
        Glide.with(this).load(headIcon).into(mIvHeadIcon);
    }

    private void initSetOnClick() {
        mLlPersonal.setOnClickListener(this);
        mStAlerts.setOnClickListener(this);
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
                cacheDialog();
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
                if (mQq.isAuthValid()) {
                    mQq.removeAccount(true);
                    Intent intent = new Intent("userMessage");
                    intent.putExtra("userName", "");
                    intent.putExtra("userIcon", "");
                    sendBroadcast(intent);
                    mLlPersonal.setVisibility(View.GONE);
                    mLlExitLogin.setVisibility(View.GONE);
                    Toast.makeText(this, "退出登录成功", Toast.LENGTH_SHORT).show();
                } else if (mWeibo.isAuthValid()){
                    mWeibo.removeAccount(true);
                    Intent intent = new Intent("userMessage");
                    intent.putExtra("userName", "");
                    intent.putExtra("userIcon", "");
                    sendBroadcast(intent);
                    mLlPersonal.setVisibility(View.GONE);
                    mLlExitLogin.setVisibility(View.GONE);
                    Toast.makeText(this, "退出登录成功", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.switch_setting_alerts:
                break;
            case R.id.btn_include_setting_return:
                finish();
                break;
        }
    }

    private void cacheDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        View viewDialog = LayoutInflater.from(this).inflate(R.layout.dialog_remove, null);
        Button btnCancel = (Button) viewDialog.findViewById(R.id.btn_cancel_dialog);
        Button btnDetermine = (Button) viewDialog.findViewById(R.id.btn_determine_dialog);
        TextView tvClearTitle = (TextView) viewDialog.findViewById(R.id.tv_clear_search_history);
        tvClearTitle.setText("您确定要清除缓存?");
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        btnDetermine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 清除
                DataCleanManager.clearAllCache(SettingActivity.this);
                try {
                    // 获取大小
                    String file = DataCleanManager.getTotalCacheSize(SettingActivity.this);
                    mTvCache.setText(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dialog.cancel();
            }
        });
        dialog.setView(viewDialog);
        dialog.show();
    }
}
