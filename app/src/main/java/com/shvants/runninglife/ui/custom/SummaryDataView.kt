package com.shvants.runninglife.ui.custom

import android.content.Context
import android.util.AttributeSet
import com.shvants.runninglife.R
import com.shvants.runninglife.ui.base.BaseCustomView
import com.shvants.runninglife.ui.base.BaseLinearView
import kotlinx.android.synthetic.main.layout_summary_data.view.*

class SummaryDataView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : BaseLinearView(context, attrs, defStyleAttr) {

    private lateinit var firstView: DataItemView
    private lateinit var secondView: DataItemView
    private lateinit var thirdView: DataItemView

    override fun onViewInflated(context: Context) {
        firstView = firstItem
        secondView = secondItem
        thirdView = thirdItem
    }

    override fun getLayoutResId() = R.layout.layout_summary_data

    override fun setView(vararg data: Any?): BaseCustomView {

        firstView.setView("Distance", data[0].toString())
        secondView.setView("Tempo", data[1].toString())
        thirdView.setView("Time", data[2].toString())

        return this
    }
}