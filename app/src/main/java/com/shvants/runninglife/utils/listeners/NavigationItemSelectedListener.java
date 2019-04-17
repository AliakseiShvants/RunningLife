package com.shvants.runninglife.utils.listeners;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.shvants.runninglife.R;
import com.shvants.runninglife.fragments.NavigationFragmentSwitcher;
import com.shvants.runninglife.ui.feed.FeedFragment;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static java.lang.Boolean.TRUE;

public class NavigationItemSelectedListener
        implements NavigationView.OnNavigationItemSelectedListener {

    private static NavigationItemSelectedListener instance;

    private final FragmentManager fragmentManager;
    private final NavigationView navigationView;
    private final DrawerLayout drawerLayout;

    private NavigationItemSelectedListener(final FragmentManager fragmentManager,
                                           final NavigationView navigationView,
                                           final DrawerLayout drawerLayout,
                                           final int resource) {
        this.fragmentManager = fragmentManager;
        this.navigationView = navigationView;
        this.drawerLayout = drawerLayout;

        setDefaultFragment(fragmentManager, resource);
    }

    public static NavigationItemSelectedListener getInstance(final FragmentManager fragmentManager,
                                                             final NavigationView navigationView,
                                                             final DrawerLayout drawerLayout,
                                                             final int resource) {
        if (instance == null) {
            instance = new NavigationItemSelectedListener(fragmentManager, navigationView,
                    drawerLayout, resource);
        }

        return instance;
    }

    private void setDefaultFragment(final FragmentManager fragmentManager,
                                    final int resource) {
        fragmentManager.beginTransaction()
                .add(R.id.main_container, new FeedFragment())
                .commit();

        navigationView.setCheckedItem(resource);
    }

    @Override
    public boolean onNavigationItemSelected(final MenuItem menuItem) {
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        new NavigationFragmentSwitcher(menuItem, transaction).switchFragment();
        transaction.commit();

        menuItem.setChecked(TRUE);
        drawerLayout.closeDrawers();

        return TRUE;
    }
}
