package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import com.shvants.runninglife.R
import com.shvants.runninglife.model.ui.SummaryActivityUi
import com.shvants.runninglife.ui.view.base.BaseConstraintView
import com.shvants.runninglife.ui.view.base.BaseCustomView
import com.shvants.runninglife.utils.Const
import kotlinx.android.synthetic.main.like_view.view.*

class LikeView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = Const.NULL,
        defStyleAttr: Int = Const.ZERO) : BaseCustomView<SummaryActivityUi>,
        BaseConstraintView(context, attrs, defStyleAttr) {

    private lateinit var firstImageView: ImageView
    private lateinit var middleImageView: ImageView
    private lateinit var lastImageView: ImageView
    private lateinit var countView: TextView

    override fun setView(item: SummaryActivityUi) {
        val count = item.kudosCount

        countView.text = when (count) {
            0 -> FIRST
            else -> "$count $KUDOS"
        }
    }

    override fun getLayoutResId() = R.layout.like_view

    override fun onViewInflated(context: Context) {
        firstImageView = firstImage
        middleImageView = middleImage
        lastImageView = lastImage
        countView = countText
    }

    companion object {
        private const val KUDOS = "kudos"
        private const val FIRST = "Be the first to give kudos!"
    }
}