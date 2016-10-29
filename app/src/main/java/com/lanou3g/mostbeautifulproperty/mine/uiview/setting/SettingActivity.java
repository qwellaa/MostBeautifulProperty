package com.lanou3g.mostbeautifulproperty.mine.uiview.setting;

import android.view.View;
import android.widget.LinearLayout;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseActivity;

/**
 *
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout mLlPersonal;

    @Override
    protected int setLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        mLlPersonal = bindView(R.id.ll_setting_personal);
    }

    @Override
    protected void initData() {
        mLlPersonal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_setting_personal:
                break;
        }
    }
}
