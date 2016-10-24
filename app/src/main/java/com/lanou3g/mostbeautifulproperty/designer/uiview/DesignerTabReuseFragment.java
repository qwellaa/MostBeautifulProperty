package com.lanou3g.mostbeautifulproperty.designer.uiview;

import android.os.Bundle;
import android.widget.TextView;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseFragment;

/**
 * Created by dllo on 16/10/22.
 */

public class DesignerTabReuseFragment extends BaseFragment {
    TextView mTextView;

    public static DesignerTabReuseFragment newInstance(int position) {

        Bundle args = new Bundle();
        args.putInt("i",position);
        DesignerTabReuseFragment fragment = new DesignerTabReuseFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_designer_reuse;
    }

    @Override
    protected void initView() {
        mTextView = bindView(R.id.tv_designer_reuse);
    }

    @Override
    protected void initData() {
        Bundle args = getArguments();
        int position = args.getInt("i");
        mTextView.setText(position + "");
    }
}
