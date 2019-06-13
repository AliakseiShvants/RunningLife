package com.shvants.runninglife.mvp.presenter

import android.content.Context
import com.shvants.runninglife.mvp.contract.DetailedActivityContract
import com.shvants.runninglife.repository.Repository
import com.shvants.runninglife.ui.view.ActivityAthleteView
import java.lang.ref.WeakReference

class DetailedActivityPresenter(context: Context) : DetailedActivityContract.Presenter {

    private var view: DetailedActivityContract.View? = null
    private var repository: WeakReference<Repository> = WeakReference(Repository(context))

    override fun attachView(view: DetailedActivityContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun setAthlete(view: ActivityAthleteView) {
        val athlete = repository.get()?.getLoggedInAthlete()
        view.setView(athlete)
    }
}