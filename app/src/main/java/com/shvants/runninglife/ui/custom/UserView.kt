package com.shvants.runninglife.ui.custom

import android.content.Context
import android.text.format.DateFormat
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import com.shvants.runninglife.R
import com.shvants.runninglife.ui.model.BaseModelUi
import com.shvants.runninglife.ui.model.RunMoveModelUi
import com.shvants.runninglife.ui.model.UserModelUi
import com.shvants.runninglife.utils.Const.NULL
import com.shvants.runninglife.utils.Const.ZERO
import kotlinx.android.synthetic.main.user_view.view.*

class UserView
@JvmOverloads
constructor(
        context: Context,
        attrs: AttributeSet? = NULL,
        defStyleAttr: Int = ZERO) : ConstraintLayout(context, attrs, defStyleAttr), IView {

    private lateinit var avatarView: ImageView
    private lateinit var fullNameView: TextView
    private lateinit var locationView: TextView
    private lateinit var startTimeView: TextView

    init {
        View.inflate(context, getLayoutResId(), this)
        onViewInflated(context)
    }

    override fun onViewInflated(context: Context) {
        avatarView = userAvatar
        fullNameView = userFullName
        locationView = userLocation
        startTimeView = startTime
    }


    override fun getLayoutResId(): Int {
        return R.layout.user_view
    }

    override fun setView(vararg modelUi: BaseModelUi): IView {
        val user = modelUi[0] as UserModelUi
        val drawable = AppCompatResources.getDrawable(context, user.avatar)

        avatarView.setImageDrawable(drawable)
        fullNameView.text = user.fullName
        locationView.text = user.location

        if (modelUi.size > 1) {
            val move = modelUi[1] as RunMoveModelUi

            avatarView.layoutParams.width = resources.getDimension(R.dimen.avatar_move_size).toInt()
            avatarView.layoutParams.height = resources.getDimension(R.dimen.avatar_move_size).toInt()

            fullNameView.textSize = 12f

            locationView.visibility = View.INVISIBLE

            startTimeView.text = getBeginTimeAsString(move.startTime)
            startTimeView.visibility = View.VISIBLE
        }

        return this
    }

    private fun getBeginTimeAsString(time: Long) = DateFormat.format(DATE_TIME_PATTERN, time)

    companion object {
        const val DATE_TIME_PATTERN = "dd MMM yy HH:mm"
    }
}
