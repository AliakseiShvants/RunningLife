package com.shvants.runninglife.model.ui

class DetailedActivityUi(override var id: Long,
                         override var name: String,
                         override var movingTime: String,
                         override var type: String,
                         override var startDate: String,
                         override var distance: String,
                         override var avgSpeed: String,
                         override var kudosCount: Int,
                         override var kudos: ArrayList<String> = ArrayList(),
                         override var commentCount: Int,
                         override var startLatlng: FloatArray,
                         override var endLatlng: FloatArray,
                         override var map: String,
                         var elevation: Int,
                         var calories: Int,
                         var avgHR: Int)
    : SummaryActivityUi(id, name, movingTime, type, startDate, distance, avgSpeed, kudosCount, kudos,
        commentCount, startLatlng, endLatlng, map)