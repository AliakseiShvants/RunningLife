package com.shvants.runninglife.utils.listeners;

import android.view.MenuItem;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.shvants.runninglife.ui.fragments.NavigationFragmentSwitcher;
import com.shvants.runninglife.ui.model.UiUserModel;

import static java.lang.Boolean.TRUE;

public class NavigationItemSelectedListener
        implements NavigationView.OnNavigationItemSelectedListener {

    private static NavigationItemSelectedListener instance;

    private final UiUserModel user;
    private final FragmentManager fragmentManager;
    private final DrawerLayout drawerLayout;

    private NavigationItemSelectedListener(final UiUserModel user,
                                           final FragmentManager fragmentManager,
                                           final DrawerLayout drawerLayout) {
        this.user = user;
        this.fragmentManager = fragmentManager;
        this.drawerLayout = drawerLayout;
    }

    public static NavigationItemSelectedListener getInstance(final UiUserModel user,
                                                             final FragmentManager fragmentManager,
                                                             final DrawerLayout drawerLayout) {
        if (instance == null) {
            instance = new NavigationItemSelectedListener(user, fragmentManager, drawerLayout);
        }

        return instance;
    }

    @Override
    public boolean onNavigationItemSelected(final MenuItem menuItem) {
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        new NavigationFragmentSwitcher(user, menuItem, transaction).switchFragment();
        transaction.commit();

        menuItem.setChecked(TRUE);
        drawerLayout.closeDrawers();

        return TRUE;
    }
}
