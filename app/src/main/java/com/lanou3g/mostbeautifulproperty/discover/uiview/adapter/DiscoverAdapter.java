package com.lanou3g.mostbeautifulproperty.discover.uiview.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lanou3g.mostbeautifulproperty.discover.uiview.DiscoverReuseFragment;

import java.util.List;

/**
 *
 */

public class DiscoverAdapter extends FragmentPagerAdapter{

    private List<Fragment> mFragments;
    private List<String> mTitles;


    public void setFragments(List<Fragment> fragments) {
        mFragments = fragments;
        notifyDataSetChanged();
    }

    public void setTitles(List<String> titles) {
        mTitles = titles;
    }

    public DiscoverAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position > 2) {
            return DiscoverReuseFragment.newInstance(position);
        } else {
            return mFragments.get(position);
        }
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
