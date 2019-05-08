package com.shvants.runninglife.ui.base

interface BasePresenter {

    fun onCreate()

    fun onDestroy()

    fun attachView(view: BaseView)

}