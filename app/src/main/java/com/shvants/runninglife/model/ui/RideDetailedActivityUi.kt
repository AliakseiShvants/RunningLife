package com.shvants.runninglife.model.ui

open class RideDetailedActivityUi(override val id: Long,
                                  override val name: String,
                                  override val movingTime: String,
                                  override val type: String,
                                  override val startDate: String,
                                  override val distance: String,
                                  override val avgSpeed: String,
                                  override val map: String,
                                  override val elevation: Int,
                                  override val calories: Int,
                                  override val avgHR: Int,
                                  val avgWatts: Int) :
        DetailedActivityUi(id, name, movingTime, type, startDate, distance, avgSpeed, map,
                elevation, calories, avgHR) {

    class Builder {
        var id: Long = 0
        var name: String = ""
        var movingTime: String = ""
        var type: String = ""
        var startDate: String = ""
        var distance: String = ""
        var avgSpeed: String = ""
        var map: String = ""
        var elevation: Int = 0
        var calories: Int = 0
        var avgHR: Int = 0
        var avgWatts: Int = 0

        fun id(id: Long) = apply { this.id = id }
        fun name(name: String) = apply { this.name = name }
        fun movingTime(movingTime: String) = apply { this.movingTime = movingTime }
        fun type(type: String) = apply { this.type = type }
        fun startDate(startDate: String) = apply { this.startDate = startDate }
        fun distance(distance: String) = apply { this.distance = distance }
        fun avgSpeed(avgSpeed: String) = apply { this.avgSpeed = avgSpeed }
        fun map(map: String) = apply { this.map = map }
        fun elevation(elevation: Int) = apply { this.elevation = elevation }
        fun calories(calories: Int) = apply { this.calories = calories }
        fun avgHR(avgHR: Int) = apply { this.avgHR = avgHR }
        fun avgWatts(avgWatts: Int) = apply { this.avgWatts = avgWatts }
        fun build() = RideDetailedActivityUi(id, name, movingTime, type, startDate, distance,
                avgSpeed, map, elevation, calories, avgHR, avgWatts)
    }
}