package com.shvants.runninglife.data.web.model

import com.shvants.runninglife.data.base.MetaActivity

@Deprecated("replace -Gson")
open class SummaryActivityWeb(
        val id: Int,
        val name: String,
        val movingTime: Int,
        val type: String,
        val startDate: String,
        val distance: Float?,
        val avgSpeed: Float?,
        val map: String?) : MetaActivity {

    private constructor(builder: Builder) : this(builder.id, builder.name, builder.movingTime,
            builder.type, builder.startDate, builder.distance, builder.avgSpeed, builder.map)

    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder(
            var id: Int = 0,
            var name: String = "",
            var movingTime: Int = 0,
            var type: String = "",
            var startDate: String = "",
            val distance: Float? = null,
            var avgSpeed: Float? = null,
            var map: String? = null) {

        fun build() = SummaryActivityWeb(this)
    }
}