package com.example.hw_0603_android_ui;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class GPPageAdapter extends FragmentStatePagerAdapter {

    private String tabs[] = new String[]{"New episodes", "In progress", "Downloads"};

    public GPPageAdapter(final FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return tabs.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(final int position) {
        return tabs[position];
    }

    @Override
    public Fragment getItem(final int i) {
        return new TabFragment();
    }
}
