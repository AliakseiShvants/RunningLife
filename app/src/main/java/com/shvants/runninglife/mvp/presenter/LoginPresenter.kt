package com.shvants.runninglife.mvp.presenter

import android.content.Context
import com.shvants.runninglife.mvp.contract.LoginContract
import com.shvants.runninglife.repository.Repository

class LoginPresenter(context: Context) : BasePresenter<LoginContract.View> {

    private var repository: Repository = Repository(context)
    private lateinit var view: LoginContract.View

    override fun attachedView(view: LoginContract.View) {
        this.view = view
    }

    override fun onResume() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun setLoggedInAthlete(): Long {
        return repository.setLoggedInAthlete()
    }

    override fun detachView() {
//        view = null
    }
}