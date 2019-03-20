package com.example.hw_0603_android_ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import java.util.Objects;

public class VkProfileActivity extends AppCompatActivity {

    public static final boolean TRUE = true;
    public static final boolean FALSE = false;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vk_profile);

        final Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(TRUE);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(FALSE);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.active_bar, menu);

        return super.onCreateOptionsMenu(menu);
    }
}
