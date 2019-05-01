package com.shvants.runninglife.ui.base

interface IPresenter<V : IView> {

    fun onAttach(view: IView)

    fun viewIsReady()

    fun onDetach()

    fun destroy()

//    fun setUserAsLoggedOut()
}