package com.lanou3g.mostbeautifulproperty.designer.uiview;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseFragment;
import com.lanou3g.mostbeautifulproperty.designer.uiview.adapter.DesignerAdapter;
import com.lanou3g.mostbeautifulproperty.discover.uiview.DiscoverTabReuseFragment;

import java.util.ArrayList;

/**
 *
 */

public class DesignerFragment extends BaseFragment{

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected int setLayout() {
        return R.layout.fragment_designer;
    }

    @Override
    protected void initView() {
        mViewPager = bindView(R.id.vp_designer);
        mTabLayout = bindView(R.id.tab_designer);
    }

    @Override
    protected void initData() {
        ArrayList<String> titles = initTitles();

        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            fragments.add(new DiscoverTabReuseFragment());
        }

        DesignerAdapter adapter = new DesignerAdapter(getChildFragmentManager());
        adapter.setTitles(titles);
        adapter.setFragments(fragments);

        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        // 更改tab 下滑线
        mTabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        // 给tab文字 加选中颜色
        mTabLayout.setTabTextColors(Color.GRAY, Color.WHITE);
    }

    private ArrayList<String> initTitles() {
        ArrayList<String>titles = new ArrayList<>();
        titles.add("我关注的");
        titles.add("推荐");
        titles.add("最受欢迎");
        titles.add("独立设计师");
        titles.add("大牌设计师");
        return titles;
    }
}
