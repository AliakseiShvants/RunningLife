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
import com.shvants.runninglife.utils.Const
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val authUrl = StravaHelper.getAuthorizeUrl()

        authView.apply {
            webViewClient = RLWebViewClient()
        }
        authView.settings.apply {
            javaScriptEnabled = true
        }
        authView.loadUrl(authUrl)
    }

    private fun showAuthView() {
        progressBar.visibility = View.GONE
        authView.visibility = View.VISIBLE
    }

    private fun showProgress() {
        progressBar.visibility = View.VISIBLE
        authView.visibility = View.GONE
    }

    inner class RLWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
//            view.loadUrl(url)

            extractToken(url)
            return true
        }

        @TargetApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
//            view.loadUrl(get.url.toString())

            extractToken(request.url.toString())

            return true
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)

            extractToken(url)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            showAuthView()
        }

        private fun extractCode(url: String?): String? {
            return if (url != null && url.startsWith(StravaHelper.REDIRECT_URI_VALUE)) {
                val uri = Uri.parse(url)
                uri.getQueryParameter(StravaHelper.CODE)
            } else {
                Const.EMPTY
            }

//            showProgress()
//            val intent = Intent()
//
//            code =
//                    if (url.contains(StravaHelper.CODE)) {
//                        val uri = Uri.parse(url.replace('#', '?'))
//                        uri.getQueryParameter(StravaHelper.CODE)
////                intent.putExtra(VKTokenPreferences.EXTRA_ACCESS_TOKEN, token)
////                Activity.RESULT_OK
//                    } else {
////                Activity.RESULT_CANCELED
//                        return code
//                    }
//
//            setResult(resultCode, intent)
//            clearCookie()
//            finish()
//
//            return code
        }

        private fun extractToken(url: String?) {
            showProgress()

            val code = extractCode(url)

            if (code != Const.EMPTY) {
                val tokenBody = StravaHelper.getTokenBody(code)
                val result = StravaRequest(StravaHelper.TOKEN_BASE_URL).post(tokenBody)

                println(result)
            }


//            val token = Uri.parse(tokenUrl).getQueryParameter(StravaHelper.ACCESS_TOKEN)

//            intent.putExtra(StravaHelper.ACCESS_TOKEN, token)


        }
    }


}
