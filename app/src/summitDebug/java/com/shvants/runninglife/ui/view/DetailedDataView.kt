package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.UiThread
import com.shvants.runninglife.R
import com.shvants.runninglife.model.ui.DetailedActivityUi
import com.shvants.runninglife.ui.view.base.BaseConstraintView
import com.shvants.runninglife.ui.view.base.BaseCustomView
import kotlinx.android.synthetic.summitDebug.layout_detailed_data.view.*

class DetailedDataView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : BaseConstraintView(context, attrs, defStyleAttr),
        BaseCustomView<DetailedActivityUi> {

    private lateinit var firstView: DataItemView
    private lateinit var secondView: DataItemView
    private lateinit var thirdView: DataItemView
    private lateinit var forthView: DataItemView
    private lateinit var fifthView: DataItemView
    private lateinit var sixthView: DataItemView

    override fun getLayoutResId() = R.layout.layout_detailed_data

    override fun onViewInflated(context: Context) {
        firstView = first
        secondView = second
        thirdView = third
        forthView = forth
        fifthView = fifth
        sixthView = sixth
    }

    @UiThread
    override fun setView(item: DetailedActivityUi) {
        firstView.setView(arrayOf("Distance", item.distance))
        secondView.setView(arrayOf("Tempo", item.avgSpeed))
        thirdView.setView(arrayOf("Time", item.movingTime))
        forthView.setView(arrayOf("Elevation", item.elevation.toString()))
        fifthView.setView(arrayOf("Calories", item.calories.toString()))
        sixthView.setView(arrayOf("arg HR", item.avgHR.toString()))
    }
}