package com.shvants.runninglife.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.annotation.UiThread
import com.shvants.runninglife.R
import com.shvants.runninglife.ui.model.BaseModelUi
import com.shvants.runninglife.ui.model.RunMoveModelUi

class FullParameterView
@JvmOverloads
constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : BaseParameterView(context, attrs, defStyleAttr) {

    private lateinit var elevationView: TextView
    private lateinit var caloriesView: TextView
    private lateinit var hrView: TextView

    override fun onViewInflated(context: Context) {
        super.onViewInflated(context)

        elevationView = findViewById(R.id.moveElevationValue)
        caloriesView = findViewById(R.id.moveCaloriesValue)
        hrView = findViewById(R.id.moveHrValue)
    }

    override fun getLayoutResId(): Int {
        return R.layout.full_parameter_view
    }

    @UiThread
    override fun setView(vararg modelUi: BaseModelUi): FullParameterView {
        val runMove = modelUi[0] as RunMoveModelUi

        super.setView(*modelUi)
        elevationView.text = runMove.elevation.toString()
        caloriesView.text = runMove.calories.toString()
        hrView.text = runMove.hr.toString()

        return this
    }
}