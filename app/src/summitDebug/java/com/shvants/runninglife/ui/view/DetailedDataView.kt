package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.UiThread
import com.shvants.runninglife.R
import com.shvants.runninglife.model.ui.DetailedActivityUi
import com.shvants.runninglife.ui.view.base.BaseConstraintView
import com.shvants.runninglife.ui.view.base.BaseCustomView
import kotlinx.android.synthetic.summitDebug.layout_detailed_data.view.*

class DetailedDataView(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : BaseConstraintView(context, attrs, defStyleAttr), BaseCustomView<DetailedActivityUi> {

    override fun getLayoutResId() = R.layout.layout_detailed_data

    @UiThread
    override fun setView(item: DetailedActivityUi) {
        first.setView(arrayOf("Distance", item.distance))
        second.setView(arrayOf("Tempo", item.avgSpeed))
        third.setView(arrayOf("Time", item.movingTime))
        forth.setView(arrayOf("Elevation", item.elevation.toString()))
        fifth.setView(arrayOf("Calories", item.calories.toString()))
        sixth.setView(arrayOf("arg HR", item.avgHR.toString()))
    }
}