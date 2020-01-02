package com.shvants.runninglife.ui.view.base

import androidx.annotation.LayoutRes

interface BaseCustomView<T> {

    fun setView(item: T)

    @LayoutRes
    fun getLayoutResId(): Int
}