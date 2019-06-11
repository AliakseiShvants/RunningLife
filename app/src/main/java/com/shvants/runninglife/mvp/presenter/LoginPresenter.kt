package com.shvants.runninglife.mvp.presenter

import android.content.Context
import com.shvants.runninglife.mvp.contract.LoginContract
import com.shvants.runninglife.repository.Repository

class LoginPresenter(context: Context) : BasePresenter<LoginContract.View> {

    private var repository: Repository = Repository(context)
    private var view: LoginContract.View? = null

    override fun attachView(view: LoginContract.View) {
        this.view = view
    }

    fun setLoggedInAthlete(): Long {
        return repository.setLoggedInAthlete()
    }

    override fun detachView() {
        view = null
    }
}