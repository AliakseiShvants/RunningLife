package com.shvants.runninglife.ui.main;

import android.os.Bundle;
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
import com.shvants.runninglife.data.base.MetaAthlete;
import com.shvants.runninglife.data.base.Repository;
import com.shvants.runninglife.data.db.DbRepository;
import com.shvants.runninglife.ui.custom.NavAthleteView;
import com.shvants.runninglife.ui.feed.FeedFragment;
import com.shvants.runninglife.utils.listener.NavigationItemSelectedListener;

import org.jetbrains.annotations.NotNull;

import static com.shvants.runninglife.utils.Const.ZERO;
import static java.lang.Boolean.TRUE;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private final Repository repository = DbRepository.Companion.getInstance();
    private MainContract.Presenter presenter;

    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBar actionBar;

//    private DbHelper dbHelper = new DbHelper(this, null, DATABASE_VERSION);

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this, repository);

        final Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(TRUE);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        setDefaultFragment();

        final NavigationItemSelectedListener navigationItemSelectedListener =
                NavigationItemSelectedListener.getInstance(fragmentManager, drawerLayout);
        navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener);

        presenter.onCreate();
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
    public void setAthleteToView(@NotNull final MetaAthlete athlete) {
        final NavAthleteView userView = navigationView.getHeaderView(ZERO)
                .findViewById(R.id.nav_user_view);

        userView.setView(athlete);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.onDestroy();
    }
}
