package com.shvants.runninglife.model.ui

import com.shvants.runninglife.model.base.MetaActivity

open class SummaryActivityUi(
        open var id: Long,
        open var name: String,
        open var movingTime: String,
        open var type: String,
        open var startDate: String,
        open var distance: String,
        open var avgSpeed: String,
        open var startLatlng: FloatArray,
        open var endLatlng: FloatArray,
        open var map: String) : MetaActivity