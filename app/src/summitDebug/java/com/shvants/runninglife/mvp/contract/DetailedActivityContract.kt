package com.shvants.runninglife.mvp.contract

import android.widget.ImageView
import com.example.imageloader.ImageType
import com.shvants.runninglife.model.ui.DetailedActivityUi
import com.shvants.runninglife.model.ui.SummaryAthleteUi
import com.shvants.runninglife.mvp.presenter.BasePresenter
import com.shvants.runninglife.ui.view.LikeView
import com.shvants.runninglife.ui.view.base.BaseView
import com.shvants.runninglife.utils.ICallback

interface DetailedActivityContract {

    interface View : BaseView

    interface Presenter : BasePresenter<View> {

        fun getAthlete(): SummaryAthleteUi?

        fun loadImage(view: ImageView, url: String, imageType: ImageType)

        fun loadMap(view: ImageView, activity: DetailedActivityUi, imageType: ImageType)

        fun loadActivity(id: Long, callback: ICallback<DetailedActivityUi>)

        fun loadKudoersProfile(view: LikeView, id: Long, imageType: ImageType,
                               callback: ICallback<List<String>>)

        fun handleKudos(view: LikeView, arr: Array<String>)

//        fun setActivity(activity: DetailedActivityUi)
    }
}