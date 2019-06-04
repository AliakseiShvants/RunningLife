package com.shvants.runninglife.ui.view.base

import androidx.annotation.LayoutRes

interface BaseView {

    fun showMessage(message: String)

    fun showLoading()

    fun hideLoading()

    @LayoutRes
    fun getLayoutResId(): Int
}