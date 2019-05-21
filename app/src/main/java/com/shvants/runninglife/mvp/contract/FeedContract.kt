package com.shvants.runninglife.mvp.contract

import com.shvants.runninglife.mvp.presenter.BasePresenter
import com.shvants.runninglife.ui.fragment.BaseFragment

interface FeedContract {

    interface Fragment : BaseFragment

    interface Presenter : BasePresenter {

        fun loadMoreItems(start: Int, end: Int)

        fun size(): Int

        fun getLoggedAthlete(): Any

        fun getActivity(position: Int): Any

    }
}