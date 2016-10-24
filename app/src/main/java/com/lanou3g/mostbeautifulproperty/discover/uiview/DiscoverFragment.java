package com.lanou3g.mostbeautifulproperty.discover.uiview;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseFragment;
import com.lanou3g.mostbeautifulproperty.view.ScaleTransitionPagerTitleView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.BezierPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import com.lanou3g.mostbeautifulproperty.discover.uiview.adapter.DiscoverAdapter;

import java.util.ArrayList;

/**
 *
 */

public class DiscoverFragment extends BaseFragment{

    private MagicIndicator mTabDiscover;
    private ViewPager mVpDiscover;
    private ArrayList<String> mTitles;

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

        mTitles = initTitles();

        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < mTitles.size(); i++) {
            fragments.add(new DiscoverTabReuseFragment());
        }

        DiscoverAdapter adapter = new DiscoverAdapter(getChildFragmentManager());
        adapter.setTitles(mTitles);
        adapter.setFragments(fragments);
        mVpDiscover.setAdapter(adapter);
        initMagic();
//        mTabDiscover.setupWithViewPager(mVpDiscover);
//        // 更改tab 下滑线
//        mTabDiscover.setSelectedTabIndicatorColor(Color.WHITE);
//        // 给tab文字 加选中颜色
//        mTabDiscover.setTabTextColors(Color.GRAY, Color.WHITE);
    }

    private void initMagic() {
        mTabDiscover.setBackgroundColor(Color.BLACK);
        CommonNavigator commonNavigator = new CommonNavigator(context);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mTitles == null ? 0 : mTitles.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                //标题文字,选中与非选中颜色
                simplePagerTitleView.setText(mTitles.get(index));
//                simplePagerTitleView.setTextSize(18);
                simplePagerTitleView.setNormalColor(Color.GRAY);
                simplePagerTitleView.setSelectedColor(Color.WHITE);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mVpDiscover.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                BezierPagerIndicator indicator = new BezierPagerIndicator(context);
                indicator.setColors(Color.parseColor("#ff4a42"), Color.parseColor("#fcde64"), Color.parseColor("#73e8f4"), Color.parseColor("#76b0ff"), Color.parseColor("#c683fe"));
                return indicator;
            }
        });
        mTabDiscover.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mTabDiscover, mVpDiscover);
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
