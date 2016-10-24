package com.lanou3g.mostbeautifulproperty.designer;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.lanou3g.mostbeautifulproperty.R;
import com.lanou3g.mostbeautifulproperty.baseclass.BaseFragment;
import com.lanou3g.mostbeautifulproperty.discover.tabreuse.DiscoverTabReuseFragment;

import net.lucode.hackware.magicindicator.MagicIndicator;

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
