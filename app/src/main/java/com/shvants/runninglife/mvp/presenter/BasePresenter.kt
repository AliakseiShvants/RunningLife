package com.shvants.runninglife.mvp.presenter

interface BasePresenter<V> {

    fun attachedView(view: V)

    fun detachView()

    fun onResume()
}