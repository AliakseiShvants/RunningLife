package com.shvants.runninglife.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shvants.runninglife.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //todo check token is valid, refresh

//        val intentUri = StravaHelper.getOauthUrl()

//        val intent = Intent(Intent.ACTION_VIEW, intentUri)
//        startActivity(intent)


//        var json = "empty"

//        val url = StravaHelper.getAuthorizeUrl()
//        val get = StravaRequest(url)
//        val handler = Handler()

//        Thread(Runnable {
//            Looper.prepare()

//            val response = get.get()

//            handler.post { json = response }
//        }).start()

        intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}
