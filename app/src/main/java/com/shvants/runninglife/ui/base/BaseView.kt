package com.shvants.runninglife.ui.base

import android.content.Context
import androidx.annotation.LayoutRes

interface BaseView {

//    fun showLoading()
//
//    fun hideLoading()
//
//    fun onError(message: String)
//
//    fun showMessage(message: String)

    fun onViewInflated(context: Context)

    @LayoutRes
    fun getLayoutResId(): Int
}