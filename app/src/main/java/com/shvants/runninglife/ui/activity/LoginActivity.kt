package com.shvants.runninglife.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.util.Log.d
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.shvants.runninglife.R
import com.shvants.runninglife.model.gson.OauthResponse
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


    private var jsonString = "jsonString"
    private val handler = Handler()
    private lateinit var preferences: StravaPreferences

    private val callback = object : ICallback<String> {

        override fun onResult(result: String) {
            d(TAG, "callback onResult")

            fillPreferences(result)

            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }

        override fun onError(message: String) {
            d(TAG, "callback onError")
            showError(message)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())

        preferences = StravaPreferences(applicationContext)

        stravaConnect.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivityForResult(intent, 1)

            //todo remove
//            onActivityResult(1, -1, null)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            showLoading()

            preferences.code = data?.getStringExtra(StravaHelper.CODE) ?: ""
            getTokenResponse()
            d(TAG, "getTokenResponse end")

//            jsonString = StravaHelper.tokenString
            //todo extract to method

        } else {
            hideLoading()
            showError(resources.getString(R.string.login_error))
        }
    }

    private fun fillPreferences(json: String) {
        val oauthResponse = Gson().fromJson(json, OauthResponse::class.java)

        d(TAG, oauthResponse.toString())

        if (oauthResponse != null) {
            preferences.athleteId = oauthResponse.athlete?.id ?: Const.ZERO
            preferences.fullName = "${oauthResponse.athlete?.firstName} ${oauthResponse.athlete?.lastName}"
            preferences.profile = oauthResponse.athlete?.profile ?: Const.EMPTY
            preferences.profileMedium = oauthResponse.athlete?.profileMedium ?: Const.EMPTY
            preferences.location = "${oauthResponse.athlete?.city}${Const.COMMA} " +
                    "${oauthResponse.athlete?.state}"

            preferences.accessToken = oauthResponse.accessToken ?: Const.EMPTY
            preferences.expiresAt = oauthResponse.expiresAt ?: Const.ZERO
            preferences.expires_in = oauthResponse.expiresIn ?: Const.ZERO
            preferences.refreshToken = oauthResponse.refreshToken ?: Const.EMPTY
            preferences.tokenType = oauthResponse.tokenType ?: Const.EMPTY
        }

        d(TAG, preferences.fullName)
    }

    private fun getTokenResponse() {
        d(TAG, "getTokenResponse")
        executor.execute {
            d(TAG, "getTokenResponse Thread")
            val tokenBody = StravaHelper.getTokenBody(preferences.code)
            val result = StravaRequest().post(StravaHelper.TOKEN_BASE_URL, tokenBody)
            d(TAG, "getTokenResponse Thread=$result")
            handler.post {
                d(TAG, "getTokenResponse Thread handler.post")
                if (result != "") callback.onResult(result) else callback.onError(ERR_MSG)
            }
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

    inner class LoginLoader : AsyncTask<String, Void, String>() {

        private val tag = LoginLoader::class.simpleName

        override fun onPreExecute() {
            d(tag, "onPreExecute")
            showLoading()
        }

        override fun doInBackground(vararg params: String?): String {
            d(tag, "doInBackground")
            SystemClock.sleep(3000)
            return StravaHelper.tokenString
        }

        override fun onPostExecute(result: String?) {
            d(tag, "onPostExecute")
            jsonString = result ?: "onPostExecute"
            hideLoading()
        }
    }
}