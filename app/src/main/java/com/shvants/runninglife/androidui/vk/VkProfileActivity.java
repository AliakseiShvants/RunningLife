package com.shvants.runninglife.androidui.vk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.shvants.runninglife.R;

import java.util.Objects;

public class VkProfileActivity extends AppCompatActivity {

    public static final boolean TRUE = true;
    public static final boolean FALSE = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vk_profile);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(TRUE);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(FALSE);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }
}
