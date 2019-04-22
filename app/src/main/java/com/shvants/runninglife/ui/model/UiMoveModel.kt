package com.shvants.runninglife.ui.model

import androidx.annotation.DrawableRes

data class UiMoveModel(var id: Long,
                       var startTime: Long,
                       var title: String = "Run",
                       var distance: Double? = 0.0,
                       var time: Long? = 0,
                       var elevation: Int? = 0,
                       var calories: Int? = 0,
                       var avgHr: Int? = 0,
                       @DrawableRes
                       var imgRes: Int?)
