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

        setListener(findViewById(R.id.vk), VkProfileActivity.class);

    }

    private void setListener(final View view, final Class classs) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.vk:
                        startActivity(new Intent(getBaseContext(), classs));
                        break;
                }
            }
        });
    }
}
