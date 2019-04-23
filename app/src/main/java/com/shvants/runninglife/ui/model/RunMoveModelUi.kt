package com.shvants.runninglife.ui.model

class RunMoveModelUi(id: Long,
                     beginTime: Long,
                     title: String,
                     distance: Double,
                     time: Int,
                     elevation: Int?,
                     calories: Int?,
                     avgHr: Int?, imgRes: Int)
    : MoveModelUi(id, beginTime, title, distance, time, elevation, calories, avgHr, imgRes)