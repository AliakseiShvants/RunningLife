package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.shvants.runninglife.R
import com.shvants.runninglife.ui.view.base.BaseCustomView
import kotlinx.android.synthetic.summitDebug.layout_club_data_item.view.*

class ClubDataItemView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr),
        BaseCustomView<Array<String>> {

    private lateinit var clubDataItemNameView: TextView
    private lateinit var clubDataItemValueView: TextView

    init {
        View.inflate(context, getLayoutResId(), this)
        onViewInflated(context)
    }

    override fun onViewInflated(context: Context) {
        clubDataItemNameView = clubDataName
        clubDataItemValueView = clubDataValue
    }

    override fun setView(item: Array<String>) {
        clubDataItemValueView.text = item[0]
        clubDataItemNameView.text = item[1]
    }

    override fun getLayoutResId() = R.layout.layout_club_data_item
}