package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.shvants.runninglife.R
import com.shvants.runninglife.model.ui.SummaryActivityUi
import com.shvants.runninglife.ui.view.base.BaseCustomView
import kotlinx.android.synthetic.main.layout_summary_data.view.*

class SummaryDataView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr),
        BaseCustomView<SummaryActivityUi> {

    private lateinit var firstView: DataItemView
    private lateinit var secondView: DataItemView
    private lateinit var thirdView: DataItemView

    init {
        View.inflate(context, getLayoutResId(), this)
        onViewInflated(context)
    }

    override fun onViewInflated(context: Context) {
        firstView = firstItem
        secondView = secondItem
        thirdView = thirdItem
    }

    override fun setView(item: SummaryActivityUi) {
        firstView.setView(arrayOf("Distance", item.distance))
        secondView.setView(arrayOf("Tempo", item.avgSpeed))
        thirdView.setView(arrayOf("Time", item.movingTime))
    }

    override fun getLayoutResId() = R.layout.layout_summary_data
}