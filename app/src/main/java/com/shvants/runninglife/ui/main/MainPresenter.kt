package com.shvants.runninglife.ui.main

import com.shvants.runninglife.data.base.Repository
import com.shvants.runninglife.data.db.model.SummaryAthleteDb
import com.shvants.runninglife.utils.Converter

class MainPresenter(private val view: MainContract.View,
                    private val repository: Repository) : MainContract.Presenter {

    override fun onCreate() {
        val athleteDb = repository.getLoggedAthlete() as SummaryAthleteDb
        val athleteUi = Converter.convertAthleteFromDbToUi(athleteDb)

        view.setAthleteToView(athleteUi)
    }

    override fun onDestroy() {
    }
}