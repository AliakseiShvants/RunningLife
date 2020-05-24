package com.shvants.runninglife.mvp.contract

import android.widget.ImageView
import com.shvants.runninglife.model.ui.SummaryActivityUi
import com.shvants.runninglife.model.ui.SummaryAthleteUi
import com.shvants.runninglife.mvp.presenter.BasePresenter
import com.shvants.runninglife.ui.view.KudoersView
import com.shvants.runninglife.ui.view.base.BaseView
import com.shvants.runninglife.utils.ICallback

interface MyFeedContract {

    interface View : BaseView

    interface Presenter : BasePresenter<View> {

        fun loadActivities(page: Int, callback: ICallback<List<SummaryActivityUi>>)

        fun loadAthleteProfile(view: ImageView, url: String)

        fun loadKudoersProfile(view: KudoersView, id: Long, callback: ICallback<List<String>>)

        fun loadActivityMap(view: ImageView, activity: SummaryActivityUi)

        fun getAthlete(): SummaryAthleteUi?

        fun showErr(message: String)

        fun handleKudos(view: KudoersView, list: List<String>)
    }
}