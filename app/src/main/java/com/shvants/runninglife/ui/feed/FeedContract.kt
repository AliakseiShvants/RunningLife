package com.shvants.runninglife.ui.feed

import com.shvants.runninglife.ui.base.BaseFragment
import com.shvants.runninglife.ui.base.BasePresenter

interface FeedContract {

    interface Fragment : BaseFragment

    interface Presenter : BasePresenter {

        fun loadMoreItems(start: Int, end: Int)

        fun size(): Int

        fun getLoggedAthlete(): Any

        fun getActivity(position: Int): Any

    }
}