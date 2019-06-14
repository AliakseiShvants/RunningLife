package com.shvants.runninglife.mvp.presenter

import com.shvants.runninglife.mvp.contract.BaseContract

interface BasePresenter<V> : BaseContract.Presenter {

    fun attachView(view: V)

    fun detachView()
}