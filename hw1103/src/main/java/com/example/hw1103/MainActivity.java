package com.example.hw1103;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.hw1103.backend.User;
import com.example.hw1103.fragments.FeedFragment;
import com.example.hw1103.fragments.TrainingRecordFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static final boolean TRUE = true;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setDisplayHomeAsUpEnabled(TRUE);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        final FragmentTransaction transaction =
                                getSupportFragmentManager().beginTransaction();

                        switch (menuItem.getItemId()){
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                final User user = getUser();
                final CompoundView compoundView = findViewById(R.id.nav_compound_view);
                compoundView.setUserToCompoundView(user);

                return TRUE;
        }
        return super.onOptionsItemSelected(item);
    }


    private User getUser(){
        final User user = new User();
        user.setIcon(R.drawable.ic_account);
        user.setName("Aliaksei Shvants");
        user.setEmail("ashvants91@gmail.com");

        return user;
    }



}
