package com.lanou3g.mostbeautifulproperty.mine.uiview;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseFragment;
import com.lanou3g.mostbeautifulproperty.mine.uiview.chat.LoginActivity;
import com.lanou3g.mostbeautifulproperty.mine.uiview.chat.MessageMainActivity;
import com.lanou3g.mostbeautifulproperty.mine.uiview.scan.ScanActivity;
import com.lanou3g.mostbeautifulproperty.mine.uiview.setting.SettingActivity;

import java.util.List;

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
    @Override
    protected int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        // 消息中心的点击事件
        mMessageLl = bindView(R.id.fragment_mine_message_ll);
        mMessageLl.setOnClickListener(this);
        // 登录头像
        CircleImageView cirMyImg = bindView(R.id.fragment_mine_circleimg);
        cirMyImg.setOnClickListener(this);
        mSetting = bindView(R.id.iv_mine_setting);
        //扫一扫
        ImageView smImg = bindView(R.id.iv_mine_sm);
        smImg.setOnClickListener(this);



    }

    @Override
    protected void initData() {
        mMessageLl.setOnClickListener(this);
        mSetting.setOnClickListener(this);
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
