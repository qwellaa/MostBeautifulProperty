package com.lanou3g.mostbeautifulproperty.mine.uiview;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseFragment;
import com.lanou3g.mostbeautifulproperty.mine.uiview.chat.LoginActivity;
import com.lanou3g.mostbeautifulproperty.mine.uiview.setting.SettingActivity;

/**
 *
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout mMessageLl;
    private ImageView mSetting;

    @Override
    protected int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        // 消息中心的点击事件
        mMessageLl = bindView(R.id.fragment_mine_message_ll);
        mSetting = bindView(R.id.iv_mine_setting);


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
                Intent loginIntent = new Intent(context,LoginActivity.class);
                startActivity(loginIntent);
                break;
            case R.id.iv_mine_setting:
                Intent settingIntent = new Intent(context, SettingActivity.class);
                startActivity(settingIntent);
                break;
        }

    }
}
