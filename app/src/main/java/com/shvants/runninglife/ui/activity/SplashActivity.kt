package com.shvants.runninglife.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //todo check token is valid, refresh and redirect to main


        intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
