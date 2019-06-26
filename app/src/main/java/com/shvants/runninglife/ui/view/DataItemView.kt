package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.shvants.runninglife.R
import com.shvants.runninglife.ui.view.base.BaseCustomView
import com.shvants.runninglife.utils.Const.HYPHEN
import com.shvants.runninglife.utils.Const.ZERO
import kotlinx.android.synthetic.main.layout_data_item.view.*

class DataItemView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr),
        BaseCustomView<Array<String>> {

    init {
        View.inflate(context, getLayoutResId(), this)
    }

    override fun setView(item: Array<String>) {
        dataItemName.text = item[0]
        dataItemValue.text = if (item[1] != ZERO.toString()) item[1] else HYPHEN
    }

    override fun getLayoutResId() = R.layout.layout_data_item
}