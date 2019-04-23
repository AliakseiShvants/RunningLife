package com.shvants.runninglife.ui.base

import android.content.Context
import androidx.annotation.LayoutRes
import com.shvants.runninglife.ui.model.MoveModelUi
import com.shvants.runninglife.ui.model.UserModelUi

interface IView {

    fun onViewInflated(context: Context)

    @LayoutRes
    fun getLayoutResId(): Int

    fun setView(user: UserModelUi, move: MoveModelUi): IView
}