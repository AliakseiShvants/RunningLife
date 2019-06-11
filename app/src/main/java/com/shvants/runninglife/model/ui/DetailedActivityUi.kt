package com.shvants.runninglife.model.ui

open class DetailedActivityUi(override var id: Long,
                              override var name: String,
                              override var movingTime: String,
                              override var type: String,
                              override var startDate: String,
                              override var distance: String,
                              override var avgSpeed: String,
                              override var startLatlng: FloatArray,
                              override var endLatlng: FloatArray,
                              override var map: String,
                              open var elevation: Int,
                              open var calories: Int,
                              open var avgHR: Int) :
        SummaryActivityUi(id, name, movingTime, type, startDate, distance, avgSpeed, startLatlng,
                endLatlng, map)