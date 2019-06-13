package com.shvants.runninglife.model.ui

open class DetailedActivityUi(override var id: Long = 0L,
                              override var name: String = "",
                              override var movingTime: String = "",
                              override var type: String = "",
                              override var startDate: String = "",
                              override var distance: String = "",
                              override var avgSpeed: String = "",
                              override var kudosCount: Int = 0,
                              override var startLatlng: FloatArray = FloatArray(0),
                              override var endLatlng: FloatArray = FloatArray(0),
                              override var map: String = "",
                              open var elevation: Int = 0,
                              open var calories: Int = 0,
                              open var avgHR: Int = 0) :
        SummaryActivityUi(id, name, movingTime, type, startDate, distance, avgSpeed, kudosCount,
                startLatlng, endLatlng, map)