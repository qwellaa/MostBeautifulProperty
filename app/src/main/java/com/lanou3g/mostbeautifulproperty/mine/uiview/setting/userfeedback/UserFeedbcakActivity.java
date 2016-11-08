package com.lanou3g.mostbeautifulproperty.mine.uiview.setting.userfeedback;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseActivity;
import com.lanou3g.mostbeautifulproperty.bean.UserFeedbackBean;
import com.lanou3g.mostbeautifulproperty.dbtool.DBTools;
import com.lanou3g.mostbeautifulproperty.mine.uiview.adapter.UserFeedbackAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserFeedbcakActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTvTitle, mTvPhoneWay, mTvAdd, mTvSave, mTvSend;
    private ImageView mIvReturn;
    private LinearLayout mLlPhoneWay, mLlAddPhone;
    private EditText mEtAddPhone, mEtBody;
    private ListView mLvFeedback;
    private SharedPreferences.Editor mSpEt;
    private UserFeedbackReceiver mReceiver;

    private final String SP_CONTACT_KEY = "userFeedbackContact";
    private final String FILTER_FEEDBACK = "userFeedbackBody";
    private UserFeedbackAdapter mAdapter;


    @Override
    protected int setLayout() {
        return R.layout.activity_user_feedbcak;
    }

    @Override
    protected void initView() {
        mTvTitle = bindView(R.id.tv_include_setting_title);
        mIvReturn = bindView(R.id.btn_include_setting_return);
        mLlPhoneWay = bindView(R.id.ll_user_feedback_before_phone);
        mTvPhoneWay = bindView(R.id.tv_user_feedback_before_phone);
        mTvAdd = bindView(R.id.tv_user_feedback_add);
        mLlAddPhone = bindView(R.id.ll_user_feedback_add_phone);
        mEtAddPhone = bindView(R.id.et_user_feedback_add_phone);
        mTvSave = bindView(R.id.tv_user_feedback_save_phone);
        mLvFeedback = bindView(R.id.lv_user_feedback);
        mEtBody = bindView(R.id.et_user_feedback_body);
        mTvSend = bindView(R.id.tv_user_feedback_send);
    }

    @Override
    protected void initData() {
        initSetOnClickListener();

        mTvTitle.setText("用户反馈");

        SharedPreferences sp = getSharedPreferences("launch", MODE_PRIVATE);
        mSpEt = sp.edit();

        String strContact = sp.getString(SP_CONTACT_KEY, "反馈前请添加联系方式(手机, QQ等)");
        if (strContact.equals("")) {
            mTvPhoneWay.setText("反馈前请添加联系方式(手机, QQ等)");
            mEtAddPhone.setText(strContact);
        } else if (strContact.equals("反馈前请添加联系方式(手机, QQ等)")) {
            mEtAddPhone.setText("");
        } else {
            mTvPhoneWay.setText("联系方式 : " + strContact);
            mEtAddPhone.setText(strContact);
        }

        mReceiver = new UserFeedbackReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(FILTER_FEEDBACK);
        registerReceiver(mReceiver, filter);

        mAdapter = new UserFeedbackAdapter(this);
        mLvFeedback.setAdapter(mAdapter);

        dbqueryList();
    }

    private void dbqueryList() {
        DBTools.getInstance().getQueryAll(UserFeedbackBean.class, new DBTools.QueryListener<UserFeedbackBean>() {
            @Override
            public void onQuery(List<UserFeedbackBean> beanArrayList) {
              mAdapter.setList(beanArrayList);
            }
        });
    }

    private void initSetOnClickListener() {
        mIvReturn.setOnClickListener(this);
        mTvAdd.setOnClickListener(this);
        mTvSave.setOnClickListener(this);
        mTvSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_include_setting_return:
                finish();
                break;
            case R.id.tv_user_feedback_add:
                mLlPhoneWay.setVisibility(View.GONE);
                mLlAddPhone.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_user_feedback_save_phone:
                String inputPhone = mEtAddPhone.getText().toString();
                if (inputPhone.equals("")) {
                    mTvPhoneWay.setText("反馈前请添加联系方式(手机, QQ等)");
                    mSpEt.putString(SP_CONTACT_KEY, "");
                    mSpEt.commit();
                } else {
                    mTvPhoneWay.setText("联系方式 : " + inputPhone);
                    mSpEt.putString(SP_CONTACT_KEY, inputPhone);
                    mSpEt.commit();
                }
                mLlPhoneWay.setVisibility(View.VISIBLE);
                mLlAddPhone.setVisibility(View.GONE);
                break;
            case R.id.tv_user_feedback_send:
                SimpleDateFormat formatter = new SimpleDateFormat ("yyyy年MM月dd日 HH:mm:ss ");
                Date curDate = new Date(System.currentTimeMillis());
                String time = formatter.format(curDate);
                String body = mEtBody.getText().toString();

                UserFeedbackBean bean = new UserFeedbackBean();
                bean.setBody(body);
                bean.setTime(time);
                DBTools.getInstance().insertDB(bean);

                dbqueryList();
                mEtBody.setText("");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    private class UserFeedbackReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }
}
