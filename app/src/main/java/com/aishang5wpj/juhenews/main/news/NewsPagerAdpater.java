package com.aishang5wpj.juhenews.main.news;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wpj on 16/5/16下午5:04.
 */
public class NewsPagerAdpater extends FragmentPagerAdapter {

    private List<Fragment> mFragments = new ArrayList<>();
    private List<String> mFragmentTitles = new ArrayList<>();

    public NewsPagerAdpater(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }

    public void clear() {
        mFragments.clear();
        mFragmentTitles.clear();
    }

    public void setData(List<Fragment> fragmentList, List<String> nameList) {
        if (null == fragmentList) {
            return;
        }
        mFragments = fragmentList;
        mFragmentTitles = nameList;
        notifyDataSetChanged();
    }
}
