package com.shvants.runninglife.mvp.presenter

import android.content.Context
import com.shvants.runninglife.model.base.MetaAthlete
import com.shvants.runninglife.model.ui.SummaryActivityUi
import com.shvants.runninglife.mvp.contract.FeedContract
import com.shvants.runninglife.repository.Repository

class FeedPresenter(private val context: Context?,
                    private var fragment: FeedContract.Fragment,
                    private val repository: Repository) : FeedContract.Presenter {

    private lateinit var athlete: MetaAthlete
    private lateinit var activities: ArrayList<SummaryActivityUi>

    override fun onCreate() {
        activities = ArrayList()
        athlete = repository.getLoggedInAthlete()

//        val activitiesDb = repository.getAthleteActivities(athleteDb.ID.toInt()) as List<SummaryActivityDb>
//        val activitiesUi = repository.getAthleteActivities(athlete.id)

//        for (activityDb in activitiesDb) {
//            activities.add(Converter.convertActivityFromDbToUi(context, activityDb))
//        }
    }

    override fun size() = activities.size

    override fun getLoggedAthlete() = athlete

    override fun getActivity(position: Int) = activities[position]

    override fun onDestroy() {
        TODO("not implemented") //To change get of created functions use File | Settings | File Templates.
    }


    override fun loadMoreItems(start: Int, end: Int) {
        TODO("not implemented") //To change get of created functions use File | Settings | File Templates.
    }
}