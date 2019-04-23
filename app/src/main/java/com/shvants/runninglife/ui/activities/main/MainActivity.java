package com.shvants.runninglife.ui.activities.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.navigation.NavigationView;
import com.shvants.runninglife.R;
import com.shvants.runninglife.ui.fragments.feed.FeedFragment;
import com.shvants.runninglife.ui.model.UserModelUi;
import com.shvants.runninglife.ui.view.UserView;
import com.shvants.runninglife.utils.listener.NavigationItemSelectedListener;
import com.shvants.runninglife.utils.service.IService;
import com.shvants.runninglife.utils.service.UserService;

import static com.shvants.runninglife.utils.Const.ZERO;
import static java.lang.Boolean.TRUE;

public class MainActivity extends AppCompatActivity {

    private final FragmentManager fragmentManager = getSupportFragmentManager();

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBar actionBar;
    private final IService<UserModelUi> userService = new UserService();

//    private DbHelper dbHelper = new DbHelper(this, null, DATABASE_VERSION);

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(TRUE);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        final UserView userView = navigationView.getHeaderView(ZERO)
                .findViewById(R.id.nav_user_view);
        userView.findViewById(R.id.userLocation).setVisibility(View.VISIBLE);

        final UserModelUi userModel = userService.getEntity(0);
        userView.setUser(userModel);

        setDefaultFragment();

        final NavigationItemSelectedListener navigationItemSelectedListener =
                NavigationItemSelectedListener.getInstance(userModel, fragmentManager, drawerLayout);
        navigationView.setNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    private void setDefaultFragment() {
        final FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.main_fragment_container, FeedFragment.getInstance())
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
}
