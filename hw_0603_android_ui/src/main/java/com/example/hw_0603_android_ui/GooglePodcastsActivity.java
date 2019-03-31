package com.example.hw_0603_android_ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import static java.lang.Boolean.TRUE;

public class GooglePodcastsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_podcasts);

        final Toolbar toolbar = findViewById(R.id.gp_toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(TRUE);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_search);
        actionBar.setDisplayShowTitleEnabled(TRUE);

        final View[] views = {
                findViewById(R.id.gp_first_image), findViewById(R.id.gp_second_image),
                findViewById(R.id.gp_third_image), findViewById(R.id.gp_forth_image),
                findViewById(R.id.gp_fifth_image)
        };

        final int[] imageIds = {
                R.drawable.cash, R.drawable.devzen, R.drawable.petit, R.drawable.red, R.drawable.ted
        };

        new ImageSetter(views, imageIds).setImageToViews();

        final TabLayout tabLayout = findViewById(R.id.gp_card_view_tab_layout);
        final ViewPager viewPager = findViewById(R.id.gp_card_view_view_pager);
        final GPPageAdapter adapter = new GPPageAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.gp_action_bar, menu);

        return super.onCreateOptionsMenu(menu);
    }
}
