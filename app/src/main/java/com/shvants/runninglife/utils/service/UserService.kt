package com.shvants.runninglife.utils.service

import com.shvants.runninglife.ui.model.UserModelUi
import com.shvants.runninglife.utils.ICallback

class UserService : IService<UserModelUi> {

    private val users: MutableList<UserModelUi> = ArrayList()

    init {
        users.add(UserModelUi(id = 1))
    }

    override fun getEntity(position: Int): UserModelUi = users[position]

    override fun getEntities(): List<UserModelUi> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getEntities(startRange: Int, endRange: Int, callback: ICallback<List<UserModelUi>>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun size(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}