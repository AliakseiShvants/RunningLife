package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import com.shvants.runninglife.R
import com.shvants.runninglife.model.base.MetaActivity
import com.shvants.runninglife.model.ui.DetailedActivityUi
import com.shvants.runninglife.model.ui.SummaryActivityUi
import com.shvants.runninglife.ui.view.base.BaseConstraintView
import com.shvants.runninglife.utils.Const.ZERO
import kotlinx.android.synthetic.main.kudoers_view.view.*

class KudoersView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = ZERO) :
        BaseConstraintView(context, attrs, defStyleAttr) {

    override fun getLayoutResId() = R.layout.kudoers_view

    fun setView(activity: MetaActivity) {
        when (activity) {
            is SummaryActivityUi -> getKudosString(activity.kudosCount)
            is DetailedActivityUi -> getKudosString(activity.kudosCount)
        }
    }

    private fun getKudosString(kudosCount: Int) {
        countTextView.text = when (kudosCount) {
            0 -> resources.getString(R.string.be_the_first_to_give_kudos)
            1 -> "$kudosCount ${resources.getString(R.string.kudo)}"
            else -> "$kudosCount ${resources.getString(R.string.kudos)}"
        }
    }
}