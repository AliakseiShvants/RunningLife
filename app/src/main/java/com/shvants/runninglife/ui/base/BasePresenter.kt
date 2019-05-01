package com.shvants.runninglife.ui.base

open class BasePresenter<V : IView> : IPresenter<V> {

    private var view: IView? = null

    override fun onAttach(view: IView) {
        this.view = view
    }

    override fun onDetach() {
        this.view = null
    }

    override fun viewIsReady() {
    }

    override fun destroy() {
    }
}