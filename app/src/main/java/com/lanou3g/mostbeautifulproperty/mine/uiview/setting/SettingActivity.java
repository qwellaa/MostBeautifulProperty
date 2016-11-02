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

import com.bumptech.glide.Glide;
import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseActivity;
import com.lanou3g.mostbeautifulproperty.mine.uiview.MineFragment;
import com.lanou3g.mostbeautifulproperty.mine.uiview.setting.clearcache.DataCleanManager;
import com.lanou3g.mostbeautifulproperty.mine.uiview.setting.userfeedback.UserFeedbcakActivity;
import com.lanou3g.mostbeautifulproperty.okhttp.URLValues;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
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

    public static final String HEADICON = "headIcon";

    @Override
    protected int setLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        ShareSDK.initSDK(this);
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

        TextView tvIncludeTitle = bindView(R.id.tv_include_setting_title);
        tvIncludeTitle.setText("设置");
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
        String headIcon = intent.getStringExtra(HEADICON);
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
                Intent intent = new Intent(SettingActivity.this, UserFeedbcakActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_setting_give_praise:
                break;
            case R.id.ll_setting_recommend_friend:
                showShare();
                break;
            case R.id.ll_setting_about_us:
                break;
            case R.id.ll_setting_start_video:
                break;
            case R.id.ll_setting_exit_login:
                exitLoginDialog();
                break;
            case R.id.switch_setting_alerts:
                break;
            case R.id.btn_include_setting_return:
                finish();
                break;
        }
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("「最美有物」全球原创设计师产…");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(URLValues.APP_DOWNLOAD_URL);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我们一起 \n" + "在「最美有物」");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(URLValues.APP_DOWNLOAD_URL);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("在乎颜值 \n" + "不讲究不凑合 \n" + "不一样的好品位");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(URLValues.APP_DOWNLOAD_URL);

        // 启动分享GUI
        oks.show(this);
    }

    private void cacheDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        View viewDialog = LayoutInflater.from(this).inflate(R.layout.dialog_remove, null);
        Button btnCancel = (Button) viewDialog.findViewById(R.id.btn_cancel_dialog);
        Button btnDetermine = (Button) viewDialog.findViewById(R.id.btn_determine_dialog);
        TextView tvClearSubTitle = (TextView) viewDialog.findViewById(R.id.tv_clear_search_history);
        TextView tvClearTitle = (TextView) viewDialog.findViewById(R.id.tv_clear_title);
        tvClearTitle.setText("清除缓存");
        tvClearSubTitle.setText("您确定要清除缓存?");
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

    private void exitLoginDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        View viewDialog = LayoutInflater.from(this).inflate(R.layout.dialog_remove, null);
        Button btnCancel = (Button) viewDialog.findViewById(R.id.btn_cancel_dialog);
        Button btnDetermine = (Button) viewDialog.findViewById(R.id.btn_determine_dialog);
        TextView tvClearSubTitle = (TextView) viewDialog.findViewById(R.id.tv_clear_search_history);
        TextView tvClearTitle = (TextView) viewDialog.findViewById(R.id.tv_clear_title);
        tvClearTitle.setText("退出登录");
        tvClearSubTitle.setText("您确定要退出登录?");
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        btnDetermine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQq = ShareSDK.getPlatform(QQ.NAME);
                mWeibo = ShareSDK.getPlatform(SinaWeibo.NAME);
                if (mQq.isAuthValid()) {
                    mQq.removeAccount(true);
                } else if (mWeibo.isAuthValid()){
                    mWeibo.removeAccount(true);
                }
                Intent intent = new Intent(MineFragment.FILTER_NAME);
                intent.putExtra(MineFragment.USER_NAME, "");
                intent.putExtra(MineFragment.USER_ICON, "");
                sendBroadcast(intent);
                mLlPersonal.setVisibility(View.GONE);
                mLlExitLogin.setVisibility(View.GONE);
                dialog.cancel();
            }
        });
        dialog.setView(viewDialog);
        dialog.show();
    }
}
