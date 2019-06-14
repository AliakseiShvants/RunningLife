package com.shvants.runninglife.model.gson.list

import com.shvants.runninglife.model.gson.SummaryClubGson

class ClubListGson(private val clubsList: List<SummaryClubGson>) : BaseList<SummaryClubGson> {

    override fun getList() = clubsList
}