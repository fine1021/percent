package com.yxkang.android.percent.sample.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yxkang.android.percent.sample.fragment.TabFragment;

import java.util.ArrayList;

/**
 * Created by yexiaokang.
 */
public class SampleFragmentAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"Tab1", "Tab2", "Tab3"};
    private ArrayList<Fragment> fragments = new ArrayList<>();

    public SampleFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public SampleFragmentAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments.size() == mTitles.length) {
            return fragments.get(position);
        }
        return TabFragment.newInstance("", "");
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
