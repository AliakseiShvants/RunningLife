package com.shvants.runninglife.ui.model

abstract class MoveModelUi(override var id: Long,
                           var startTime: Long,
                           val title: String,
                           val distance: Double,
                           val time: Int) : BaseModelUi(id)
