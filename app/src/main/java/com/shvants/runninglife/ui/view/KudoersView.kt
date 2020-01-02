package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import com.shvants.runninglife.R
import com.shvants.runninglife.model.ui.DetailedActivityUi
import com.shvants.runninglife.model.ui.SummaryActivityUi
import com.shvants.runninglife.ui.view.base.BaseConstraintView
import com.shvants.runninglife.utils.Const.ZERO
import kotlinx.android.synthetic.main.kudoers_view.view.*

private const val KUDOS = "kudos"
private const val FIRST = "Be the first to give kudos!"

class KudoersView(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = ZERO
) : BaseConstraintView(context, attrs, defStyleAttr) {

    override fun getLayoutResId() = R.layout.kudoers_view

    fun setView(item: SummaryActivityUi) {
        val count = item.kudosCount

        countText.text = when (count) {
            0 -> FIRST
            else -> "$count $KUDOS"
        }
    }

    fun seView(activity: DetailedActivityUi) {
        val count = activity.kudosCount

        countText.text = when (count) {
            0 -> FIRST
            else -> "$count $KUDOS"
        }
    }
}