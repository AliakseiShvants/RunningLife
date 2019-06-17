package com.shvants.runninglife.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.View
import com.google.gson.Gson
import com.shvants.runninglife.R
import com.shvants.runninglife.model.gson.OauthResponse
import com.shvants.runninglife.mvp.contract.LoginContract
import com.shvants.runninglife.mvp.presenter.LoginPresenter
import com.shvants.runninglife.strava.StravaHelper
import com.shvants.runninglife.strava.StravaPreferences
import com.shvants.runninglife.strava.StravaRequest
import com.shvants.runninglife.utils.Const
import com.shvants.runninglife.utils.Const.EMPTY
import com.shvants.runninglife.utils.Const.MINUS_LONG
import com.shvants.runninglife.utils.Const.ONE
import com.shvants.runninglife.utils.ICallback
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.Executors


class LoginActivity : BaseActivity(), LoginContract.View {

    private lateinit var preferences: StravaPreferences
    private lateinit var presenter: LoginPresenter
    private val executor = Executors.newCachedThreadPool()
    private val handler = Handler()

    private val callback = object : ICallback<Long> {

        override fun onResult(result: Long) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }

        override fun onError(message: String) {
            showMessage(message)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())

        preferences = StravaPreferences(applicationContext)
        presenter = LoginPresenter(this@LoginActivity)

        stravaConnect.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivityForResult(intent, ONE)

            //test
//            handleTokenResponse("877b390046ce7751815aaf6a8e8d32b3f69e5aea")
        }

        slideImages()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ONE && resultCode == Activity.RESULT_OK) {

            preferences.code = data?.getStringExtra(StravaHelper.CODE) ?: EMPTY

            handleTokenResponse(preferences.code)
        } else {
            showMessage(resources.getString(R.string.login_error))
        }
    }

    private fun handleTokenResponse(code: String) {
        executor.execute {
            val tokenBody = StravaHelper.getTokenBody(code)
            val responseString = StravaRequest().post(StravaHelper.TOKEN_BASE_URL,
                    preferences.accessToken, tokenBody)

            if (responseString != EMPTY) {
                try {
                    fillPreferences(responseString)
                } catch (e: Exception) {
                    callback.onError(Const.ERR.JSON)
                }
            }

            val result = presenter.setLoggedInAthlete()

            handler.post {
                if (result != MINUS_LONG) {
                    callback.onResult(result)
                } else {
                    callback.onError(Const.ERR.LOGIN)
                }
            }
        }
    }

    private fun fillPreferences(json: String) {
        val oauthResponse = Gson().fromJson(json, OauthResponse::class.java)

        if (oauthResponse != null) {
            preferences.athleteId = oauthResponse.athlete?.id ?: Const.ZERO_LONG
            preferences.fullName = "${oauthResponse.athlete?.firstname} ${oauthResponse.athlete?.lastname}"
            preferences.profile = oauthResponse.athlete?.profile ?: EMPTY
            preferences.profileMedium = oauthResponse.athlete?.profileMedium ?: EMPTY
            preferences.location = "${oauthResponse.athlete?.city}${Const.COMMA} " +
                    "${oauthResponse.athlete?.state}"
            preferences.sex = oauthResponse.athlete?.sex ?: EMPTY
            preferences.summit = oauthResponse.athlete?.summit ?: false

            preferences.accessToken = oauthResponse.accessToken ?: EMPTY
            preferences.expiresAt = oauthResponse.expiresAt ?: Const.ZERO
            preferences.expiresIn = oauthResponse.expiresIn ?: Const.ZERO
            preferences.refreshToken = oauthResponse.refreshToken ?: EMPTY
            preferences.tokenType = oauthResponse.tokenType ?: EMPTY
        }
    }

    override fun showMessage(message: String) {
        errorView.visibility = View.VISIBLE
        errorView.text = message
    }

    private fun slideImages() {
        val imgArr = arrayOf(
                R.drawable.login_slider0,
                R.drawable.login_slider1,
                R.drawable.login_slider2)

        var counter = 0

        executor.execute {
            while (true) {
                val i = counter % 3

                handler.post { loginImageView.setImageResource(imgArr[i]) }

                counter += 1
                SystemClock.sleep(5000)
            }
        }
    }

    override fun getLayoutResId() = R.layout.activity_login
}