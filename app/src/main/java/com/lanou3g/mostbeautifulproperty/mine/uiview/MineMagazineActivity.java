package com.lanou3g.mostbeautifulproperty.mine.uiview;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseActivity;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseViewHolder;
import com.lanou3g.mostbeautifulproperty.baseclass.CurrentAdapter;
import com.lanou3g.mostbeautifulproperty.bean.MyMagazineBean;
import com.lanou3g.mostbeautifulproperty.dbtool.DBTools;
import com.lanou3g.mostbeautifulproperty.magazine.uiview.MagaDetailsActivity;

import java.util.List;

/**
 *
 */

public class MineMagazineActivity extends BaseActivity implements View.OnClickListener {

    private ListView mLv;

    @Override
    protected int setLayout() {
        return R.layout.activity_mine_magazine;
    }

    @Override
    protected void initView() {
        TextView tvTitle = bindView(R.id.tv_include_setting_title);
        ImageView ivReturn = bindView(R.id.btn_include_setting_return);
        mLv = bindView(R.id.mine_magazine_lv);

        tvTitle.setText("收藏的画报");
        ivReturn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        initSetList();

        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyMagazineBean bean = (MyMagazineBean) parent.getItemAtPosition(position);
                Intent intent = new Intent(MineMagazineActivity.this, MagaDetailsActivity.class);
                intent.putExtra(MagaDetailsActivity.KEY_ID, bean.getDetailsId());
                startActivity(intent);
            }
        });
    }

    private void initSetList() {
        DBTools.getInstance().getQueryAll(MyMagazineBean.class, new DBTools.QueryListener<MyMagazineBean>() {
            @Override
            public void onQuery(List<MyMagazineBean> beanArrayList) {

                mLv.setAdapter(new CurrentAdapter<MyMagazineBean>(MineMagazineActivity.this, beanArrayList, R.layout.item_mine_magazine) {
                    @Override
                    public void convert(BaseViewHolder helper, MyMagazineBean item) {
                        helper.setText(R.id.tv_item_mine_magazine_title, item.getTitle());
                        helper.setText(R.id.tv_item_mine_magazine_sub_title, item.getSubTitle());
                        helper.setIamgeGlide(R.id.iv_item_mine_magazine, item.getImageUrl());
                    }
                });
            }

        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_include_setting_return:
                finish();
                break;
        }
    }
}
