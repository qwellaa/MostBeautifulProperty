package com.lanou3g.mostbeautifulproperty.discover.uiview;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
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
import com.lanou3g.mostbeautifulproperty.discover.presenter.DiscoverReusePresenter;
import com.lanou3g.mostbeautifulproperty.homepage.MainActivity;
import com.lanou3g.mostbeautifulproperty.okhttp.URLValues;
import com.lanou3g.mostbeautifulproperty.view.LVGhost;

import java.util.ArrayList;

/**
 *
 */

public class DiscoverReuseFragment extends BaseFragment implements View.OnClickListener,IDiscoverView<DiscoverBean> {
    private static final int TABMEN = 6;
    private RelativeLayout mMoreTopView;
    private TextView mTitcleTv;
    private ImageView mTitcleImg;
    private View mView;
    private PopupWindow mTitclePopupWindow;
    private TableLayout tb;
    private TextView mLocationTv;
    private ListView mListView;
    private CurrentAdapter mAdapter;
    private DiscoverReusePresenter mDiscoverReusePresenter;
    private AlertDialog mDialog;

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
        mLocationTv = bindView(R.id.dingwei);
        mMoreTopView = bindView(R.id.reuse_discoverfragment_relative);
        mTitcleTv = bindView(R.id.fragment_discover_all_tv);
        mTitcleImg = bindView(R.id.fragment_discover_dowm_img);
        mTitcleImg.setOnClickListener(this);
        mView = LayoutInflater.from(getContext()).inflate(R.layout.top_popupwindow, null);
        mListView = bindView(R.id.lv_discover_refuse);
        mDialog = createDialog();
    }

    private AlertDialog createDialog(){
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.setCanceledOnTouchOutside(true);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_loading, null);
        LVGhost mLvGhost =  (LVGhost) view.findViewById(R.id.dialog_lvghost);
        mLvGhost.startAnim();
        dialog.setView(view);
        return dialog;
    }

    @Override
    protected void initData() {
        Bundle args = getArguments();
        int position = args.getInt("position");
        if (TABMEN == position) {
            mMoreTopView.setVisibility(View.INVISIBLE);
        }
//        List<DiscoverBean> discoverBeanList = new ArrayList<>();
//        mListView.setAdapter(mAdapter = new CurrentAdapter<DiscoverBean>(context,discoverBeanList,
//                R.layout.discover_item_list) {
//            @Override
//            public void convert(BaseViewHolder helper, DiscoverBean item) {
//
//            }
//        });
        mDiscoverReusePresenter = new DiscoverReusePresenter(this);
        mDiscoverReusePresenter.startRequest(URLValues.DISCOVER_JEWELRY_ALL_URL);


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

              if (mTitclePopupWindow.isShowing()){
                  mTitclePopupWindow.dismiss();
              }

            }
      };

        // 将myTouchListener注册到分发列表
        ((MainActivity)this.getActivity()).registerMyTouchListener(myTouchListener);

    }

//


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_discover_dowm_img:



                    mTitclePopupWindow = new PopupWindow(mView, RelativeLayout.LayoutParams.MATCH_PARENT, 200);
                    mTitclePopupWindow.showAsDropDown(mLocationTv);
                    mTitclePopupWindow.setFocusable(true);
                    mTitclePopupWindow.setTouchable(true);
                    initOnTouchLisenner();
//
                break;
        }

    }

    @Override
    public void showDialog() {
        mDialog.show();
    }

    @Override
    public void dismissDialog() {
        mDialog.dismiss();
    }

    @Override
    public void onResponse(DiscoverBean discoverBean) {
        ArrayList<DiscoverBean.DataBean.ProductsBean> beanArrayList = (ArrayList<DiscoverBean.DataBean.ProductsBean>) discoverBean.getData().getProducts();
        mListView.setAdapter(mAdapter = new CurrentAdapter<DiscoverBean.DataBean.ProductsBean>(context,beanArrayList, R.layout.discover_item_list) {
            @Override
            public void convert(BaseViewHolder helper, DiscoverBean.DataBean.ProductsBean item) {
                helper.setText(R.id.tv_discover_list_name,item.getDesigner().getName());
                helper.setText(R.id.tv_discover_list_identity,item.getDesigner().getLabel());
                helper.setText(R.id.tv_discover_list_title,item.getName());
                helper.setIamgeGlide(R.id.iv_discover_list_photo,item.getDesigner().getAvatar_url());
                helper.setIamgeGlide(R.id.iv_discover_list_title,item.getCover_images().get(0));
            }
        });
    }

    @Override
    public void onError() {
        Toast.makeText(context, "数据异常,请求失败", Toast.LENGTH_SHORT).show();
    }
}
