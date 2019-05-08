package com.shvants.runninglife.ui.feed

import android.content.Context
import com.shvants.runninglife.data.base.Repository
import com.shvants.runninglife.data.db.model.SummaryAthleteDb
import com.shvants.runninglife.ui.base.BaseView
import com.shvants.runninglife.ui.model.SummaryActivityUi
import com.shvants.runninglife.ui.model.SummaryAthleteUi
import com.shvants.runninglife.utils.Converter

class FeedPresenter(private val context: Context?,
                    private var fragment: FeedContract.Fragment,
                    private val repository: Repository) : FeedContract.Presenter {

    private lateinit var athlete: SummaryAthleteUi
    private lateinit var activities: ArrayList<SummaryActivityUi>

    override fun onCreate() {
        activities = ArrayList()

        val athleteDb = repository.getLoggedAthlete() as SummaryAthleteDb
        athlete = Converter.convertAthleteFromDbToUi(athleteDb)

        val activitiesDb = repository.getAthleteSummaryActivities(athleteDb.ID.toInt())

        for (activityDb in activitiesDb) {
            activities.add(Converter.convertSummaryActivityFromDbToUi(context, activityDb))
        }
    }

    override fun size() = activities.size

    override fun getLoggedAthlete() = athlete

    override fun getActivity(position: Int) = activities[position]

    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun attachView(view: BaseView) {
        this.fragment = view as FeedContract.Fragment
    }

    override fun loadMoreItems(start: Int, end: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}