package com.shvants.runninglife.ui.view.base

import android.content.Context

interface BaseCustomView : BaseView {

    fun setView(vararg data: Any?): BaseCustomView

    fun onViewInflated(context: Context)
}