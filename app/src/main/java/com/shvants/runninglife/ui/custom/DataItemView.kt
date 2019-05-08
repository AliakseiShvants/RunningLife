package com.shvants.runninglife.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.shvants.runninglife.R
import com.shvants.runninglife.ui.base.BaseCustomView
import com.shvants.runninglife.ui.base.BaseLinearView
import kotlinx.android.synthetic.main.layout_data_item.view.*

class DataItemView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : BaseLinearView(context, attrs, defStyleAttr) {

    private lateinit var dataItemNameView: TextView
    private lateinit var dataItemValueView: TextView

    override fun onViewInflated(context: Context) {
        dataItemNameView = dataItemName
        dataItemValueView = dataItemValue
    }

    override fun getLayoutResId() = R.layout.layout_data_item

    override fun setView(vararg data: Any?): BaseCustomView {
        dataItemNameView.text = data[0] as String
        dataItemValueView.text = data[1] as String

        return this
    }
}