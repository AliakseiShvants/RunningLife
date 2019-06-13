package com.shvants.runninglife.mvp.contract

import com.shvants.runninglife.mvp.presenter.BasePresenter
import com.shvants.runninglife.ui.view.ActivityAthleteView

interface DetailedActivityContract {

    interface View

    interface Presenter : BasePresenter<View> {

        fun setAthlete(view: ActivityAthleteView)
    }
}