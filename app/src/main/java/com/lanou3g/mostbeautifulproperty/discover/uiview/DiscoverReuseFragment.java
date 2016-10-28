package com.lanou3g.mostbeautifulproperty.discover.uiview;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseFragment;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseViewHolder;
import com.lanou3g.mostbeautifulproperty.baseclass.CurrentAdapter;
import com.lanou3g.mostbeautifulproperty.bean.DiscoverBean;
import com.lanou3g.mostbeautifulproperty.bean.PopupwindowBean;
import com.lanou3g.mostbeautifulproperty.discover.presenter.DiscoverPresenter;
import com.lanou3g.mostbeautifulproperty.homepage.MainActivity;
import com.lanou3g.mostbeautifulproperty.okhttp.URLValues;
import com.lanou3g.mostbeautifulproperty.view.LVGhost;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class DiscoverReuseFragment extends BaseFragment implements View.OnClickListener, IDiscoverView{


    private static final int TABMEN = 6;
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

    private ListView mListView;
    private CurrentAdapter mAdapter;
    private AlertDialog mDialog;
    private PopupwindowBean.DataBean.CategoriesBean.SubCategoriesBean mBean;
    private PopupwindowBean.DataBean.CategoriesBean.SubCategoriesBean mBeanAll;
    private List<PopupwindowBean.DataBean.CategoriesBean.SubCategoriesBean> mTitleList;

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
        if (!isNetworkConnected(context)) {
            Toast.makeText(context, "网络不可用", Toast.LENGTH_SHORT).show();
        }
        tb = bindView(R.id.tab_discover);
        mLocationTv = bindView(R.id.dingwei);
        mMoreTopView = bindView(R.id.reuse_discoverfragment_relative);
        mTitcleTv = bindView(R.id.fragment_discover_all_tv);
        mMoreTopView.setOnClickListener(this);
        mView = LayoutInflater.from(getContext()).inflate(R.layout.top_popupwindow, null);
        mUpImg = bindView(R.id.popupwindow_up_img,mView);
        mUpImg.setOnClickListener(this);
        mPopupWindowGrideView = bindView(R.id.popuuwindow_gridview, mView);
        mItemTv = bindView(R.id.item_popupwindow_tv);


        mListView = bindView(R.id.lv_discover_refuse);
        mDialog = createDialog();
    }


    private AlertDialog createDialog() {
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCanceledOnTouchOutside(true);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_loading, null);
        LVGhost mLvGhost = (LVGhost) view.findViewById(R.id.dialog_lvghost);
        mLvGhost.startAnim();
        dialog.setView(view);
        return dialog;


    }
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;

    }


    @Override
    protected void initData() {
        Bundle args = getArguments();
        mPosition = args.getInt("position");
        mPresenter = new DiscoverPresenter(this);
        if (TABMEN == mPosition) {
            mMoreTopView.setVisibility(View.INVISIBLE);
        } else {
            mPresenter.startRequest(URLValues.POPUPWINDOW_URL,PopupwindowBean.class);
        }
        mPresenter.startRequest(URLValues.DISCOVER_JEWELRY_ALL_URL,DiscoverBean.class);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reuse_discoverfragment_relative:
                mTitclePopupWindow = new PopupWindow(mView, RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                mTitclePopupWindow.showAsDropDown(mLocationTv);
                mTitclePopupWindow.setFocusable(true);
                mTitclePopupWindow.setTouchable(true);
                initOnTouchLisenner();


                // 点击事件
                initOnClickGridList();
                break;
            case R.id.popupwindow_up_img:
                if (mTitclePopupWindow != null || mTitclePopupWindow.isShowing()){
                    mTitclePopupWindow.dismiss();
                }

                break;
        }

    }


        @Override
        public void showDialog () {
            mDialog.show();
        }

    private void initOnClickGridList() {
        mPopupWindowGrideView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String title = mTitleList.get(position).getName();
                mTitcleTv.setText(title);
                mTitclePopupWindow.dismiss();
            }
        });

    }



        @Override
        public void dismissDialog () {
            mDialog.dismiss();
        }

    @Override

    public void onResponse(Object result) {
        if (result instanceof PopupwindowBean){

            mTitleList = null;
            PopupwindowBean popupwindowBean = (PopupwindowBean) result;
            List<PopupwindowBean.DataBean.CategoriesBean.SubCategoriesBean> mTitleList = null;

            try {
                mTitleList = popupwindowBean.getData().getCategories().get(mPosition - 3).getSub_categories();

            } catch (NullPointerException e) {
                e.printStackTrace();
                mTitleList = new ArrayList<>();
            }
            mBean = new PopupwindowBean.DataBean.CategoriesBean.SubCategoriesBean();
            mBeanAll = new PopupwindowBean.DataBean.CategoriesBean.SubCategoriesBean();
            mBeanAll.setName("全部");
            mTitleList.add(0,mBeanAll);
            mPopupWindowGrideView.setAdapter(popupAdapter = new CurrentAdapter<PopupwindowBean.DataBean.CategoriesBean.SubCategoriesBean>(context
                    , mTitleList, R.layout.item_popopwindow) {

                @Override
                public void convert(BaseViewHolder helper, PopupwindowBean.DataBean.CategoriesBean.SubCategoriesBean item) {
                    helper.setText(R.id.item_popupwindow_tv, item.getName());
                    mBean.setName(item.getName());
                }

            });
            mTitleList.add(mBean);
            mTitleList.remove(mTitleList.size()-1);
            mDialog.dismiss();
        }
        if (result instanceof DiscoverBean){

            DiscoverBean discoverBean = (DiscoverBean) result;
            final ArrayList<DiscoverBean.DataBean.ProductsBean> beanArrayList = (ArrayList<DiscoverBean.DataBean.ProductsBean>) discoverBean.getData().getProducts();
            mListView.setAdapter(mAdapter = new CurrentAdapter<DiscoverBean.DataBean.ProductsBean>(context, beanArrayList, R.layout.discover_item_list) {
                @Override
                public void convert(BaseViewHolder helper, DiscoverBean.DataBean.ProductsBean item) {
                    helper.setText(R.id.tv_discover_list_name, item.getDesigner().getName());
                    helper.setText(R.id.tv_discover_list_identity, item.getDesigner().getLabel());
                    helper.setText(R.id.tv_discover_list_title, item.getName());
                    helper.setIamgeGlide(R.id.iv_discover_list_photo, item.getDesigner().getAvatar_url());
                    helper.setIamgeGlide(R.id.iv_discover_list_title, item.getCover_images().get(0));
                }
            });
        }
    }
        @Override
        public void onError () {

            Toast.makeText(context, "数据异常,请求失败", Toast.LENGTH_SHORT).show();
        }

}

