package com.shvants.runninglife.ui.activity

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.shvants.runninglife.R
import com.shvants.runninglife.model.gson.ActivityType
import kotlinx.android.synthetic.summitDebug.layout_detailed_club.*

abstract class BaseActivity : AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutResId(): Int

    fun setSupportActionBar() {
        val toolbarView = toolbar as Toolbar
        setSupportActionBar(toolbarView)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
    }

    fun getActivityTypeAsString(type: String): String {
        return when (type) {
            ActivityType.RUN.title -> resources.getString(R.string.run)
            ActivityType.RIDE.title -> resources.getString(R.string.ride)
            else -> resources.getString(R.string.activity)
        }
    }
}