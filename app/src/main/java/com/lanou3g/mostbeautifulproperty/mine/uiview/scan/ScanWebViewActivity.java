package com.lanou3g.mostbeautifulproperty.mine.uiview.scan;

import android.content.Intent;
import android.webkit.WebView;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseActivity;

/**
 * Created by dllo on 16/11/11.
 */

public class ScanWebViewActivity extends BaseActivity {

    private WebView mWeb;

    @Override
    protected int setLayout() {
        return R.layout.sanwebview_activity;
    }

    @Override
    protected void initView() {
        mWeb = bindView(R.id.sanwebview_activity_web);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String url = intent.getStringExtra("hh");
        mWeb.loadUrl(url);


    }
}
