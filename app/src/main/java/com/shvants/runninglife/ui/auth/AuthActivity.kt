package com.shvants.runninglife.ui.auth

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.shvants.runninglife.R
import com.shvants.runninglife.strava.StravaHelper
import com.shvants.runninglife.strava.StravaRequest
import com.shvants.runninglife.ui.base.BaseActivityView
import com.shvants.runninglife.utils.Const
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity(), BaseActivityView {

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
            return handleUrl(url)
        }

        @TargetApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            return handleUrl(request.url.toString())
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)

            handleUrl(url)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            hideLoading()
        }

        private fun extractCode(url: String?) =
                if (url != null && url.startsWith(StravaHelper.REDIRECT_URI_VALUE)) {
                    Uri.parse(url).getQueryParameter(StravaHelper.CODE)
            } else {
                Const.EMPTY
            }

        private fun handleUrl(url: String?): Boolean {
            showLoading()

            val code = extractCode(url)

            if (code != Const.EMPTY) {
                // post to
                val requestBody = StravaHelper.getTokenBody(code)
                val jsonString = StravaRequest(StravaHelper.TOKEN_BASE_URL).post(requestBody)
                //parse response

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
                finish()

                return true
            }

            return false
        }

    }
}
