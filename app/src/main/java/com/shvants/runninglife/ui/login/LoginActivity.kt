package com.shvants.runninglife.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import com.shvants.runninglife.R
import com.shvants.runninglife.strava.StravaTokenLoader
import com.shvants.runninglife.ui.auth.AuthActivity
import com.shvants.runninglife.ui.base.BaseActivityView
import com.shvants.runninglife.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(), BaseActivityView, LoaderManager.LoaderCallbacks<String> {

    private var jsonString = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())

        stravaConnect.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)

            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            showLoading()

//            StravaPreferences(this).code =
//                    data?.getStringExtra(StravaHelper.CODE) ?: Const.EMPTY
//
//            LoaderManager.getInstance(this)
//                    .initLoader(0, null, this as LoaderManager.LoaderCallbacks<String>)
//                    .forceLoad()

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            hideLoading()
            showError()
        }
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<String> {
        return StravaTokenLoader(this)
    }

    override fun onLoadFinished(loader: Loader<String>, data: String?) {
        jsonString = data ?: "onLoadFinished"
    }

    override fun onLoaderReset(loader: Loader<String>) {}

    private fun showError() {
        errorView.visibility = View.VISIBLE
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