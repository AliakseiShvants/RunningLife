package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.shvants.runninglife.R
import com.shvants.runninglife.model.ui.SummaryActivityUi
import com.shvants.runninglife.ui.view.base.BaseCustomView
import kotlinx.android.synthetic.main.layout_summary_data.view.*

class
SummaryDataView(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), BaseCustomView<SummaryActivityUi> {

    init {
        View.inflate(context, getLayoutResId(), this)
    }

    override fun setView(item: SummaryActivityUi) {
        firstItem.setView(arrayOf("Distance", item.distance))
        secondItem.setView(arrayOf("Tempo", item.avgSpeed))
        thirdItem.setView(arrayOf("Time", item.movingTime))
    }

    override fun getLayoutResId() = R.layout.layout_summary_data
}