package com.shvants.runninglife.ui.main

import com.shvants.runninglife.data.base.MetaAthlete
import com.shvants.runninglife.ui.base.BasePresenter
import com.shvants.runninglife.ui.base.BaseView

interface MainContract {

    interface View : BaseView {

        fun setAthleteToView(athlete: MetaAthlete)
    }

    interface Presenter : BasePresenter
}