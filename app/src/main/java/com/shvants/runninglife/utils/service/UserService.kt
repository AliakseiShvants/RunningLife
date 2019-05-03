package com.shvants.runninglife.utils.service

import com.shvants.runninglife.ui.model.SummaryAthleteUi
import com.shvants.runninglife.utils.ICallback

class UserService : IService<SummaryAthleteUi> {

    private val users: MutableList<SummaryAthleteUi> = ArrayList()

    init {
        users.add(SummaryAthleteUi(id = 1))
    }

    override fun getEntity(position: Int): SummaryAthleteUi = users[position]

    override fun getEntities(): List<SummaryAthleteUi> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getEntities(startRange: Int, endRange: Int, callback: ICallback<List<SummaryAthleteUi>>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun size(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}