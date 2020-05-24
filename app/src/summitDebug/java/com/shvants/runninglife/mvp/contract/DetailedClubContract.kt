package com.shvants.runninglife.mvp.contract

import android.widget.ImageView
import com.shvants.runninglife.model.ui.DetailedClubUi
import com.shvants.runninglife.mvp.presenter.BasePresenter
import com.shvants.runninglife.ui.view.base.BaseView
import com.shvants.runninglife.utils.ICallback

interface DetailedClubContract {

    interface View : BaseView

    interface Presenter : BasePresenter<View> {

        fun loadImage(view: ImageView, url: String, isGone: Boolean)

        fun loadClub(id: Int, callback: ICallback<DetailedClubUi>)
    }
}