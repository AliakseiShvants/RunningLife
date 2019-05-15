package com.shvants.runninglife.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shvants.runninglife.R
import com.shvants.runninglife.ui.auth.AuthActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        stravaConnect.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)

            startActivity(intent)
        }
    }
}