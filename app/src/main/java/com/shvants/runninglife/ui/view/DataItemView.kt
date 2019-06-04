package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.shvants.runninglife.R
import com.shvants.runninglife.ui.view.base.BaseCustomView
import kotlinx.android.synthetic.main.layout_data_item.view.*

class DataItemView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr),
        BaseCustomView<Array<String>> {

    private lateinit var dataItemNameView: TextView
    private lateinit var dataItemValueView: TextView

    init {
        View.inflate(context, getLayoutResId(), this)
        onViewInflated(context)
    }

    override fun onViewInflated(context: Context) {
        dataItemNameView = dataItemName
        dataItemValueView = dataItemValue
    }

    override fun setView(item: Array<String>) {
        dataItemNameView.text = item[0]
        dataItemValueView.text = item[1]
    }

    override fun getLayoutResId() = R.layout.layout_data_item
}