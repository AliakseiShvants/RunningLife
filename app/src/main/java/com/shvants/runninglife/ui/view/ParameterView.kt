package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.shvants.runninglife.R
import kotlinx.android.synthetic.main.parameter_view.view.*

class ParameterView
@JvmOverloads
constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var titleView: TextView
    private lateinit var valueView: TextView

    init {
        View.inflate(context, R.layout.parameter_view, this)
        init()
    }

    private fun init() {
        titleView = parameterTitle
        valueView = parameterValue
    }

    fun setParameter(title: String, value: String){
        titleView.text = title
        valueView.text = value
    }
}