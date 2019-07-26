package org.gowoon.inum.custom;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdapterViewPagerSignUp extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();

    public AdapterViewPagerSignUp(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, int step){
        mFragmentList.add(fragment);
//        mFragmentTitleList.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
