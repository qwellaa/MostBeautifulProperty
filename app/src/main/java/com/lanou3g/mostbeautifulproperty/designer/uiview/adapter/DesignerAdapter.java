package com.lanou3g.mostbeautifulproperty.designer.uiview.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lanou3g.mostbeautifulproperty.designer.uiview.DesignerTabReuseFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/22.
 */

public class DesignerAdapter extends FragmentPagerAdapter{
    private ArrayList<Fragment> mFragments;
    private ArrayList<String> mTitles;


    public void setFragments(ArrayList<Fragment> fragments) {
        mFragments = fragments;
        notifyDataSetChanged();
    }

    public void setTitles(ArrayList<String> titles) {
        mTitles = titles;
    }

    public DesignerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return DesignerTabReuseFragment.newInstance(position);
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
