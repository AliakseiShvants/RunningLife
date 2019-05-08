package com.shvants.runninglife.ui.model

open class DetailedActivityUi(override val id: Int,
                              override val name: String,
                              override val movingTime: String,
                              override val type: String,
                              override val startDate: String,
                              override val distance: String?,
                              override val avgSpeed: String?,
                              override val map: String?,
                              open val elevation: Int?,
                              open val calories: Int?,
                              open val avgHR: Int?) :
        SummaryActivityUi(id, name, movingTime, type, startDate, distance, avgSpeed, map) {

    private constructor(builder: Builder) : this(builder.id, builder.name, builder.movingTime,
            builder.type, builder.startDate, builder.distance, builder.avgSpeed, builder.map,
            builder.elevation, builder.calories, builder.avgHR)

    class Builder {
        var id: Int = 0
            private set
        var name: String = ""
            private set
        var movingTime: String = ""
            private set
        var type: String = ""
            private set
        var startDate: String = ""
            private set
        var distance: String? = null
            private set
        var avgSpeed: String? = null
            private set
        var map: String? = null
            private set
        var elevation: Int? = null
            private set
        var calories: Int? = null
            private set
        var avgHR: Int? = null
            private set

        fun id(id: Int) = apply { this.id = id }
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
        fun build() = DetailedActivityUi(this)
    }
}