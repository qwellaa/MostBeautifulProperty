package com.lanou3g.mostbeautifulproperty.discover.uiview;

import android.widget.ListView;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseFragment;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseViewHolder;
import com.lanou3g.mostbeautifulproperty.baseclass.CurrentAdapter;
import com.lanou3g.mostbeautifulproperty.bean.DailyBean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class DiscoverDailyFragment extends BaseFragment{
    private ListView mListView;
    private CurrentAdapter mAdapter;
    private DailyBean mDailyBean = new DailyBean();
    @Override
    protected int setLayout() {
        return R.layout.fragment_designer_daily;
    }

    @Override
    protected void initView() {
        mListView = bindView(R.id.list_daily);
    }

    @Override
    protected void initData() {
        List<DailyBean>dailyBeen = new ArrayList<>();
        mListView.setAdapter(mAdapter = new CurrentAdapter<DailyBean>(context,dailyBeen,
                R.layout.item_discover_daily) {
            @Override
            public void convert(BaseViewHolder helper, DailyBean item) {

            }
        });
    }
}
