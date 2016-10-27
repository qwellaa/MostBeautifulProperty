package com.lanou3g.mostbeautifulproperty.discover.uiview;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseFragment;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseViewHolder;
import com.lanou3g.mostbeautifulproperty.baseclass.CurrentAdapter;
import com.lanou3g.mostbeautifulproperty.bean.PopupwindowBean;
import com.lanou3g.mostbeautifulproperty.discover.discoverpresenter.DiscoverPresenter;
import com.lanou3g.mostbeautifulproperty.homepage.MainActivity;
import com.lanou3g.mostbeautifulproperty.okhttp.URLValues;

import java.util.ArrayList;

import static com.lanou3g.mostbeautifulproperty.R.id.item_popupwindow_tv;

/**
 *
 */

public class DiscoverReuseFragment extends BaseFragment implements View.OnClickListener, IDiscoverView<PopupwindowBean> {

    private static final int TABMEN = 6;

    private TextView mTv;
    private RelativeLayout mMoreTopView;
    private TextView mTitcleTv;
    private ImageView mTitcleImg;
    private View mView;
    private PopupWindow mTitclePopupWindow;
    private TableLayout tb;
    private TextView mLocationTv;
    private GridView mPopupWindowGrideView;
    private int mPosition;
    private DiscoverPresenter mPresenter;
    private CurrentAdapter popupAdapter;
    private TextView mItemTv;
    private ImageView mUpImg;


    public static DiscoverReuseFragment newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt("position", position);
        DiscoverReuseFragment fragment = new DiscoverReuseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_discover_reuse;
    }

    @Override
    protected void initView() {
        tb = bindView(R.id.tab_discover);
        //  mTv = bindView(R.id.tv_discover_reuse);
        mLocationTv = bindView(R.id.dingwei);
        mMoreTopView = bindView(R.id.reuse_discoverfragment_relative);
        mTitcleTv = bindView(R.id.fragment_discover_all_tv);
        mMoreTopView.setOnClickListener(this);
        mView = LayoutInflater.from(getContext()).inflate(R.layout.top_popupwindow, null);
        mUpImg = bindView(R.id.popupwindow_up_img,mView);
        mUpImg.setOnClickListener(this);
        mPopupWindowGrideView = bindView(R.id.popuuwindow_gridview, mView);
        mItemTv = bindView(R.id.item_popupwindow_tv);




    }

    @Override
    protected void initData() {
        Bundle args = getArguments();
        mPosition = args.getInt("position");
        if (TABMEN == mPosition) {
            mMoreTopView.setVisibility(View.INVISIBLE);

        } else {


            mPresenter = new DiscoverPresenter(this);
            mPresenter.startRequest(URLValues.POPUPWINDOW_URL);

        }


    }

    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    if (mTitclePopupWindow != null && mTitclePopupWindow.isShowing()) {
                        mTitclePopupWindow.dismiss();
                        return true;
                    }
                }
                return false;
            }
        });
    }


    private void initOnTouchLisenner() {

//        * Fragment中，注册
//                * 接收MainActivity的Touch回调的对象
//                * 重写其中的onTouchEvent函数，并进行该Fragment的逻辑处理
//                */
        MainActivity.MyTouchListener myTouchListener = new MainActivity.MyTouchListener() {
            @Override
            public void onTouchEvent(MotionEvent event) {
                // 处理手势事件

                if (mTitclePopupWindow.isShowing()) {
                    mTitclePopupWindow.dismiss();
                }

            }
        };

        // 将myTouchListener注册到分发列表
        ((MainActivity) this.getActivity()).registerMyTouchListener(myTouchListener);

    }

//


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reuse_discoverfragment_relative:
                mTitclePopupWindow = new PopupWindow(mView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                mTitclePopupWindow.showAsDropDown(mLocationTv);
                mTitclePopupWindow.setFocusable(true);
                mTitclePopupWindow.setTouchable(true);

                initOnTouchLisenner();
//                mPopupWindowGrideView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        mPopupWindowGrideView.getChildAt(0).setBackgroundResource(R.color.nameText);
//
//                    }
//                });
//
                break;
            case R.id.popupwindow_up_img:
                if (mTitclePopupWindow != null || mTitclePopupWindow.isShowing()){
                    mTitclePopupWindow.dismiss();
                }
                break;
        }

    }


    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void onResponse(PopupwindowBean popupwindowBean) {

     ArrayList<PopupwindowBean.DataBean.CategoriesBean.SubCategoriesBean> mTitleList = (ArrayList<PopupwindowBean.DataBean.CategoriesBean.SubCategoriesBean>) popupwindowBean.getData().getCategories().get(mPosition -3).getSub_categories();
        PopupwindowBean.DataBean.CategoriesBean.SubCategoriesBean bean = new PopupwindowBean.DataBean.CategoriesBean.SubCategoriesBean();
        bean.setName("全部");
        mTitleList.add(0,bean);


        mPopupWindowGrideView.setAdapter(popupAdapter = new CurrentAdapter<PopupwindowBean.DataBean.CategoriesBean.SubCategoriesBean>(context
                ,mTitleList,R.layout.item_popopwindow) {

            @Override
            public void convert(BaseViewHolder helper, PopupwindowBean.DataBean.CategoriesBean.SubCategoriesBean item) {
                helper.setText(item_popupwindow_tv,item.getName());



            }
        });


    }

    @Override
    public void onError() {

    }
}
