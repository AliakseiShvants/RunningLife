package com.shvants.runninglife.mvp.presenter

import android.content.Context
import android.graphics.Point
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.shvants.runninglife.model.ui.SummaryActivityUi
import com.shvants.runninglife.model.ui.SummaryAthleteUi
import com.shvants.runninglife.mvp.contract.MyFeedContract
import com.shvants.runninglife.repository.Repository
import com.shvants.runninglife.strava.StravaHelper
import com.shvants.runninglife.ui.view.KudoersView
import com.shvants.runninglife.utils.Const
import com.shvants.runninglife.utils.Const.EMPTY
import com.shvants.runninglife.utils.ICallback
import kotlinx.android.synthetic.main.kudoers_view.view.*
import java.util.concurrent.Executors

class MyFeedPresenter(context: Context) : MyFeedContract.Presenter {

    private var view: MyFeedContract.View? = null
    private val repository = Repository(context)
    private val executor = Executors.newCachedThreadPool()
    private val handler = Handler()

    private val display = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay

    override fun attachView(view: MyFeedContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun getAthlete(): SummaryAthleteUi {
        return repository.getLoggedInAthlete()
    }

    override fun loadActivities(page: Int, callback: ICallback<List<SummaryActivityUi>>) {
        executor.execute {
            try {
                val activities = repository.getAthleteActivities(page)

                handler.post { callback.onResult(activities) }
            } catch (e: Exception) {
                handler.post {
                    callback.onError("${Const.ERR.ACTIVITIES_LOAD}\n${Const.ERR.INTERNET_CONNECTION}")
                }
            }
        }
    }

    override fun loadKudoersProfile(view: KudoersView, id: Long, callback: ICallback<List<String>>) {
        executor.execute {
            try {
                val kudoers = repository.getKudoers(id)
                val profileUris = kudoers.map { it.profileMedium }

                handler.post { callback.onResult(profileUris) }
            } catch (e: Exception) {
                handler.post {
                    callback.onError("${Const.ERR.KUDOS_LOAD}\n${Const.ERR.INTERNET_CONNECTION}")
                }
            }
        }
    }

    override fun handleKudos(view: KudoersView, list: List<String>) {
        when (list.size) {
            0 -> {
            }
            1 -> setKudosProfile(hashMapOf(view.firstImage to list[0]))

            2 -> setKudosProfile(hashMapOf(view.firstImage to list[0],
                    view.middleImage to list[1]))

            else -> setKudosProfile(hashMapOf(view.firstImage to list[0],
                    view.middleImage to list[1],
                    view.lastImage to list[2]))
        }
    }

    private fun setKudosProfile(map: HashMap<ImageView, String>) {
        map.forEach { (key, value) -> setKudosProfile(key, value) }
    }

    private fun setKudosProfile(key: ImageView, value: String) {
        key.visibility = View.VISIBLE
        loadAthleteProfile(key, value)
    }

    override fun loadAthleteProfile(view: ImageView, url: String) {
        Glide.with(view)
                .load(url)
                .into(view)
    }

    override fun loadActivityMap(view: ImageView, activity: SummaryActivityUi) {
        if (activity.map != EMPTY) {

            val size = Point()
            display.getSize(size)

            val mapUrlString = StravaHelper.getActivityMapUrl(
                    activity.map,
                    activity.startLatlng,
                    activity.endLatlng,
                    size.x)

            Glide.with(view)
                    .load(mapUrlString)
                    .into(view)
        } else {
            view.visibility = View.GONE
        }
    }

    override fun showErr(message: String) {
        view?.showMessage(message)
    }
}