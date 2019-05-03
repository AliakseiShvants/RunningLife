package com.shvants.runninglife.ui.main

import com.shvants.runninglife.ui.base.BasePresenter
import com.shvants.runninglife.ui.base.BaseView
import com.shvants.runninglife.ui.model.SummaryAthleteUi

interface MainContract {

    interface View : BaseView {

        fun setAthleteToView(athlete: SummaryAthleteUi)
    }

    interface Presenter : BasePresenter
}