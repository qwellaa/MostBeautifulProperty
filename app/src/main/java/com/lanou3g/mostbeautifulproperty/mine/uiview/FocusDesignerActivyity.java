package com.lanou3g.mostbeautifulproperty.mine.uiview;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseActivity;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseViewHolder;
import com.lanou3g.mostbeautifulproperty.baseclass.CurrentAdapter;
import com.lanou3g.mostbeautifulproperty.bean.FocusDesignerBean;
import com.lanou3g.mostbeautifulproperty.dbtool.DBTools;

import java.util.List;

/**
 *
 */

public class FocusDesignerActivyity extends BaseActivity implements View.OnClickListener {

    private ListView mLvFocus;

    @Override
    protected int setLayout() {
        return R.layout.activity_focus_designer;
    }

    @Override
    protected void initView() {
        TextView tvTitle = bindView(R.id.tv_include_setting_title);
        ImageView ivReturn = bindView(R.id.btn_include_setting_return);
        mLvFocus = bindView(R.id.lv_focus_designer);

        tvTitle.setText("关注的设计师");
        ivReturn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        DBTools.getInstance().getQueryAll(FocusDesignerBean.class, new DBTools.QueryListener<FocusDesignerBean>() {
            @Override
            public void onQuery(List<FocusDesignerBean> beanArrayList) {
                mLvFocus.setAdapter(new CurrentAdapter<FocusDesignerBean>(FocusDesignerActivyity.this, beanArrayList, R.layout.item_focus_designer) {
                    @Override
                    public void convert(BaseViewHolder helper, FocusDesignerBean item) {
                        helper.setIamgeGlide(R.id.iv_item_focus_designer, item.getImageUrl());
                        helper.setText(R.id.tv_item_focus_designer_name, item.getName());
                        helper.setText(R.id.tv_item_focus_designer_title, item.getLabel());
                        helper.setText(R.id.tv_item_focus_designer_sub_title, item.getConcept());
                        helper.setIamgeGlide(R.id.cv_item_focus_designer_head, item.getIconHeadUrl());
                        helper.setText(R.id.tv_item_focus_designer_follow, item.getFollowNum() + " 关注者");
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_include_setting_return:
                finish();
                break;
        }
    }
}
