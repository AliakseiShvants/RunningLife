package com.shvants.runninglife.mvp.presenter

import android.content.Context
import android.graphics.Point
import android.view.WindowManager
import com.shvants.runninglife.mvp.contract.ClubsContract
import com.shvants.runninglife.utils.Const

class ClubsPresenter(context: Context) : ClubsContract.Presenter {

    private var view: ClubsContract.View? = null

    private val display = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay

    override fun getClubsImageHeight(): Int {
        val size = Point()
        display.getSize(size)

        return (size.x / Const.FI).toInt()
    }

    override fun attachView(view: ClubsContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }
}