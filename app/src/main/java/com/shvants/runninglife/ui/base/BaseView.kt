package com.shvants.runninglife.ui.base

import androidx.annotation.LayoutRes

interface BaseView {
//
//    fun onError(message: String)
//
//    fun showMessage(message: String)

    @LayoutRes
    fun getLayoutResId(): Int
}