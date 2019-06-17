package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import com.shvants.runninglife.R
import com.shvants.runninglife.model.ui.DetailedActivityUi
import com.shvants.runninglife.model.ui.SummaryActivityUi
import com.shvants.runninglife.ui.view.base.BaseConstraintView
import com.shvants.runninglife.utils.Const.ZERO
import kotlinx.android.synthetic.main.kudoers_view.view.*

class KudoersView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = ZERO) : /*BaseCustomView<DetailedActivityUi>,*/
        BaseConstraintView(context, attrs, defStyleAttr) {

    private lateinit var firstImageView: ImageView
    private lateinit var middleImageView: ImageView
    private lateinit var lastImageView: ImageView
    private lateinit var countView: TextView

    override fun onViewInflated(context: Context) {
        firstImageView = firstImage
        middleImageView = middleImage
        lastImageView = lastImage
        countView = countText
    }

    override fun getLayoutResId() = R.layout.kudoers_view

    fun setView(item: SummaryActivityUi) {
        val count = item.kudosCount

        countView.text = when (count) {
            0 -> FIRST
            else -> "$count $KUDOS"
        }
    }

    fun seView(activity: DetailedActivityUi) {
        val count = activity.kudosCount

        countView.text = when (count) {
            0 -> FIRST
            else -> "$count $KUDOS"
        }
    }

    companion object {
        private const val KUDOS = "kudos"
        private const val FIRST = "Be the first to give kudos!"
    }
}