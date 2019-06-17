package com.shvants.runninglife.ui.activity

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.shvants.runninglife.R
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
}