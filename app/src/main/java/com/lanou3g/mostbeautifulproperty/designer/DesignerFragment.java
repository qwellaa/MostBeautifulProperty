package com.lanou3g.mostbeautifulproperty.designer;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseFragment;
import com.lanou3g.mostbeautifulproperty.discover.tabreuse.DiscoverTabReuseFragment;
import com.lanou3g.mostbeautifulproperty.tabtitles.ScaleTransitionPagerTitleView;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.BezierPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;

/**
 *
 */

public class DesignerFragment extends BaseFragment{

    private MagicIndicator mTabLayout;
    private ViewPager mViewPager;
    private ArrayList<String> mTitles;

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
        initMagic();
//        mTabLayout.setupWithViewPager(mViewPager);
//        // 更改tab 下滑线
//        mTabLayout.setSelectedTabIndicatorColor(Color.WHITE);
//        // 给tab文字 加选中颜色
//        mTabLayout.setTabTextColors(Color.GRAY, Color.WHITE);
    }

    private void initMagic() {
        mTabLayout.setBackgroundColor(Color.BLACK);
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
                        mViewPager.setCurrentItem(index);
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
        mTabLayout.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mTabLayout,mViewPager);
    }

    private ArrayList<String> initTitles() {
        mTitles = new ArrayList<>();
        mTitles.add("我关注的");
        mTitles.add("推荐");
        mTitles.add("最受欢迎");
        mTitles.add("独立设计师");
        mTitles.add("大牌设计师");
        return mTitles;
    }
}
