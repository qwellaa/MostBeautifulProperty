package com.lanou3g.mostbeautifulproperty.discover.uiview.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lanou3g.mostbeautifulproperty.discover.uiview.DiscoverTabReuseFragment;

import java.util.ArrayList;

/**
 *
 */

public class DiscoverAdapter extends FragmentPagerAdapter{

    private ArrayList<Fragment> mFragments;
    private ArrayList<String> mTitles;


    public void setFragments(ArrayList<Fragment> fragments) {
        mFragments = fragments;
        notifyDataSetChanged();
    }

    public void setTitles(ArrayList<String> titles) {
        mTitles = titles;
    }

    public DiscoverAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return DiscoverTabReuseFragment.newInstance(position);
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
