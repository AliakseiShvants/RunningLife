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

    override fun getLayoutResId() = R.layout.layout_detailed_data

    @UiThread
    override fun setView(item: DetailedActivityUi) {
        firstDataItemView.setView(arrayOf(resources.getString(R.string.distance), item.distance))
        secondDataItemView.setView(arrayOf(resources.getString(R.string.tempo), item.avgSpeed))
        thirdDataItemView.setView(arrayOf(resources.getString(R.string.time), item.movingTime))
        forthDataItemView.setView(arrayOf(resources.getString(R.string.elevation), item.elevation.toString()))
        fifthDataItemView.setView(arrayOf(resources.getString(R.string.calories), item.calories.toString()))
        sixthDataItemView.setView(arrayOf(resources.getString(R.string.hr), item.avgHR.toString()))
    }
}