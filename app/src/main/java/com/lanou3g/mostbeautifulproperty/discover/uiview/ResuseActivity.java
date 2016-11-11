package com.lanou3g.mostbeautifulproperty.discover.uiview;

import android.widget.ImageView;
import android.widget.RadioGroup;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseActivity;
import com.lanou3g.mostbeautifulproperty.bean.DiscoverResuseBean;

/**
 * Created by dllo on 16/11/11.
 */

public class ResuseActivity extends BaseActivity implements IDiscoverView<DiscoverResuseBean>{
    @Override
    protected int setLayout() {
        return R.layout.activity_resuse;
    }

    @Override
    protected void initView() {
        ImageView vpPhotp = bindView(R.id.resuse_vp_photo);
        RadioGroup radioGroup = bindView(R.id.ll_resuse_rb);
        
    }



    @Override
    protected void initData() {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void onResponse(DiscoverResuseBean discoverResuseBean) {

    }

    @Override
    public void onError() {

    }
}
