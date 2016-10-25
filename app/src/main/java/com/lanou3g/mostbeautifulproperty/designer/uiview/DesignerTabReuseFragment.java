package com.lanou3g.mostbeautifulproperty.designer.uiview;

import android.os.Bundle;
import android.widget.ListView;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseFragment;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseViewHolder;
import com.lanou3g.mostbeautifulproperty.baseclass.CurrentAdapter;
import com.lanou3g.mostbeautifulproperty.bean.DesignerBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/22.
 */

public class DesignerTabReuseFragment extends BaseFragment {
//    TextView mTextView;
    private ListView mListView;
    private CurrentAdapter mAdapter;
    private DesignerBean mDesignerBean = new DesignerBean();

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
//        mTextView = bindView(R.id.tv_designer_reuse);
        mListView = bindView(R.id.list_designer);
    }

    @Override
    protected void initData() {

        List<DesignerBean>mBeanList = new ArrayList<>();
//        Bundle args = getArguments();
//        int position = args.getInt("i");
//        mTextView.setText(position + "");
        mDesignerBean.setTv1("一");
        mDesignerBean.setTv2("二");
        mBeanList.add(mDesignerBean);
        mListView.setAdapter(mAdapter = new CurrentAdapter<DesignerBean>(context,mBeanList,
                R.layout.designer_item_list) {
            @Override
            public void convert(BaseViewHolder helper, DesignerBean item) {
                Bundle args =  getArguments();
                int postiton = args.getInt("i");
                helper.setText(R.id.tv1,item.getTv1() + postiton);
                helper.setText(R.id.tv2,item.getTv2() + postiton);
            }

        });
    }
}
