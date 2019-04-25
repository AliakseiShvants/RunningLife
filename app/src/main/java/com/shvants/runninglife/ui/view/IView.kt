package com.shvants.runninglife.ui.view

import android.content.Context
import androidx.annotation.LayoutRes
import com.shvants.runninglife.ui.model.BaseModelUi

interface IView {

    fun onViewInflated(context: Context)

    @LayoutRes
    fun getLayoutResId(): Int

    fun setView(vararg modelUi: BaseModelUi): IView
}