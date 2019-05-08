package com.shvants.runninglife.ui.model

import com.shvants.runninglife.data.base.MetaActivity

open class SummaryActivityUi(
        open val id: Int,
        open val name: String,
        open val movingTime: String,
        open val type: String,
        open val startDate: String,
        open val distance: String?,
        open val avgSpeed: String?,
        open val map: String?) : MetaActivity {

    private constructor(builder: Builder) : this(builder.id, builder.name, builder.movingTime,
            builder.type, builder.startDate, builder.distance, builder.avgSpeed, builder.map)

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

        fun id(id: Int) = apply { this.id = id }
        fun name(name: String) = apply { this.name = name }
        fun movingTime(movingTime: String) = apply { this.movingTime = movingTime }
        fun type(type: String) = apply { this.type = type }
        fun startDate(startDate: String) = apply { this.startDate = startDate }
        fun distance(distance: String) = apply { this.distance = distance }
        fun avgSpeed(avgSpeed: String) = apply { this.avgSpeed = avgSpeed }
        fun map(map: String) = apply { this.map = map }
        fun build() = SummaryActivityUi(this)
    }
}