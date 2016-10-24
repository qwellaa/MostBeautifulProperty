package com.lanou3g.mostbeautifulproperty.discover.uiview;

import android.os.Bundle;
import android.widget.TextView;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseFragment;

/**
 *
 */

public class DiscoverTabReuseFragment extends BaseFragment{

    private TextView mTv;

    public static DiscoverTabReuseFragment newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt("position", position);
        DiscoverTabReuseFragment fragment = new DiscoverTabReuseFragment();
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
