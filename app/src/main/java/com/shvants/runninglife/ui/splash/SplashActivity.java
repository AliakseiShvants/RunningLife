package com.shvants.runninglife.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import com.shvants.runninglife.ui.main.MainActivity;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
