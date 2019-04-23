package com.shvants.runninglife.utils

import com.shvants.runninglife.ui.model.MoveModelUi

interface IAdapter {

    fun setShowLastViewAsLoading(isShow: Boolean)

    fun onItemMove(fromPosition: Int, toPosition: Int)

    fun onItemDismiss(adapterPosition: Int)

    fun addItems(result: List<MoveModelUi>)
}
