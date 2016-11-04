package com.lanou3g.mostbeautifulproperty.magazine.uiview;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseActivity;
import com.lanou3g.mostbeautifulproperty.bean.MagaDetailsBean;
import com.lanou3g.mostbeautifulproperty.discover.presenter.DiscoverPresenter;
import com.lanou3g.mostbeautifulproperty.discover.uiview.IDiscoverView;
import com.lanou3g.mostbeautifulproperty.okhttp.URLValues;
import com.lanou3g.mostbeautifulproperty.view.BounceScrollView;
import com.lanou3g.mostbeautifulproperty.view.htmltextview.HtmlTextView;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 *
 */

public class MagaDetailsActivity extends BaseActivity implements IDiscoverView, BounceScrollView.OnScrollViewListener, View.OnClickListener {

    private CircleImageView mCvReturn, mCvDesignerHead, mCvUserIcon;
    private TextView mTvDesignerName, mTvCity, mTvTitle, mTvSubTitle, mTvUserName, mTvSign;
    private ImageView mIvImage;
    private HtmlTextView mHtmlTextView;
    private LinearLayout mDesignerll;
    private BounceScrollView mScrollView;
    private int mVisibility;

    @Override
    protected int setLayout() {
        return R.layout.activity_maga_details;
    }

    @Override
    protected void initView() {
        mDesignerll = bindView(R.id.include_magazine_ll);
        mCvReturn = bindView(R.id.include_magazine_btn_return);
        mCvDesignerHead = bindView(R.id.include_magazine_magazine_icon);
        mTvDesignerName = bindView(R.id.include_magazine_tv_name);
        mTvCity = bindView(R.id.include_magazine_tv_city);

        mScrollView = bindView(R.id.details_bouncescrollView);
        mTvTitle = bindView(R.id.magazine_details_title);
        mTvSubTitle = bindView(R.id.magazine_details_sub_title);
        mIvImage = bindView(R.id.magazine_details_image);

        mCvUserIcon = bindView(R.id.magazine_details_user_icon);
        mTvUserName = bindView(R.id.magazine_details_user_name);
        mTvSign = bindView(R.id.magazine_details_user_sign);

        mHtmlTextView = bindView(R.id.magazine_details_html_text_view);
    }

    @Override
    protected void initData() {
        mCvReturn.setOnClickListener(this);
        mScrollView.setListener(this);

        startRequest();
    }

    private void startRequest() {
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
            if (bean.getData().getDesigners().size() != 0) {
                Glide.with(this).load(bean.getData().getDesigners().get(0).getAvatar_url()).into(mCvDesignerHead);
                mTvDesignerName.setText(bean.getData().getDesigners().get(0).getName());
                mTvCity.setText(bean.getData().getDesigners().get(0).getCity());
            } else {
                mDesignerll.setVisibility(View.GONE);
            }
            mTvTitle.setText(bean.getData().getTitle());
            mTvSubTitle.setText(bean.getData().getSub_title());
            Glide.with(this).load(bean.getData().getImage_url()).into(mIvImage);

            Glide.with(this).load(bean.getData().getAuthor().getAvatar_url()).into(mCvUserIcon);
            mTvUserName.setText(bean.getData().getAuthor().getUsername());
            mTvSign.setText(bean.getData().getAuthor().getSign());

            mHtmlTextView.setHtmlFromString(bean.getData().getContent());

            mVisibility = mDesignerll.getVisibility();
        }
    }

    @Override
    public void onError() {
        Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
    }

    // 上滑
    @Override
    public void onSlide() {
        mCvReturn.setVisibility(View.VISIBLE);
        if (mVisibility == View.VISIBLE) {
            mDesignerll.setVisibility(View.VISIBLE);
        }
    }

    // 下滑
    @Override
    public void onDecline() {
        mCvReturn.setVisibility(View.GONE);
        if (mVisibility == View.VISIBLE) {
            mDesignerll.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.include_magazine_btn_return:
                finish();
                break;
        }
    }
}
