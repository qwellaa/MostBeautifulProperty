package com.lanou3g.mostbeautifulproperty.mine.uiview;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseFragment;
import com.lanou3g.mostbeautifulproperty.mine.uiview.chat.LoginActivity;
import com.lanou3g.mostbeautifulproperty.mine.uiview.chat.MessageMainActivity;
import com.lanou3g.mostbeautifulproperty.mine.uiview.scan.ScanActivity;
import com.lanou3g.mostbeautifulproperty.mine.uiview.setting.SettingActivity;
import com.lanou3g.mostbeautifulproperty.okhttp.URLValues;

import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import de.hdodenhof.circleimageview.CircleImageView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;


/**
 *
 */

public class MineFragment extends BaseFragment implements View.OnClickListener,EasyPermissions.PermissionCallbacks {

    private LinearLayout mMessageLl;
    private ImageView mSetting;
    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;
    private Button mBtnShare;

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
        CircleImageView cirMyImg = bindView(R.id.fragment_mine_circleimg);
        cirMyImg.setOnClickListener(this);
        mSetting = bindView(R.id.iv_mine_setting);
        // 扫一扫
        ImageView smImg = bindView(R.id.iv_mine_sm);
        smImg.setOnClickListener(this);
        // 分享
        mBtnShare = bindView(R.id.btn_mine_share);
        mBtnShare.setOnClickListener(this);


    }

    @Override
    protected void initData() {
        mMessageLl.setOnClickListener(this);
        mSetting.setOnClickListener(this);
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
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
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
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.fragment_mine_message_ll:
                Intent mesIntent = new Intent(context, MessageMainActivity.class);
                startActivity(mesIntent);
                break;
            case R.id.fragment_mine_circleimg:
                Intent loginIntent = new Intent(context,LoginActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.iv_mine_setting:
                Intent settingIntent = new Intent(context, SettingActivity.class);
                startActivity(settingIntent);
                break;
            case R.id.iv_mine_sm:
                Intent scanIntent = new Intent(context,ScanActivity.class);
                startActivity(scanIntent);
                break;
            case R.id.btn_mine_share:
                showShare();
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
}
