package com.shvants.runninglife.mvp.presenter

import android.content.Context
import com.shvants.runninglife.mvp.contract.MainContract
import com.shvants.runninglife.repository.Repository

class MainPresenter(private var context: Context?,
                    private var view: MainContract.View?) : MainContract.Presenter {

    private lateinit var repository: Repository

    override fun onCreate() {
        repository = Repository(context)
    }

    override fun loadAthlete() {
        val athlete = repository.getLoggedInAthlete()
        view?.setAthlete(athlete)
    }

    override fun onDestroy() {
        context = null
        view = null
    }
}