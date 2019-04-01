package com.example.hw_0603_android_ui;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

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
