package com.shvants.runninglife.utils

import android.content.Context
import android.widget.ProgressBar
import java.util.concurrent.Executors

object CommonUtils {

    fun showLoadingBar(context: Context) {
        val progressBar = ProgressBar(context)
        progressBar.showContextMenu()
    }

    val EXECUTOR = Executors.newCachedThreadPool()
}