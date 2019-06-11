package com.shvants.runninglife.mvp.presenter

import android.content.Context
import android.graphics.Point
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import com.example.imageloader.ImageLoader
import com.example.imageloader.ImageType
import com.shvants.runninglife.model.ui.SummaryActivityUi
import com.shvants.runninglife.model.ui.SummaryAthleteUi
import com.shvants.runninglife.mvp.contract.FeedContract
import com.shvants.runninglife.repository.Repository
import com.shvants.runninglife.strava.StravaHelper
import com.shvants.runninglife.utils.ICallback
import java.util.concurrent.Executors

class FeedPresenter(context: Context) : FeedContract.Presenter {

    private var view: FeedContract.View? = null
    private val repository = Repository(context)
    private val executor = Executors.newCachedThreadPool()
    private val handler = Handler()

    private val imageLoader = ImageLoader.instance
    private val display = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay

    override fun attachView(view: FeedContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

//    override fun size() = activities.size

    override fun getAthlete(): SummaryAthleteUi {

        return repository.getLoggedInAthlete()
    }

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

    override fun loadAthleteProfile(view: ImageView, url: String, imageType: ImageType) {
        imageLoader.load(view, url, imageType)
    }

    override fun loadActivityMap(view: ImageView, activity: SummaryActivityUi, imageType: ImageType) {
        if (activity.map != "") {

            val size = Point()
            display.getSize(size)

            val mapUrlString = StravaHelper.getActivityMapUrl(
                    activity.map,
                    activity.startLatlng,
                    activity.endLatlng,
                    size.x)

            imageLoader.load(view, mapUrlString, imageType)
        } else {
            view.visibility = View.GONE
        }
    }
}