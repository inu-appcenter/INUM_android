package org.gowoon.inum.custom;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.baoyachi.stepview.bean.StepBean;

import java.util.ArrayList;
import java.util.List;

public class AdapterViewPagerSignUp extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private List<Integer> mFragmentStep = new ArrayList<>();

    public AdapterViewPagerSignUp(FragmentManager fm) {
        super(fm);
    }

    public StepBean addFragment(Fragment fragment, int step){
        mFragmentList.add(fragment);
        mFragmentStep.add(step);
        return null;
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
