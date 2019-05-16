package com.shvants.runninglife.ui.auth

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.graphics.Bitmap
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.shvants.runninglife.R
import com.shvants.runninglife.data.web.model.OauthResponse
import com.shvants.runninglife.strava.StravaHelper
import com.shvants.runninglife.strava.StravaPreferences
import com.shvants.runninglife.strava.StravaRequest
import com.shvants.runninglife.ui.base.BaseActivityView
import com.shvants.runninglife.utils.Const
import com.shvants.runninglife.utils.ICallback
import kotlinx.android.synthetic.main.activity_auth.*
import java.util.concurrent.Executors

class AuthActivity : AppCompatActivity(), BaseActivityView {

    private val handler = Handler()
    private val executor = Executors.newCachedThreadPool()
    private var code: String = Const.EMPTY
    private var jsonString = Const.EMPTY

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())

        val authUrl = StravaHelper.getAuthorizeUrl()

        authView.apply {
            webViewClient = RLWebViewClient()
        }
        authView.settings.apply {
            javaScriptEnabled = true
        }
        authView.loadUrl(authUrl)
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        authView.visibility = View.GONE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        authView.visibility = View.VISIBLE
    }

    override fun getLayoutResId() = R.layout.activity_auth

    inner class RLWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            handleUrl(url)

            return true
        }

        @TargetApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            handleUrl(request.url.toString())

            return true
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)

            handleUrl(url)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)

            hideLoading()
        }

        private fun extractCode(url: String?) =
                if (url != null && url.startsWith(StravaHelper.REDIRECT_URI_VALUE)) {
                    Uri.parse(url).getQueryParameter(StravaHelper.CODE)
                } else {
                    Const.EMPTY
                }

        fun getNewToken(code: String, callback: ICallback<String>) {
            executor.execute {
                val requestBody = StravaHelper.getTokenBody(code)
                val jsonString = StravaRequest(StravaHelper.TOKEN_BASE_URL).post(requestBody)

                handler.post { callback.onResult(jsonString) }
            }

//            Thread(Runnable {
//                val requestBody = StravaHelper.getTokenBody(code)
//                var jsonString = StravaRequest(StravaHelper.TOKEN_BASE_URL).post(requestBody)
//
//                handler.post { callback.onResult(jsonString) }
//            }).start()
//            Looper.prepare()
        }

        private fun handleUrl(url: String?) {
            if (code != Const.EMPTY) return

            showLoading()

            code = extractCode(url)

            if (code != Const.EMPTY) {
                // post to
                /*val requestBody = StravaHelper.getTokenBody(code)
                var jsonString = StravaRequest(StravaHelper.TOKEN_BASE_URL).post(requestBody)*/
//                var jsonString = ""


                OauthResponseLoader().execute(StravaPreferences(this@AuthActivity).code)


//                getNewToken(code, object : ICallback<String> {
//
//                    override fun onResult(result: String) {
//                        jsonString = result
//                        println(jsonString)
//                    }
//
//                    override fun onError(message: String) {
//                        println("Error!!!")
//                    }
//                })

                //jsonString = StravaHelper.tokenString

                val oauthResponse = Gson().fromJson(jsonString, OauthResponse::class.java)


                val preferences = StravaPreferences(this@AuthActivity)
                if (oauthResponse != null) {
                    preferences.accessToken = oauthResponse.accessToken ?: Const.EMPTY
                    preferences.athleteId = oauthResponse.athlete?.id ?: Const.ZERO
                    preferences.expiresAt = oauthResponse.expiresAt ?: Const.ZERO
                    preferences.expires_in = oauthResponse.expiresIn ?: Const.ZERO
                    preferences.refreshToken = oauthResponse.refreshToken ?: Const.EMPTY
                    preferences.tokenType = oauthResponse.tokenType ?: Const.EMPTY
                }
                // put in shared preferences
//            val resultCode = if (url.contains(VKTokenPreferences.ACCESS_TOKEN)) {
//                val uri = Uri.parse(url.replace('#', '?'))
//                val token = uri.getQueryParameter(VKTokenPreferences.ACCESS_TOKEN)
//                intent.putExtra(VKTokenPreferences.EXTRA_ACCESS_TOKEN, token)
//                Activity.RESULT_OK
//            } else {
//                Activity.RESULT_CANCELED
//            }

//            setResult(resultCode, intent)
//                finish()

            }

        }

        inner class OauthResponseLoader : AsyncTask<String, Void, String>() {

            override fun onPostExecute(result: String?) {
                jsonString = result ?: "onPostExecute"
            }

            override fun doInBackground(vararg params: String?): String {
                val tokenBody = StravaHelper.getTokenBody(params[0])
                val result = StravaRequest(StravaHelper.TOKEN_BASE_URL).post(tokenBody)

                return result
            }
        }

    }
}
