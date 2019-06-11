package com.shvants.runninglife.mvp.contract

import android.widget.ImageView
import com.example.imageloader.ImageType
import com.shvants.runninglife.model.ui.SummaryActivityUi
import com.shvants.runninglife.model.ui.SummaryAthleteUi
import com.shvants.runninglife.mvp.presenter.BasePresenter
import com.shvants.runninglife.ui.view.base.BaseView
import com.shvants.runninglife.utils.ICallback

interface FeedContract {

    interface View : BaseView {

//        fun setAthlete(athlete: SummaryAthleteUi)

//        fun addActivities(activities: List<SummaryActivityUi>)
    }

    interface Presenter : BasePresenter<View> {

        fun loadActivities(page: Int, callback: ICallback<List<SummaryActivityUi>>)

        fun loadAthleteProfile(view: ImageView, url: String, imageType: ImageType)

        fun loadActivityMap(view: ImageView, activity: SummaryActivityUi, imageType: ImageType)

//        fun size(): Int

        fun getAthlete(): SummaryAthleteUi

//        fun getActivity(position: Int): SummaryActivityUi

    }
}