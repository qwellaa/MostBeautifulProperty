package com.lanou3g.mostbeautifulproperty.discover;

import android.os.Bundle;
import android.widget.TextView;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseFragment;

/**
 *
 */

public class DiscoverReuseFragment extends BaseFragment{

    private TextView mTv;

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
        mTv = bindView(R.id.tv_discover_reuse);
    }

    @Override
    protected void initData() {
        Bundle args = getArguments();
        int position = args.getInt("position");
        mTv.setText(position + "");
    }
}
