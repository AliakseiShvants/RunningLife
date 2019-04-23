package com.shvants.runninglife.ui.model

import androidx.annotation.DrawableRes

abstract class MoveModelUi(var id: Long,
                           var beginTime: Long,
                           var title: String = "Run",
                           var distance: Double = 0.0,
                           var time: Int = 0,
                           var elevation: Int? = 0,
                           var calories: Int? = 0,
                           var avgHr: Int? = 0,
                           @DrawableRes
                       var imgRes: Int)
