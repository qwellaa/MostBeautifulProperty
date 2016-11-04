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

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 *
 */

public class MagaDetailsActivity extends BaseActivity implements IDiscoverView, BounceScrollView.OnScrollViewListener, View.OnClickListener {

    private CircleImageView mCvReturn, mCvDesignerHead, mCvUserIcon;
    private TextView mTvDesignerName, mTvCity, mTvTitle, mTvSubTitle, mTvUserName, mTvSign;
    private ImageView mIvImage, mSina, mQq,mWeiXin;
    private HtmlTextView mHtmlTextView;
    private LinearLayout mDesignerll;
    private BounceScrollView mScrollView;
    private int mVisibility;
    private String mWebUrl;

    @Override
    protected int setLayout() {
        return R.layout.activity_maga_details;
    }

    @Override
    protected void initView() {
        ShareSDK.initSDK(this);

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

        mSina = bindView(R.id.magazine_details_sina);
        mQq = bindView(R.id.magazine_details_qq);
        mWeiXin = bindView(R.id.magazine_details_weixin);
    }

    @Override
    protected void initData() {
        mCvReturn.setOnClickListener(this);
        mScrollView.setListener(this);
        mSina.setOnClickListener(this);
        mQq.setOnClickListener(this);
        mWeiXin.setOnClickListener(this);

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

            mWebUrl = bean.getData().getWeb_url();
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

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle("「最美有物」全球原创设计师产…");
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(mWebUrl);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我们一起 \n" + "在「最美有物」");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(mWebUrl);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("在乎颜值 \n" + "不讲究不凑合 \n" + "不一样的好品位");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(mWebUrl);

        // 启动分享GUI
        oks.show(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.include_magazine_btn_return:
                finish();
                break;
            case R.id.magazine_details_sina:
                showShare();
                break;
            case R.id.magazine_details_qq:
                showShare();
                break;
            case R.id.magazine_details_weixin:
                showShare();
                break;
        }
    }
}
