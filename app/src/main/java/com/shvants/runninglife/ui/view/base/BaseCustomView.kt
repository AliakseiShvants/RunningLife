package com.shvants.runninglife.ui.view.base

import android.content.Context
import androidx.annotation.LayoutRes

interface BaseCustomView<T> {

    fun setView(item: T?)

    fun onViewInflated(context: Context)

    @LayoutRes
    fun getLayoutResId(): Int
}