package com.shvants.runninglife.mvp.presenter

import com.shvants.runninglife.mvp.contract.MainContract
import com.shvants.runninglife.repository.Repository

class MainPresenter(private var view: MainContract.View?,
                    private var repository: Repository?) : MainContract.Presenter {

//    override fun loadAthlete() {
//        repository.getAthlete(id, object : ICallback<MetaAthlete> {
//
//            override fun onResult(result: User) {
//                view.setUserInfo(result)
//            }
//
//            override fun onError(message: String) {
//                view.showErrorMsg(message)
//            }
//        })
//    }

    override fun onCreate() {
        val athlete = repository?.getLoggedInAthlete()

        view?.setAthlete(athlete)
    }

    override fun onDestroy() {
        view = null
        repository = null
    }
}