package com.huawei.deepitm.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * AUTHOR Paul
 * DATE 2018/3/24
 */
public class HomeAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> fragments;
//    private ArrayList<String> tabTitles;
    private Context mContext;

    public HomeAdapter(FragmentManager fm, ArrayList<Fragment> fragments, Context mContext) {
        super(fm);
        this.fragments = fragments;
//        this.tabTitles = tabTitles;
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
