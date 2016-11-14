package com.lanou3g.mostbeautifulproperty.discover.uiview;

import android.content.Intent;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseActivity;
import com.lanou3g.mostbeautifulproperty.bean.DiscoverResuseBean;
import com.lanou3g.mostbeautifulproperty.discover.presenter.DiscoverPresenter;
import com.lanou3g.mostbeautifulproperty.okhttp.URLValues;

/**
 * Created by dllo on 16/11/11.
 */

public class ResuseActivity extends BaseActivity implements IDiscoverView<DiscoverResuseBean>{

    private ImageView mVpPhoto;
    private RadioGroup mRadioGroup;
    private ImageView mIvPageOne;
    private ImageView mIvPageTwo;
    private ImageView mIvPageThree;
    private TextView mTvPageOne;
    private TextView mTvPageTwo;
    private TextView mTvPageThree;
    private TextView mTitle;
    private TextView mProperty;
    private ImageView mIvPhotoOne;
    private ImageView mIvPhoto;
    private TextView mName;
    private TextView mPerson;
    private TextView mContent;

    @Override
    protected int setLayout() {
        return R.layout.activity_resuse;
    }

    @Override
    protected void initView() {
        mVpPhoto = bindView(R.id.resuse_vp_photo);
        mRadioGroup = bindView(R.id.ll_resuse_rb);
        mIvPageOne = bindView(R.id.iv_resuse_page_one);
        mIvPageTwo = bindView(R.id.iv_resuse_page_two);
        mIvPageThree = bindView(R.id.iv_resuse_page_three);
        mTvPageOne = bindView(R.id.tv_resuse_page_one);
        mTvPageTwo = bindView(R.id.tv_resuse_page_two);
        mTvPageThree = bindView(R.id.tv_resuse_page_three);
        mTitle = bindView(R.id.tv_resuse_title);
        mProperty = bindView(R.id.tv_resuse_property);
        mIvPhotoOne = bindView(R.id.iv_resuse_photo_one);
        mIvPhoto = bindView(R.id.iv_resuse_photo);
        mName = bindView(R.id.tv_resuse_name);
        mPerson = bindView(R.id.tv_resuse_person);
        mContent = bindView(R.id.tv_resuse_content);
    }



    @Override
    protected void initData() {
        Intent intent = getIntent();
        int infoId = intent.getIntExtra("infoId",1);
        DiscoverPresenter presenter = new DiscoverPresenter(this);
        presenter.startRequest(URLValues.getResuseFragment() + infoId,DiscoverResuseBean.class);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void onResponse(DiscoverResuseBean discoverResuseBean) {
        DiscoverResuseBean.DataBean bean = discoverResuseBean.getData();
        Glide.with(this).load(bean.getCover_images().get(0)).into(mVpPhoto);
        Glide.with(this).load(bean.getDesc_types().get(0).getImage_url()).into(mIvPageOne);
        Glide.with(this).load(bean.getDesc_types().get(1).getImage_url()).into(mIvPageTwo);
        Glide.with(this).load(bean.getDesc_types().get(2).getImage_url()).into(mIvPageThree);
        mTvPageOne.setText(bean.getDesc_types().get(0).getDesc());
        mTvPageTwo.setText(bean.getDesc_types().get(1).getDesc());
        mTvPageThree.setText(bean.getDesc_types().get(2).getDesc());
        mTitle.setText(bean.getName());
        mProperty.setText(bean.getDigest());
        Glide.with(this).load(bean.getImages().get(0)).into(mIvPhotoOne);
        Glide.with(this).load(bean.getDesigner().getAvatar_url()).into(mIvPhoto);
        mName.setText(bean.getDesigner().getName());
        mPerson.setText(bean.getDesigner().getLabel());
        mContent.setText(bean.getDesigner().getDescription());

    }

    @Override
    public void onError() {

    }
}
