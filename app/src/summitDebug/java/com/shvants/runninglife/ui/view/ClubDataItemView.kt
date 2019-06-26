package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.shvants.runninglife.R
import com.shvants.runninglife.ui.view.base.BaseCustomView
import kotlinx.android.synthetic.summitDebug.layout_club_data_item.view.*

class ClubDataItemView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr),
        BaseCustomView<Array<String>> {

    init {
        View.inflate(context, getLayoutResId(), this)
    }

    override fun setView(item: Array<String>) {
        clubDataValueTextView.text = item[0]
        clubDataNameTextView.text = item[1]
    }

    override fun getLayoutResId() = R.layout.layout_club_data_item
}