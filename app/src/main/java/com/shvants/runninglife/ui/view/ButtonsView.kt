package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import com.shvants.runninglife.R
import com.shvants.runninglife.ui.view.base.BaseConstraintView
import com.shvants.runninglife.utils.Const
import kotlinx.android.synthetic.main.layout_buttons_panel.view.*

class ButtonsView(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = Const.ZERO
) : BaseConstraintView(context, attrs, defStyleAttr) {

    override fun getLayoutResId() = R.layout.layout_buttons_panel
}