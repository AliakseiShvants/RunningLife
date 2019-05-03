package com.shvants.runninglife.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.UiThread
import com.shvants.runninglife.R
import com.shvants.runninglife.data.base.Repository
import com.shvants.runninglife.ui.base.BaseCustomView
import com.shvants.runninglife.ui.model.RunMoveModelUi
import com.shvants.runninglife.utils.Const.NULL
import com.shvants.runninglife.utils.Const.ZERO

class RunMoveView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = NULL,
        defStyleAttr: Int = ZERO) : BaseCustomView(context, attrs, defStyleAttr) {

    private lateinit var userView: UserView
    private lateinit var baseParameterView: BaseParameterView
    private lateinit var titleView: TextView
    private lateinit var imageView: ImageView
    private lateinit var fullParameterView: FullParameterView

    override fun onViewInflated(context: Context) {

        userView = findViewById(R.id.moveUser)
        baseParameterView = findViewById(R.id.moveBaseParameter)
        titleView = findViewById(R.id.moveTitle)
        imageView = findViewById(R.id.moveImage)
        fullParameterView = findViewById(R.id.moveFullParameter)
    }

    @UiThread
    override fun setView(vararg data: Repository): BaseCustomView {
        userView.setView(data[0], data[1])
        baseParameterView.setView(data[1])
        fullParameterView.setView(data[1])

        val runMove = data[1] as RunMoveModelUi

        titleView.text = runMove.title

        if (runMove.imgRes != ZERO) {
            imageView.setImageResource(runMove.imgRes)
        } else {
            imageView.visibility = View.GONE
        }

        return this
    }

    override fun getLayoutResId(): Int {
        return R.layout.run_move_view
    }

    companion object {
        const val DATE_TIME_PATTERN = "dd MMM yy HH:mm"
        const val DISTANCE_FORMAT = "%.2f"
        const val ONE_MINUTE = 60
        const val ONE_HOUR = 3600
    }
}