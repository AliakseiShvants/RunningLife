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
import com.shvants.runninglife.mvp.contract.MyFeedContract
import com.shvants.runninglife.repository.Repository
import com.shvants.runninglife.strava.StravaHelper
import com.shvants.runninglife.ui.view.LikeView
import com.shvants.runninglife.utils.Const
import com.shvants.runninglife.utils.Const.EMPTY
import com.shvants.runninglife.utils.ICallback
import kotlinx.android.synthetic.main.like_view.view.*
import java.util.concurrent.Executors

class MyFeedPresenter(context: Context) : MyFeedContract.Presenter {

    private var view: MyFeedContract.View? = null
    private val repository = Repository(context)
    private val executor = Executors.newCachedThreadPool()
    private val handler = Handler()

    private val imageLoader = ImageLoader.getInstance()
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
                    callback.onError("${Const.ERR.ACTIVITIES_LOAD_ERR}\n${Const.ERR.INTERNET_CONNECTION}")
                }
            }
        }
    }

    override fun loadKudoersProfile(view: LikeView, id: Long, imageType: ImageType,
                                    callback: ICallback<List<String>>) {
        executor.execute {
            try {
                val kudoers = repository.getKudoers(id)
                val profileUris = kudoers.map { it.profileMedium }

                handler.post { callback.onResult(profileUris) }
            } catch (e: Exception) {
                handler.post {
                    callback.onError("${Const.ERR.KUDOS_LOAD_ERR}\n${Const.ERR.INTERNET_CONNECTION}")
                }
            }
        }
    }

    override fun handleKudos(view: LikeView, list: List<String>) {
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
        map.forEach { (key, value) -> setKudoProfile(key, value) }
    }

    private fun setKudoProfile(key: ImageView, value: String) {
        key.visibility = View.VISIBLE
        loadAthleteProfile(key, value, ImageType.ROUNDED)
    }


    override fun loadAthleteProfile(view: ImageView, url: String, imageType: ImageType) {
        imageLoader.load(view, url, imageType)
    }

    override fun loadActivityMap(view: ImageView, activity: SummaryActivityUi, imageType: ImageType) {
        if (activity.map != EMPTY) {

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

    override fun showErr(message: String) {
        view?.showMessage(message)
    }
}