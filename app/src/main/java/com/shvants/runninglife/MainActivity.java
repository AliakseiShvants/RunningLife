package com.shvants.runninglife;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import static java.lang.Boolean.TRUE;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(TRUE);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setTitle(R.string.feed);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        setNavigationListener(navigationView);
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

    private void setNavigationListener(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(final MenuItem menuItem) {
                        final FragmentTransaction transaction =
                                getSupportFragmentManager().beginTransaction();

                        switch (menuItem.getItemId()) {
                            case R.id.nav_record:
//                                transaction.replace(R.id.content_frame, new TrainingRecordFragment());
                                break;
                            case R.id.nav_feed:
//                                transaction.replace(R.id.content_frame, new FeedFragment());
                                break;
                            case R.id.nav_clubs:
//                                transaction.replace(R.id.content_frame, new FeedFragment());
                                break;
                        }

                        transaction.commit();

                        menuItem.setChecked(TRUE);
                        drawerLayout.closeDrawers();

                        return TRUE;
                    }
                });
    }
}
