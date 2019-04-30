package com.shvants.runninglife.ui.base

import android.content.Context
import androidx.annotation.LayoutRes
import com.shvants.runninglife.data.Data

interface IView {

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

    fun setView(vararg data: Data): IView
}