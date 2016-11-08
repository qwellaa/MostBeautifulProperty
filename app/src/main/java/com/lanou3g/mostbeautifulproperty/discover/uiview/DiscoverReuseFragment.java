package com.lanou3g.mostbeautifulproperty.discover.uiview;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshView.SimpleXRefreshListener;
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

public class DiscoverReuseFragment extends BaseFragment implements View.OnClickListener, IDiscoverView {


    private static final int TABMEN = 6;
    private RelativeLayout mMoreTopView;
    private TextView mTitcleTv;
    private View mView;
    private PopupWindow mTitclePopupWindow;
    private TableLayout tb;
    private TextView mLocationTv;
    private GridView mPopupWindowGrideView;
    private int mPosition;
    private DiscoverPresenter mPresenter;
    private MyAdapter popupAdapter;
    private TextView mItemTv;
    private ImageView mUpImg;
    private int page = 1;
    private int size = 20;
    private ListView mListView;
    private AlertDialog mDialog;
    private PopupwindowBean.DataBean.CategoriesBean.SubCategoriesBean mBean;
    private PopupwindowBean.DataBean.CategoriesBean.SubCategoriesBean mBeanAll;
    private List<PopupwindowBean.DataBean.CategoriesBean.SubCategoriesBean> mTitleList;
    private XRefreshView mRefreshView;
    private String mEndUrl;
    private int mPopPosition;
    private DiscoverResuseAdapter mAdapter;

    private void initLoadData() {
        List<String> URLJewelryList = new ArrayList<>();
        URLJewelryList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(3));
        URLJewelryList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(24));
        URLJewelryList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(23));
        URLJewelryList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(22));
        URLJewelryList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(21));
        URLJewelryList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(20));

        List<String> URLBagList = new ArrayList<>();
        URLBagList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(1));
        URLBagList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(51));
        URLBagList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(32));
        URLBagList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(10));
        URLBagList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(9));
        URLBagList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(8));
        URLBagList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(7));
        URLBagList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(6));
        URLBagList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(5));

        List<String> URLShoeList = new ArrayList<>();
        URLShoeList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(2));
        URLShoeList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(14));
        URLShoeList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(49));
        URLShoeList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(48));
        URLShoeList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(38));
        URLShoeList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(16));
        URLShoeList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(15));
        URLShoeList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(11));

        List<String>URLManList = new ArrayList<>();
        URLManList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(65));

        //配饰
        List<String>URLAccessoriesList = new ArrayList<>();
        URLAccessoriesList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(4));
        URLAccessoriesList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(53));
        URLAccessoriesList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(52));
        URLAccessoriesList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(45));
        URLAccessoriesList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(37));
        URLAccessoriesList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(36));
        URLAccessoriesList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(29));
        URLAccessoriesList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(27));
        URLAccessoriesList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(26));
        URLAccessoriesList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(25));

        List<String>URLOtherList = new ArrayList<>();
        URLOtherList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(54));
        URLOtherList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(68));
        URLOtherList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(64));
        URLOtherList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(61));
        URLOtherList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(58));
        URLOtherList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(56));
        URLOtherList.add(URLValues.getDISCOVER_HEAD_AND_MID_URL(42));

        mEndUrl = URLValues.getDISCOVER_TAIL_URL();

        URLHeadAndMid.add(URLJewelryList);
        URLHeadAndMid.add(URLBagList);
        URLHeadAndMid.add(URLShoeList);
        URLHeadAndMid.add(URLManList);
        URLHeadAndMid.add(URLAccessoriesList);
        URLHeadAndMid.add(URLOtherList);

    }


    private ArrayList<List> URLHeadAndMid = new ArrayList<>();
    private List<DiscoverBean.DataBean.ProductsBean> mBeanArrayList;
    private String mUrl;


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
        mUpImg = bindView(R.id.popupwindow_up_img, mView);
        mUpImg.setOnClickListener(this);
        mPopupWindowGrideView = bindView(R.id.popuuwindow_gridview, mView);
        mItemTv = bindView(R.id.item_popupwindow_tv);
        mRefreshView = bindView(R.id.discover_refresh);

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
        mBeanArrayList = new ArrayList<>();
        mAdapter = new DiscoverResuseAdapter(context);
        mListView.setAdapter(mAdapter);
                initLoadData();

        Bundle args = getArguments();
        mPosition = args.getInt("position");
        mPresenter = new DiscoverPresenter(this);
        if (TABMEN == mPosition) {
            mMoreTopView.setVisibility(View.INVISIBLE);
        } else {
            mPresenter.startRequest(URLValues.POPUPWINDOW_URL, PopupwindowBean.class);
        }

        List URLStrs = URLHeadAndMid.get(mPosition - 3);
        mUrl = ((String) URLStrs.get(0)) + page + mEndUrl + size;
        mPresenter.startRequest(mUrl, DiscoverBean.class);
        //设置是否可以下拉刷新
        mRefreshView.setPullRefreshEnable(true);
        //设置是否可以上拉加载
        mRefreshView.setPullLoadEnable(true);
        refresh();

    }

    private void refresh() {
        mRefreshView.setXRefreshViewListener(new SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
                        mPresenter.startRequest(mUrl, DiscoverBean.class);
                        Log.d("DiscoverReuseFragment", mUrl);

            }

            @Override
            public void onLoadMore(boolean isSilence) {
                        page = page + 1;
                        Log.d("DiscoverReuseFragment", "page:" + page);
                        initLoadData();
                        List URLStrs = URLHeadAndMid.get(mPosition - 3);
                        mUrl = (String) URLStrs.get(mPopPosition) + page + mEndUrl + size;
                        Log.d("DiscoverReuseFragment", mUrl);
                        mPresenter.startRequest(mUrl, DiscoverBean.class);

                    }
        });
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
                if (mTitclePopupWindow != null || mTitclePopupWindow.isShowing()) {
                    mTitclePopupWindow.dismiss();
                }

                break;
        }

    }


    @Override
    public void showDialog() {
        mDialog.show();
    }

    private void initOnClickGridList() {
        mPopupWindowGrideView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPopPosition = position;
                TextView tv = (TextView) view.findViewById(R.id.item_popupwindow_tv);
                for (int i = 0; i < mTitleList.size(); i++) {
                    View v = parent.getChildAt(i);
                    TextView tv1 = (TextView) v.findViewById(R.id.item_popupwindow_tv);
                    if (position == i) {
                        view.setBackgroundColor(Color.WHITE);
                        tv.setTextColor(Color.BLACK);
                    } else {
                        v.setBackgroundColor(Color.parseColor("#2c2c2c"));
                        tv1.setTextColor(Color.WHITE);
                    }

                }
                popupAdapter.setSelectPos(position);

                String title = mTitleList.get(position).getName();
                mTitcleTv.setText(title);
                List URLStrings = URLHeadAndMid.get(mPosition - 3);
                String urls = ((String) URLStrings.get(position)) + page + mEndUrl + size;
                mPresenter.startRequest(urls, DiscoverBean.class);
                mTitclePopupWindow.dismiss();
                Toast.makeText(context, "position:" + position, Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public void dismissDialog() {
        mDialog.dismiss();
    }

    @Override

    public void onResponse(Object result) {
        if (result instanceof PopupwindowBean) {

            mTitleList = null;
            PopupwindowBean popupwindowBean = (PopupwindowBean) result;

            try {
                mTitleList = popupwindowBean.getData().getCategories().get(mPosition - 3).getSub_categories();

            } catch (NullPointerException e) {
                e.printStackTrace();
                mTitleList = new ArrayList<>();
            }
            mBean = new PopupwindowBean.DataBean.CategoriesBean.SubCategoriesBean();
            mBeanAll = new PopupwindowBean.DataBean.CategoriesBean.SubCategoriesBean();
            mBeanAll.setName("全部");
            mTitleList.add(0, mBeanAll);
            mPopupWindowGrideView.setAdapter(popupAdapter = new MyAdapter(context
                    , mTitleList, R.layout.item_popopwindow));
            mTitleList.add(mBean);
            mTitleList.remove(mTitleList.size() - 1);
            mDialog.dismiss();
        }
        if (result instanceof DiscoverBean) {
            DiscoverBean discoverBean = (DiscoverBean) result;
            List<DiscoverBean.DataBean.ProductsBean>list = discoverBean.getData().getProducts();
            if (page == 1){
                mAdapter.setDataListRefresh(list);
                mRefreshView.stopRefresh();
            }
            mAdapter.setDataListLoad(list);
            mRefreshView.stopLoadMore();
        }
    }

    @Override
    public void onError() {

        Toast.makeText(context, "数据异常,请求失败", Toast.LENGTH_SHORT).show();
    }


    class MyAdapter extends CurrentAdapter<PopupwindowBean.DataBean.CategoriesBean.SubCategoriesBean> {
        private int selectPos = 0;

        public MyAdapter(Context context, List<PopupwindowBean.DataBean.CategoriesBean.SubCategoriesBean> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(BaseViewHolder helper, PopupwindowBean.DataBean.CategoriesBean.SubCategoriesBean item) {
            helper.setText(R.id.item_popupwindow_tv, item.getName());
            mBean.setName(item.getName());
            TextView textView = helper.getView(R.id.item_popupwindow_tv);
            RelativeLayout rl = helper.getView(R.id.item_popupwindow_rl);
            if (helper.getPosition() == selectPos) {
                rl.setBackgroundColor(Color.WHITE);
                textView.setTextColor(Color.BLACK);
            } else {
                rl.setBackgroundColor(Color.parseColor("#2c2c2c"));
                textView.setTextColor(Color.WHITE);
            }

        }

        public void setSelectPos(int selectPos) {
            this.selectPos = selectPos;
            notifyDataSetChanged();
        }

    }

}

