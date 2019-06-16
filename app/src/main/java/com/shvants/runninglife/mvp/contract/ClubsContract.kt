package com.shvants.runninglife.mvp.contract

import android.widget.ImageView
import com.shvants.runninglife.model.ui.SummaryClubUi
import com.shvants.runninglife.mvp.presenter.BasePresenter
import com.shvants.runninglife.ui.view.base.BaseView
import com.shvants.runninglife.utils.ICallback

interface ClubsContract {

    interface View : BaseView {

        fun setClubsCount(count: Int)
    }

    interface Presenter : BasePresenter<View> {

        fun getClubsImageHeight(): Int

        fun loadClubs(callback: ICallback<List<SummaryClubUi>>)

        fun loadClubImage(view: ImageView, profileMedium: String)

        fun setClubsCount(count: Int)
    }
}