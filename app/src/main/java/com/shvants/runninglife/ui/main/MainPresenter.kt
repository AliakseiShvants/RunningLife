package com.shvants.runninglife.ui.main

import com.shvants.runninglife.data.base.Repository
import com.shvants.runninglife.data.db.model.SummaryAthleteDb
import com.shvants.runninglife.ui.base.BaseView
import com.shvants.runninglife.utils.Converter

class MainPresenter(private var view: MainContract.View,
                    private var repository: Repository) : MainContract.Presenter {

    override fun attachView(view: BaseView) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate() {
        val athleteDb = repository.getLoggedAthlete() as SummaryAthleteDb
        val athleteUi = Converter.convertAthleteFromDbToUi(athleteDb)

        view.setAthleteToView(athleteUi)
    }

    override fun onDestroy() {
    }
}