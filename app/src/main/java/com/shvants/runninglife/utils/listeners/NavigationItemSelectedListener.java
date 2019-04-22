package com.shvants.runninglife.utils.listeners;

import android.view.MenuItem;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.shvants.runninglife.ui.fragments.NavigationFragmentSwitcher;

import static java.lang.Boolean.TRUE;

public class NavigationItemSelectedListener
        implements NavigationView.OnNavigationItemSelectedListener {

    private static NavigationItemSelectedListener instance;

    private final FragmentManager fragmentManager;
    private final DrawerLayout drawerLayout;

    private NavigationItemSelectedListener(final FragmentManager fragmentManager,
                                           final DrawerLayout drawerLayout) {
        this.fragmentManager = fragmentManager;
        this.drawerLayout = drawerLayout;
    }

    public static NavigationItemSelectedListener getInstance(final FragmentManager fragmentManager,
                                                             final DrawerLayout drawerLayout) {
        if (instance == null) {
            instance = new NavigationItemSelectedListener(fragmentManager, drawerLayout);
        }

        return instance;
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
