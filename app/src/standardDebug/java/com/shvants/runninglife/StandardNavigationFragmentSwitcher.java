package com.shvants.runninglife;

import android.view.MenuItem;

import androidx.fragment.app.FragmentTransaction;

public class StandardNavigationFragmentSwitcher {

    private MenuItem menuItem;
    private FragmentTransaction fragmentTransaction;

    public StandardNavigationFragmentSwitcher(final MenuItem menuItem,
                                              final FragmentTransaction fragmentTransaction) {
        this.menuItem = menuItem;
        this.fragmentTransaction = fragmentTransaction;
    }

    public void switchFragment() {
        switch (menuItem.getItemId()) {
            case R.id.navItemFeed:
//                                transaction.replace(R.id.content_frame, new TrainingRecordFragment());
                break;
            case R.id.navItemClubs:
//                                transaction.replace(R.id.content_frame, new FeedFragment());
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
