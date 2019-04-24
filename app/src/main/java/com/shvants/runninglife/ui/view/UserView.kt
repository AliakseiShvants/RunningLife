package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import com.shvants.runninglife.R
import com.shvants.runninglife.ui.model.UserModelUi
import com.shvants.runninglife.utils.Const.NULL
import com.shvants.runninglife.utils.Const.ZERO
import kotlinx.android.synthetic.main.user_view.view.*

class UserView
@JvmOverloads
constructor(
        context: Context,
        attrs: AttributeSet? = NULL,
        defStyleAttr: Int = ZERO) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var avatar: ImageView? = null
    private var fullName: TextView? = null
    private var location: TextView? = null
    private var startTime: TextView? = null

    init {
        View.inflate(context, R.layout.user_view, this)
        init()
    }

    private fun init() {
        avatar = userAvatar
        fullName = userFullName
        location = userLocation
        startTime = beginTime
    }

    fun setUser(user: UserModelUi) {
        val drawable = AppCompatResources.getDrawable(context, user.avatar)

        avatar?.setImageDrawable(drawable)
        fullName?.text = user.fullName
        location?.text = user.location
    }
}
