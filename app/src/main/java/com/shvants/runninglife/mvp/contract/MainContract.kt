package com.shvants.runninglife.mvp.contract

import com.shvants.runninglife.model.ui.SummaryAthleteUi
import com.shvants.runninglife.mvp.presenter.BasePresenter
import com.shvants.runninglife.ui.view.base.BaseView

interface MainContract {

    interface View : BaseView {

        fun setAthlete(athlete: SummaryAthleteUi?)
    }

    interface Presenter : BasePresenter {

//        fun loadAthlete()
    }
}