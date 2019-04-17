package com.shvants.runninglife.fragments;

import android.view.MenuItem;

import com.shvants.runninglife.R;
import com.shvants.runninglife.ui.clubs.ClubsFragment;
import com.shvants.runninglife.ui.feed.FeedFragment;
import com.shvants.runninglife.ui.record.MoveRecordFragment;

import androidx.fragment.app.FragmentTransaction;

public class NavigationFragmentSwitcher {

    private final MenuItem menuItem;
    private final FragmentTransaction fragmentTransaction;

    public NavigationFragmentSwitcher(final MenuItem menuItem,
                                      final FragmentTransaction fragmentTransaction) {
        this.menuItem = menuItem;
        this.fragmentTransaction = fragmentTransaction;
    }

    public void switchFragment() {
        switch (menuItem.getItemId()) {
            case R.id.navItemRecord:
                fragmentTransaction.replace(R.id.main_content_frame, new MoveRecordFragment());
                break;
            case R.id.navItemFeed:
                fragmentTransaction.replace(R.id.main_content_frame, new FeedFragment());
                                break;
            case R.id.navItemClubs:
                fragmentTransaction.replace(R.id.main_content_frame, new ClubsFragment());
                                break;
            case R.id.navItemSettings:
//                                transaction.replace(R.id.content_frame, new FeedFragment());
                                break;
            case R.id.navItemExit:
//                                transaction.replace(R.id.content_frame, new FeedFragment());
                                break;
                        }
    }
}
