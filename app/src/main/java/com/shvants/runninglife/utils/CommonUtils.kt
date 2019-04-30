package com.shvants.runninglife.utils

import android.content.Context
import android.widget.ProgressBar

object CommonUtils {

    fun showLoadingBar(context: Context) {
        val progressBar = ProgressBar(context)
        progressBar.showContextMenu()
    }
}