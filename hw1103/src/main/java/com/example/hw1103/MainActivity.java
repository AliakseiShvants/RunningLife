package com.example.hw1103;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.hw1103.backend.ColorManager;
import com.example.hw1103.backend.User;
import com.example.hw1103.fragments.FeedFragment;
import com.example.hw1103.fragments.TrainingRecordFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static final boolean TRUE = true;
    public static final int NULL = 0;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private CompoundView compoundView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(TRUE);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        final User user = getUser();
        compoundView = navigationView.getHeaderView(NULL).findViewById(R.id.nav_compound_view);
        compoundView.setUserToCompoundView(user);

        final ImageView icon = compoundView.findViewById(R.id.compound_view_icon);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final int color = ColorManager.getRandomColor();
                compoundView.changeColor(color);
            }
        });

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(final MenuItem menuItem) {
                        final FragmentTransaction transaction =
                                getSupportFragmentManager().beginTransaction();

                        switch (menuItem.getItemId()) {
                            case R.id.nav_record:
                                transaction.replace(R.id.content_frame, new TrainingRecordFragment());
                                break;
                            case R.id.nav_feed:
                                transaction.replace(R.id.content_frame, new FeedFragment());
                                break;
                            case R.id.nav_clubs:
                                transaction.replace(R.id.content_frame, new FeedFragment());
                                break;
                        }

                        transaction.commit();

                        menuItem.setChecked(TRUE);
                        drawerLayout.closeDrawers();

                        return TRUE;
                    }
                });
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

    private User getUser() {
        final User user = new User();
        user.setIcon(R.drawable.ic_account);
        user.setName("Aliaksei Shvants");
        user.setEmail("ashvants91@gmail.com");

        return user;
    }
}
