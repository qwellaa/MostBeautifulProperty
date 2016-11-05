package com.lanou3g.mostbeautifulproperty.mine.uiview;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseFragment;
import com.lanou3g.mostbeautifulproperty.mine.uiview.chat.MessageMainActivity;
import com.lanou3g.mostbeautifulproperty.mine.uiview.scan.ScanActivity;
import com.lanou3g.mostbeautifulproperty.mine.uiview.setting.SettingActivity;
import com.lanou3g.mostbeautifulproperty.okhttp.URLValues;
import com.lanou3g.mostbeautifulproperty.view.BounceScrollView;

import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import de.hdodenhof.circleimageview.CircleImageView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


/**
 *
 */

public class MineFragment extends BaseFragment implements View.OnClickListener,EasyPermissions.PermissionCallbacks, BounceScrollView.OnScrollViewListener {

    private LinearLayout mMessageLl;
    private ImageView mSetting;
    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;
    private Button mBtnShare;
    private AlertDialog mDialog;
    private TextView mTvUserName;
    private Platform mWeibo;
    private Platform mQq;
    private CircleImageView mCirMyImg;
    private LinearLayout mLlDaily;
    private LinearLayout mLlAttention;
    private LinearLayout mLlWish;
    private MyDisOrderBroadCastReceiver mReceiver;

    public static final String FILTER_NAME = "userMessage";
    public static final String USER_NAME = "userName";
    public static final String USER_ICON = "userIcon";
    private BounceScrollView mBounceScrollView;

    @Override
    protected int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        ShareSDK.initSDK(context);
        // 消息中心的点击事件
        mMessageLl = bindView(R.id.fragment_mine_message_ll);
        mMessageLl.setOnClickListener(this);
        // 登录头像
        mCirMyImg = bindView(R.id.fragment_mine_circleimg);
        mCirMyImg.setOnClickListener(this);
        // 用户名
        mTvUserName = bindView(R.id.tv_fragment_mine_user_name);
        mSetting = bindView(R.id.iv_mine_setting);
        // 扫一扫
        ImageView smImg = bindView(R.id.iv_mine_sm);
        smImg.setOnClickListener(this);
        // 分享
        mBtnShare = bindView(R.id.btn_mine_share);
        mBtnShare.setOnClickListener(this);

        mLlDaily = bindView(R.id.fragment_mine_daily_ll);
        mLlAttention = bindView(R.id.fragment_mine_attention_ll);
        mLlWish = bindView(R.id.fragment_mine_wish_ll);

        mBounceScrollView = bindView(R.id.mine_bounce_scroll);
    }

    @Override
    protected void initData() {
        mMessageLl.setOnClickListener(this);
        mSetting.setOnClickListener(this);
        mLlDaily.setOnClickListener(this);
        mLlWish.setOnClickListener(this);
        mLlAttention.setOnClickListener(this);
        mBounceScrollView.setListener(this);


        mReceiver = new MyDisOrderBroadCastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(FILTER_NAME);
        context.registerReceiver(mReceiver, filter);
        mDialog = createDialog();


        // 设置微博登录头像
        mWeibo = ShareSDK.getPlatform(SinaWeibo.NAME);
        if (!(mWeibo.getDb().getUserId().equals(""))) {
            mTvUserName.setText(mWeibo.getDb().getUserName());
            Glide.with(this).load(mWeibo.getDb().getUserIcon()).into(mCirMyImg);
        }
        // 设置qq登录头像
        mQq = ShareSDK.getPlatform(QQ.NAME);
        if (!(mQq.getDb().getUserId().equals(""))) {
            mTvUserName.setText(mQq.getDb().getUserName());
            Glide.with(this).load(mQq.getDb().getUserIcon()).into(mCirMyImg);
        }

    }

    private AlertDialog createDialog() {
        final AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCanceledOnTouchOutside(true);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_login, null);
        ImageView ivWeiXin = (ImageView) view.findViewById(R.id.btn_login_weixin);
        ImageView ivQQ = (ImageView) view.findViewById(R.id.btn_login_qq);
        ImageView ivSina = (ImageView) view.findViewById(R.id.btn_login_sina);
        ImageView ivExitLogin = (ImageView) view.findViewById(R.id.iv_login_exit);

        final PlatformActionListener paListener = new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                PlatformDb platDB = platform.getDb();//获取数平台数据DB
                Intent intent = new Intent(FILTER_NAME);
                intent.putExtra(USER_NAME, platDB.getUserName());
                intent.putExtra(USER_ICON, platDB.getUserIcon());
                context.sendBroadcast(intent);
                Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Toast.makeText(context, "取消登录", Toast.LENGTH_SHORT).show();
            }
        };

        ivSina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWeibo.setPlatformActionListener(paListener);
                //authorize与showUser单独调用一个即可
//                weibo.authorize();//单独授权,OnComplete返回的hashmap是空的
                mWeibo.showUser(null);//授权并获取用户信息

                dialog.dismiss();
            }
        });

        ivQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mQq.setPlatformActionListener(paListener);
                //authorize与showUser单独调用一个即可
//                qq.authorize();//单独授权,OnComplete返回的hashmap是空的
                mQq.showUser(null);//授权并获取用户信息
                dialog.dismiss();
            }
        });

        ivWeiXin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ivExitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mQq.isAuthValid()) {
                    mQq.removeAccount(true);
                    Intent intent = new Intent(FILTER_NAME);
                    intent.putExtra(USER_NAME, "");
                    intent.putExtra(USER_ICON, "");
                    context.sendBroadcast(intent);
                    Toast.makeText(context, "退出登录成功", Toast.LENGTH_SHORT).show();
                } else if (mWeibo.isAuthValid()){
                    mWeibo.removeAccount(true);
                    Intent intent = new Intent(FILTER_NAME);
                    intent.putExtra(USER_NAME, "");
                    intent.putExtra(USER_ICON, "");
                    context.sendBroadcast(intent);
                    Toast.makeText(context, "退出登录成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        return dialog;
    }

    private void showShare() {
        ShareSDK.initSDK(context);
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
        oks.show(context);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        context.unregisterReceiver(mReceiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.fragment_mine_message_ll:
                Intent mesIntent = new Intent(context, MessageMainActivity.class);
                startActivity(mesIntent);
                break;
            case R.id.fragment_mine_circleimg:
                mDialog.show();
                break;
            case R.id.iv_mine_setting:
                Intent settingIntent = new Intent(context, SettingActivity.class);

                if (mQq.isAuthValid()) {
                    settingIntent.putExtra(SettingActivity.HEADICON, mQq.getDb().getUserIcon());
                } else if (mWeibo.isAuthValid()){
                    settingIntent.putExtra(SettingActivity.HEADICON, mWeibo.getDb().getUserIcon());
                }
                startActivity(settingIntent);
                break;
            case R.id.iv_mine_sm:
                Intent scanIntent = new Intent(context,ScanActivity.class);
                startActivity(scanIntent);
                break;
            case R.id.btn_mine_share:
                showShare();
                break;
            case R.id.fragment_mine_daily_ll:
                if (mQq.isAuthValid()) {
                    Toast.makeText(context, "qq关注", Toast.LENGTH_SHORT).show();
                } else if (mWeibo.isAuthValid()){
                    Toast.makeText(context, "微博关注", Toast.LENGTH_SHORT).show();
                } else {
                    mDialog.show();
                }
                Intent dailyIntent = new Intent(context, MineMagazineActivity.class);
                startActivity(dailyIntent);
                break;
            case R.id.fragment_mine_attention_ll:
                if (mQq.isAuthValid()) {
                    Toast.makeText(context, "qq关注", Toast.LENGTH_SHORT).show();
                } else if (mWeibo.isAuthValid()){
                    Toast.makeText(context, "微博关注", Toast.LENGTH_SHORT).show();
                } else {
                    mDialog.show();
                }
                break;
            case R.id.fragment_mine_wish_ll:
                if (mQq.isAuthValid()) {
                    Toast.makeText(context, "qq关注", Toast.LENGTH_SHORT).show();
                } else if (mWeibo.isAuthValid()){
                    Toast.makeText(context, "微博关注", Toast.LENGTH_SHORT).show();
                } else {
                    mDialog.show();
                }
                break;
        }

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
       EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults,this);

    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }
    @AfterPermissionGranted(REQUEST_CODE_QRCODE_PERMISSIONS)
    private void requestCodeQrcodePermissions() {
        String[] perms = {Manifest.permission.CAMERA};
        if (!EasyPermissions.hasPermissions(context, perms)) {
            EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和散光灯的权限", REQUEST_CODE_QRCODE_PERMISSIONS, perms);
        }
    }

    @Override
    public void onSlide() {

    }

    @Override
    public void onDecline() {

    }

    private class MyDisOrderBroadCastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String userName = intent.getStringExtra(USER_NAME);
            String userIcon = intent.getStringExtra(USER_ICON);

            if (userName.equals("")) {
                mTvUserName.setText("请登录");
            } else {
                mTvUserName.setText(userName);
            }
            if (userIcon.equals("")) {
                mCirMyImg.setImageResource(R.mipmap.man_selected);
            } else {
                Glide.with(context).load(userIcon).into(mCirMyImg);
            }
        }
    }
}
