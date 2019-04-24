package com.shvants.runninglife.ui.model

class RunMoveModelUi(id: Long,
                     beginTime: Long,
                     title: String = "Run",
                     distance: Double = 0.0,
                     time: Int,
                     val elevation: Int? = 0,
                     val calories: Int? = 0,
                     val hr: Int? = 0,
                     val imgRes: Int = 0) : MoveModelUi(id, beginTime, title, distance, time)
