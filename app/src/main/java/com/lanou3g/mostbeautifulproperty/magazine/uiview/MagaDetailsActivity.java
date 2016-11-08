package com.lanou3g.mostbeautifulproperty.magazine.uiview;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseActivity;
import com.lanou3g.mostbeautifulproperty.bean.FocusDesignerBean;
import com.lanou3g.mostbeautifulproperty.bean.MagaDetailsBean;
import com.lanou3g.mostbeautifulproperty.bean.MyMagazineBean;
import com.lanou3g.mostbeautifulproperty.dbtool.DBTools;
import com.lanou3g.mostbeautifulproperty.discover.presenter.DiscoverPresenter;
import com.lanou3g.mostbeautifulproperty.discover.uiview.IDiscoverView;
import com.lanou3g.mostbeautifulproperty.okhttp.URLValues;
import com.lanou3g.mostbeautifulproperty.view.BounceScrollView;
import com.lanou3g.mostbeautifulproperty.view.htmltextview.HtmlTextView;

import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 *
 */

public class MagaDetailsActivity extends BaseActivity implements IDiscoverView, BounceScrollView.OnScrollViewListener, View.OnClickListener {

    private CircleImageView mCvReturn, mCvDesignerHead, mCvUserIcon, mModDesignerHead;
    private TextView mTvDesignerName, mTvCity, mTvTitle, mTvSubTitle, mTvUserName, mTvSign, mModDesignerName,
                     mModDesignerLabel, mModDesignerDes;
    private ImageView mIvImage, mSina, mQq,mWeiXin;
    private HtmlTextView mHtmlTextView;
    private LinearLayout mDesignerll, mModDesignerLl;
    private BounceScrollView mScrollView;
    private int mVisibility;
    private String mWebUrl;
    private CheckBox mModDesignerFocus, mCollection;
    private MyMagazineBean mMyMagazineBean;
    private List<MyMagazineBean> mList;
    private List<FocusDesignerBean> mDesignerList;

    public static final String KEY_ID = "id";
    private FocusDesignerBean mDesignerBean;

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

        // 设计师
        mModDesignerLl = bindView(R.id.magazine_details_ll_designer_mod);
        mModDesignerHead = bindView(R.id.magazine_details_cv_designer_head);
        mModDesignerName = bindView(R.id.magazine_details_tv_designer_name);
        mModDesignerLabel = bindView(R.id.magazine_details_tv_designer_label);
        mModDesignerDes = bindView(R.id.magazine_details_tv_designer_description);
        mModDesignerFocus = bindView(R.id.magazine_details_cb_designer_focus);
        // 收藏
        mCollection = bindView(R.id.magazine_details_cb_collection);
    }

    @Override
    protected void initData() {
        mCvReturn.setOnClickListener(this);
        mScrollView.setListener(this);
        mSina.setOnClickListener(this);
        mQq.setOnClickListener(this);
        mWeiXin.setOnClickListener(this);

        startRequest();
        focusOnCheckChange();
    }

    private void focusOnCheckChange() {
        mModDesignerFocus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mModDesignerFocus.setText("取消关注");
                    if (mDesignerList.size() == 0) {
                        DBTools.getInstance().insertDB(mDesignerBean);
                        Toast.makeText(MagaDetailsActivity.this, "关注成功", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    mModDesignerFocus.setText("+关注");
                    DBTools.getInstance().deleteWhere(FocusDesignerBean.class, "designerId", new Integer[]{mDesignerBean.getDesignerId()});
                    Toast.makeText(MagaDetailsActivity.this, "取消关注", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mCollection.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (mList.size() == 0) {
                        DBTools.getInstance().insertDB(mMyMagazineBean);
                        Toast.makeText(MagaDetailsActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    DBTools.getInstance().deleteWhere(MyMagazineBean.class, "detailsId", new Integer[]{mMyMagazineBean.getDetailsId()});
                    Toast.makeText(MagaDetailsActivity.this, "取消收藏", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startRequest() {
        Intent intent = getIntent();
        int id = intent.getIntExtra(KEY_ID, 0);

        // 收藏用到的 MyMagazineBean
        mMyMagazineBean = new MyMagazineBean();
        mMyMagazineBean.setDetailsId(id);

        final DiscoverPresenter presenter = new DiscoverPresenter(this);
        presenter.startRequest(URLValues.getMAGAZINEDETAILS_URL(id), MagaDetailsBean.class);

        DBTools.getInstance().getQueryByWhere(MyMagazineBean.class, "detailsId", new Integer[]{id}, new DBTools.QueryListener<MyMagazineBean>() {
            @Override
            public void onQuery(List<MyMagazineBean> beanArrayList) {
                mList = beanArrayList;
                if (mList.size() != 0) {
                    mCollection.setChecked(true);
                } else {
                    mCollection.setChecked(false);
                }
            }
        });
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
            mDesignerBean = new FocusDesignerBean();
            if (bean.getData().getDesigners().size() != 0) {
                MagaDetailsBean.Databean.DesignersBean designersBean = bean.getData().getDesigners().get(0);
                Glide.with(this).load(designersBean.getAvatar_url()).into(mCvDesignerHead);
                mTvDesignerName.setText(designersBean.getName());
                mTvCity.setText(designersBean.getCity());

                Glide.with(this).load(designersBean.getAvatar_url()).into(mModDesignerHead);
                mModDesignerName.setText(designersBean.getName());
                mModDesignerLabel.setText(designersBean.getLabel());
                mModDesignerDes.setText(designersBean.getDescription());

                // 给 关注设计师bean类 赋值
                mDesignerBean.setDesignerId(designersBean.getId());
                mDesignerBean.setImageUrl(bean.getData().getImage_url());
                mDesignerBean.setName(designersBean.getName());
                mDesignerBean.setConcept(designersBean.getConcept());
                mDesignerBean.setFollowNum(designersBean.getFollow_num());
                mDesignerBean.setIconHeadUrl(designersBean.getAvatar_url());
                mDesignerBean.setLabel(designersBean.getLabel());

                DBTools.getInstance().getQueryByWhere(FocusDesignerBean.class, "designerId", new Integer[]{designersBean.getId()}, new DBTools.QueryListener<FocusDesignerBean>() {
                    @Override
                    public void onQuery(List<FocusDesignerBean> beanArrayList) {
                        mDesignerList = beanArrayList;
                        if (mDesignerList.size() != 0) {
                            mModDesignerFocus.setChecked(true);
                        } else {
                            mModDesignerFocus.setChecked(false);
                        }
                    }
                });
            } else {
                mDesignerll.setVisibility(View.GONE);
                mModDesignerLl.setVisibility(View.GONE);
            }
            mTvTitle.setText(bean.getData().getTitle());
            mTvSubTitle.setText(bean.getData().getSub_title());
            Glide.with(this).load(bean.getData().getImage_url()).into(mIvImage);

            Glide.with(this).load(bean.getData().getAuthor().getAvatar_url()).into(mCvUserIcon);
            mTvUserName.setText(bean.getData().getAuthor().getUsername());
            mTvSign.setText(bean.getData().getAuthor().getSign());

            mHtmlTextView.setHtmlFromString(bean.getData().getContent());

            mVisibility = mDesignerll.getVisibility();

            // 分享网址
            mWebUrl = bean.getData().getWeb_url();

            // 给 收藏画报bean类 MyMagazineBean 赋值
            mMyMagazineBean.setTitle(bean.getData().getTitle());
            mMyMagazineBean.setSubTitle(bean.getData().getSub_title());
            mMyMagazineBean.setImageUrl(bean.getData().getImage_url());

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
        oks.setTitle(mMyMagazineBean.getTitle());
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(mWebUrl);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(mMyMagazineBean.getSubTitle());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        oks.setImageUrl(mMyMagazineBean.getImageUrl());
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
