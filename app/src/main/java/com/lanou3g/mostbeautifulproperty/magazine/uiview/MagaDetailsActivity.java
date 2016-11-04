package com.lanou3g.mostbeautifulproperty.magazine.uiview;

import android.content.Intent;
import android.util.Log;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseActivity;
import com.lanou3g.mostbeautifulproperty.bean.MagaDetailsBean;
import com.lanou3g.mostbeautifulproperty.discover.presenter.DiscoverPresenter;
import com.lanou3g.mostbeautifulproperty.discover.uiview.IDiscoverView;
import com.lanou3g.mostbeautifulproperty.okhttp.URLValues;


/**
 *
 */

public class MagaDetailsActivity extends BaseActivity implements IDiscoverView {
    @Override
    protected int setLayout() {
        return R.layout.activity_maga_details;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        Log.d("MagaDetailsActivity", "id:" + id);
        final DiscoverPresenter presenter = new DiscoverPresenter(this);
        presenter.startRequest(URLValues.getMAGAZINEDETAILS_URL(id), MagaDetailsBean.class);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void onResponse(Object result) {
        if (result instanceof MagaDetailsBean) {
            MagaDetailsBean bean = (MagaDetailsBean) result;
            Log.d("MagaDetailsActivity", bean.getData().getTitle());
        }
    }

    @Override
    public void onError() {

    }
}
