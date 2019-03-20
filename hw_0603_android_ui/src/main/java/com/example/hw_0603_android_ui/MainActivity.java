package com.example.hw_0603_android_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View container = findViewById(R.id.container);
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final Intent intent = new Intent();

                switch (v.getId()) {
                    case R.id.vk:
                        intent.setClass(MainActivity.this, VkProfileActivity.class);
                        break;
                }

                startActivity(intent);
            }
        });
    }
}
