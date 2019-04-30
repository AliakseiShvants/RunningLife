package com.shvants.runninglife.ui.base

import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), IView {

    private lateinit var progressBar: ProgressBar

    override fun showLoading() {
        progressBar =
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showMessage(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}