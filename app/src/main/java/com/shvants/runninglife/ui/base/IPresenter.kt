package com.shvants.runninglife.ui.base

interface IPresenter<V : IView> {

    fun onAttach(view: IView)

    fun onDetach()

    fun setUserAsLoggedOut()
}