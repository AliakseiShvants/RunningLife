package com.shvants.runninglife.ui.base

import android.content.Context
import androidx.annotation.LayoutRes

interface IView {

    fun onViewInflated(context: Context)

    @LayoutRes
    fun getLayoutResId(): Int
}