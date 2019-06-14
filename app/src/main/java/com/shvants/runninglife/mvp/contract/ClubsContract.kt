package com.shvants.runninglife.mvp.contract

import com.shvants.runninglife.mvp.presenter.BasePresenter
import com.shvants.runninglife.ui.view.base.BaseView

interface ClubsContract {

    interface View : BaseView

    interface Presenter : BasePresenter<View> {

        fun getClubsImageHeight(): Int
    }
}