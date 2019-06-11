package com.shvants.runninglife.mvp.presenter

interface BasePresenter<V> {

    fun attachView(view: V)

    fun detachView()

}