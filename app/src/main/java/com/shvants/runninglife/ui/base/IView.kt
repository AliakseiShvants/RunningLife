package com.shvants.runninglife.ui.base

import android.content.Context
import androidx.annotation.LayoutRes
import com.shvants.runninglife.ui.model.UiMoveModel
import com.shvants.runninglife.ui.model.UiUserModel

interface IView {

    fun onViewInflated(context: Context)

    @LayoutRes
    fun getLayoutResId(): Int

    fun setView(user: UiUserModel, move: UiMoveModel): IView
}