package com.lanou3g.mostbeautifulproperty.discover.uiview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseFragment;

/**
 *
 */

public class DiscoverReuseFragment extends BaseFragment implements View.OnClickListener {
    private static final int TABMEN = 6;

    private TextView mTv;
    private RelativeLayout mMoreTopView;
    private TextView mTitcleTv;
    private ImageView mTitcleImg;

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
      //  mTv = bindView(R.id.tv_discover_reuse);
        mMoreTopView = bindView(R.id.reuse_discoverfragment_relative);
        mTitcleTv = bindView(R.id.fragment_discover_all_tv);
        mTitcleImg = bindView(R.id.fragment_discover_dowm_img);
        mTitcleImg.setOnClickListener(this);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.top_popupwindow,null);

    }

    @Override
    protected void initData() {
        Bundle args = getArguments();
        int position = args.getInt("position");
        if (TABMEN == position) {
         mMoreTopView.setVisibility(View.INVISIBLE);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_discover_dowm_img:

                PopupWindow titclePopupWindow = new PopupWindow(getContext(), RelativeLayout.LayoutParams.MATCH_PARENT,200);

                break;
        }

    }
}
