package com.shvants.runninglife.model.ui

import com.shvants.runninglife.model.base.MetaActivity

open class SummaryActivityUi(
        open val id: Long,
        open val name: String,
        open val movingTime: String,
        open val type: String,
        open val startDate: String,
        open val distance: String,
        open val avgSpeed: String,
        open val map: String?) : MetaActivity {

    data class Builder(
            var id: Long = 0,
            var name: String = "",
            var movingTime: String = "",
            var type: String = "",
            var startDate: String = "",
            var distance: String = "",
            var avgSpeed: String = "",
            var map: String? = null) {

        fun id(id: Long) = apply { this.id = id }
        fun name(name: String) = apply { this.name = name }
        fun movingTime(movingTime: String) = apply { this.movingTime = movingTime }
        fun type(type: String) = apply { this.type = type }
        fun startDate(startDate: String) = apply { this.startDate = startDate }
        fun distance(distance: String) = apply { this.distance = distance }
        fun avgSpeed(avgSpeed: String) = apply { this.avgSpeed = avgSpeed }
        fun map(map: String) = apply { this.map = map }
        fun build() = SummaryActivityUi(id, name, movingTime, type, startDate, distance, avgSpeed, map)
    }
}