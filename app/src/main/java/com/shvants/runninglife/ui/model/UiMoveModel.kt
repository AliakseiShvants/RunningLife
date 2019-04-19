package com.shvants.runninglife.ui.model

data class UiMoveModel(var id: Long,
                       var title: String,
                       var distance: Double,
                       var time: Long,
                       var elevation: Int,
                       var calories: Int,
                       var avgHr: Int)
