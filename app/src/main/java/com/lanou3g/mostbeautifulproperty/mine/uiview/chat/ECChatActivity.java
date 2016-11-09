package com.lanou3g.mostbeautifulproperty.mine.uiview.chat;

import com.hyphenate.easeui.ui.EaseChatFragment;
import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseActivity;

/**
 * Created by dllo on 16/11/9.
 */

public class ECChatActivity extends BaseActivity {
    private EaseChatFragment chatFragment;
    @Override
    protected int setLayout() {
        return R.layout.ecchatactivity;
    }

    @Override
    protected void initView() {
        //自带的fragment
        chatFragment = new EaseChatFragment();




    }

    @Override
    protected void initData() {
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.ecchat_activity_framelayout, chatFragment).commit();



    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}
