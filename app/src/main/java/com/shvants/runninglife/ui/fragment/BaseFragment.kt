package com.shvants.runninglife.ui.fragment

import com.shvants.runninglife.ui.view.base.BaseView

abstract class BaseFragment<T> : BaseView {

    abstract fun setItems(list: List<T>)

    abstract fun onItemSelected(position: Int)

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}