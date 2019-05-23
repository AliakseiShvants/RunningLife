package com.shvants.runninglife.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.shvants.runninglife.R;
import com.shvants.runninglife.model.ui.SummaryAthleteUi;
import com.shvants.runninglife.mvp.contract.MainContract;
import com.shvants.runninglife.mvp.presenter.MainPresenter;
import com.shvants.runninglife.ui.fragment.FeedFragment;
import com.shvants.runninglife.ui.view.NavAthleteView;
import com.shvants.runninglife.utils.listener.NavigationItemSelectedListener;

import org.jetbrains.annotations.Nullable;

import static com.shvants.runninglife.utils.Const.ZERO;
import static java.lang.Boolean.TRUE;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private MainContract.Presenter presenter;

    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBar actionBar;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        Log.d("MainActivity", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(getApplicationContext(), this);
        presenter.onCreate();

        final Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(TRUE);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        presenter.loadAthlete();

        final NavigationItemSelectedListener navigationItemSelectedListener =
                NavigationItemSelectedListener.getInstance(fragmentManager, drawerLayout);
        navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener);

        setDefaultFragment();
    }

    private void setDefaultFragment() {
        final FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.main_fragment_container, FeedFragment.Companion.getInstance())
                .commit();

        navigationView.setCheckedItem(R.id.navItemFeed);
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

    public void setActionBarTitle(final String title) {
        actionBar.setTitle(title);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.onDestroy();
    }

    @Override
    public void setAthlete(@Nullable final SummaryAthleteUi athlete) {
        final NavAthleteView userView = navigationView.getHeaderView(ZERO)
                .findViewById(R.id.nav_user_view);

        userView.setView(athlete);
    }
}
