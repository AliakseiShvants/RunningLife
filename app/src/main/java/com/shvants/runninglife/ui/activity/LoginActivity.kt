package com.shvants.runninglife.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.shvants.runninglife.R
import com.shvants.runninglife.model.gson.OauthResponse
import com.shvants.runninglife.mvp.presenter.LoginPresenter
import com.shvants.runninglife.strava.StravaHelper
import com.shvants.runninglife.strava.StravaPreferences
import com.shvants.runninglife.strava.StravaRequest
import com.shvants.runninglife.ui.view.base.BaseActivityView
import com.shvants.runninglife.utils.Const
import com.shvants.runninglife.utils.ICallback
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.Executors


class LoginActivity : AppCompatActivity(), BaseActivityView {

    private val TAG = LoginActivity::class.simpleName
    private val executor = Executors.newCachedThreadPool()
    private val ERR_MSG = "Some error with login"
    private val ERR_JSON = "Invalid format of server response"

    private val handler = Handler()
    private lateinit var preferences: StravaPreferences
    private lateinit var presenter: LoginPresenter

    private val callback = object : ICallback<String> {

        override fun onResult(result: String?) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }

        override fun onError(message: String) {
            showError(message)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())

        preferences = StravaPreferences(applicationContext)

        presenter = LoginPresenter(this@LoginActivity)
        presenter.onCreate()

        stravaConnect.setOnClickListener {
            //            val intent = Intent(this, AuthActivity::class.java)
//            startActivityForResult(intent, 1)

            //test
            handleTokenResponse("2d40f76f629f44fcf22141a0e5d48a254333d8b7")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            showLoading()

            preferences.code = data?.getStringExtra(StravaHelper.CODE) ?: ""

            handleTokenResponse(preferences.code)
        } else {
            hideLoading()
            showError(resources.getString(R.string.login_error))
        }
    }

    private fun handleTokenResponse(code: String) {
        executor.execute {
            val tokenBody = StravaHelper.getTokenBody(code)
            val responseString = StravaRequest().post(StravaHelper.TOKEN_BASE_URL, tokenBody)

            if (responseString != Const.EMPTY) {
                try {
                    fillPreferences(responseString)
                } catch (e: JsonSyntaxException) {
                    callback.onError(ERR_JSON)
                }
            }

            val result = presenter.setLoggedInAthlete(preferences)

            handler.post {
                if (result != -1L) callback.onResult(null) else callback.onError(ERR_MSG)
            }
        }
    }

    private fun fillPreferences(json: String) {
        val oauthResponse = Gson().fromJson(json, OauthResponse::class.java)

        if (oauthResponse != null) {
            preferences.athleteId = oauthResponse.athlete?.id ?: Const.ZERO_LONG
            preferences.fullName = "${oauthResponse.athlete?.firstName} ${oauthResponse.athlete?.lastName}"
            preferences.profile = oauthResponse.athlete?.profile ?: Const.EMPTY
            preferences.profileMedium = oauthResponse.athlete?.profileMedium ?: Const.EMPTY
            preferences.location = "${oauthResponse.athlete?.city}${Const.COMMA} " +
                    "${oauthResponse.athlete?.state}"
            preferences.sex = oauthResponse.athlete?.sex ?: Const.EMPTY
            preferences.summit = oauthResponse.athlete?.summit ?: false

            preferences.accessToken = oauthResponse.accessToken ?: Const.EMPTY
            preferences.expiresAt = oauthResponse.expiresAt ?: Const.ZERO
            preferences.expires_in = oauthResponse.expiresIn ?: Const.ZERO
            preferences.refreshToken = oauthResponse.refreshToken ?: Const.EMPTY
            preferences.tokenType = oauthResponse.tokenType ?: Const.EMPTY
        }
    }

    private fun showError(message: String) {
        hideLoading()
        errorView.visibility = View.VISIBLE
        errorView.text = message
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        stravaConnect.visibility = View.GONE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        stravaConnect.visibility = View.VISIBLE
    }

    override fun getLayoutResId() = R.layout.activity_login
}