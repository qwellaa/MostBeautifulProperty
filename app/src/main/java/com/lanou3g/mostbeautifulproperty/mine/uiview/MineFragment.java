package com.lanou3g.mostbeautifulproperty.mine.uiview;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseFragment;
import com.lanou3g.mostbeautifulproperty.mine.uiview.chat.LoginActivity;

/**
 *
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout mMessageLl;

    @Override
    protected int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        // 消息中心的点击事件
        mMessageLl = bindView(R.id.fragment_mine_message_ll);
        mMessageLl.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_mine_message_ll:
                Intent loginIntent = new Intent(getContext(),LoginActivity.class);
                startActivity(loginIntent);
                break;
        }

    }
}
