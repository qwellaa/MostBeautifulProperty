package com.lanou3g.mostbeautifulproperty.discover;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;


import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseFragment;
import com.lanou3g.mostbeautifulproperty.discover.tabreuse.DiscoverTabReuseFragment;

import java.util.ArrayList;

/**
 *
 */

public class DiscoverFragment extends BaseFragment{

    private TabLayout mTabDiscover;
    private ViewPager mVpDiscover;

    @Override
    protected int setLayout() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void initView() {
        mTabDiscover = bindView(R.id.tab_discover);
        mVpDiscover = bindView(R.id.vp_discover);
    }

    @Override
    protected void initData() {

        ArrayList<String> titles = initTitles();

        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            fragments.add(new DiscoverTabReuseFragment());
        }

        DiscoverAdapter adapter = new DiscoverAdapter(getChildFragmentManager());
        adapter.setTitles(titles);
        adapter.setFragments(fragments);

        mVpDiscover.setAdapter(adapter);
        mTabDiscover.setupWithViewPager(mVpDiscover);
        // 更改tab 下滑线
        mTabDiscover.setSelectedTabIndicatorColor(Color.WHITE);
        // 给tab文字 加选中颜色
        mTabDiscover.setTabTextColors(Color.GRAY, Color.WHITE);
    }

    private ArrayList<String> initTitles() {

        ArrayList<String> titles = new ArrayList<>();

        titles.add("喜欢的");
        titles.add("设计师动态");
        titles.add("Daily");
        titles.add("首饰");
        titles.add("包袋");
        titles.add("鞋履");
        titles.add("Men");
        titles.add("配饰");
        titles.add("其他");
        return titles;
    }


}
