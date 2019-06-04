package com.shvants.runninglife.ui.activity

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log.d
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.shvants.runninglife.R
import com.shvants.runninglife.strava.StravaHelper
import com.shvants.runninglife.ui.view.base.BaseView
import com.shvants.runninglife.utils.Const
import kotlinx.android.synthetic.main.activity_auth.*
import java.util.concurrent.Executors

class AuthActivity : AppCompatActivity(), BaseView {
    override fun showMessage(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val TAG = AuthActivity::class.simpleName

    private val handler = Handler()
    private val executor = Executors.newCachedThreadPool()
    private var code: String = Const.EMPTY
    private var jsonString = Const.EMPTY
    private var loginRedirected = false

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
            return handleUrl(view, url)
        }

        @TargetApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            return handleUrl(view, request.url.toString())
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)

            handleUrl(view, url)
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

        private fun handleUrl(view: WebView?, url: String?): Boolean {
            showLoading()

            when {
//                code != Const.EMPTY -> {}
                url == "https://www.strava.com/session" -> {
//                    return false
                }
                url == StravaHelper.LOGIN_URL && loginRedirected -> {
//                    return false
                }
                url == StravaHelper.LOGIN_URL -> {
                    view?.loadUrl(url)
                    loginRedirected = true

//                    return false
                }

                url?.startsWith(StravaHelper.REDIRECT_URI_VALUE) ?: false -> {
                    code = extractCode(url)
                    d(TAG, code)

                    val intent = Intent()
                    val resultCode =
                            if (code != Const.EMPTY) {
                                intent.putExtra(StravaHelper.CODE, code)
                                Activity.RESULT_OK
                            } else {
                                Activity.RESULT_CANCELED
                            }

                    setResult(resultCode, intent)
                    finish()
                }
            }

            return true
        }
    }
}
