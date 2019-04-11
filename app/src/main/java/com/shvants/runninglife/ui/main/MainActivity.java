package com.shvants.runninglife.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.shvants.runninglife.BuildConfig;
import com.shvants.runninglife.R;
import com.shvants.runninglife.SummitNavigationFragmentSwitcher;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static java.lang.Boolean.TRUE;

public class MainActivity extends AppCompatActivity {

    public static final String STANDARD = "standard";
    private final FragmentManager fragmentManager = getSupportFragmentManager();

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBar actionBar;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(TRUE);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setTitle("Feed");

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationItemSelectedListener());
    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.active_bar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);

                return TRUE;
        }

        return super.onOptionsItemSelected(item);
    }

    private void changeActionBar(final CharSequence title) {
        actionBar.setTitle(title);
    }

    private class NavigationItemSelectedListener implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(final MenuItem menuItem) {
            final FragmentTransaction transaction =
                    getSupportFragmentManager().beginTransaction();

            if (BuildConfig.FLAVOR.equals(STANDARD)) {
                new SummitNavigationFragmentSwitcher(menuItem, transaction).switchFragment();
            }

            changeActionBar(menuItem.getTitle());

            transaction.commit();

            menuItem.setChecked(TRUE);
            drawerLayout.closeDrawers();

            return TRUE;
        }
    }
}
