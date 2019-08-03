package org.gowoon.inum.custom;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdapterViewPagerSignUp extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private List<Integer> mFragmentStep = new ArrayList<>();

    public AdapterViewPagerSignUp(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, int step){
        mFragmentList.add(fragment);
        mFragmentStep.add(step);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
