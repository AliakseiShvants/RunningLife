package com.shvants.runninglife.ui.login;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.shvants.runninglife.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.stravaConnect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                final Uri intentUri = Uri.parse("https://www.strava.com/oauth/mobile/authorize")
                        .buildUpon()
                        .appendQueryParameter("client_id", "34943")
                        .appendQueryParameter("redirect_uri", "https://localhost/callback")
                        .appendQueryParameter("response_type", "code")
                        .appendQueryParameter("approval_prompt", "auto")
                        .appendQueryParameter("scope", "activity:read")
                        .build();

                final FrameLayout frame = findViewById(R.id.loginFrame);
                frame.removeAllViews();
                LayoutInflater.from(v.getContext()).inflate(R.layout.layout_login_form, frame, true);
            }
        });

    }


}
