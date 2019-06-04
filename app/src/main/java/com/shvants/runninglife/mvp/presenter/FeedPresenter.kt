package com.shvants.runninglife.mvp.presenter

import android.content.Context
import android.os.Handler
import com.shvants.runninglife.model.ui.SummaryActivityUi
import com.shvants.runninglife.model.ui.SummaryAthleteUi
import com.shvants.runninglife.mvp.contract.FeedContract
import com.shvants.runninglife.repository.Repository
import com.shvants.runninglife.utils.ICallback
import java.util.concurrent.Executors

class FeedPresenter(context: Context,
                    private var view: FeedContract.View) : FeedContract.Presenter {

    private val repository = Repository(context)
    private val executor = Executors.newCachedThreadPool()
    private val handler = Handler()

//    private var athlete: SummaryAthleteUi
//    private var activities: List<SummaryActivityUi>
//
//    init {
//        athlete = repository.getLoggedInAthlete()
//        activities = repository.getAthleteActivities()
//    }

    override fun attachedView(view: FeedContract.View) {
        this.view = view
    }

    override fun detachView() {
//        this.view = null
    }

    override fun onResume() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

//    override fun size() = activities.size

    override fun getAthlete(): SummaryAthleteUi {

        return repository.getLoggedInAthlete()
    }

//    override fun getActivity(position: Int): SummaryActivityUi {
//        val activities = repository.getAthleteActivities()
//
//        return activities[position]
//    }

//    override fun loadActivities() {
//        executor.execute {
//            try {
//                val activities = repository.getAthleteActivities()
//
//                handler.post { view.addActivities(activities) }
//            } catch (e: Exception) {
//                handler.post { view.showMessage("Check Internet connection and try again") }
//            }
//
//        }
////        return repository.getAthleteActivities()
//    }

    override fun loadActivities(page: Int, callback: ICallback<List<SummaryActivityUi>>) {
        executor.execute {
            try {
                val activities = repository.getAthleteActivities(page)

                handler.post { callback.onResult(activities) }
            } catch (e: Exception) {
                handler.post { callback.onError("Check Internet connection and try again") }
            }
        }
    }
}