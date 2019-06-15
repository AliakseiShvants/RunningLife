package com.shvants.runninglife.mvp.presenter

import android.content.Context
import android.graphics.Point
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import com.example.imageloader.ImageLoader
import com.example.imageloader.ImageType
import com.shvants.runninglife.model.ui.DetailedActivityUi
import com.shvants.runninglife.mvp.contract.DetailedActivityContract
import com.shvants.runninglife.repository.Repository
import com.shvants.runninglife.strava.StravaHelper
import com.shvants.runninglife.ui.view.LikeView
import com.shvants.runninglife.utils.Const
import com.shvants.runninglife.utils.Const.EMPTY
import com.shvants.runninglife.utils.ICallback
import kotlinx.android.synthetic.main.like_view.view.*
import java.lang.ref.WeakReference
import java.util.concurrent.Executors

class DetailedActivityPresenter(context: Context) : DetailedActivityContract.Presenter {

    private var view: DetailedActivityContract.View? = null
    private var repository: WeakReference<Repository> = WeakReference(Repository(context))

    private val imageLoader = ImageLoader.getInstance()
    private val display = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay

    private val executor = Executors.newCachedThreadPool()
    private val handler = Handler()

    override fun attachView(view: DetailedActivityContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun getAthlete() = repository.get()?.getLoggedInAthlete()

    override fun loadImage(view: ImageView, url: String, imageType: ImageType) {
        imageLoader.load(view, url, imageType)
    }

    override fun loadActivity(id: Long, callback: ICallback<DetailedActivityUi>) {
        executor.execute {
            try {
                val activity = repository.get()?.getActivity(id)

                handler.post { if (activity != null) callback.onResult(activity) }
            } catch (e: Exception) {
                handler.post {
                    callback.onError("${Const.ERR.ACTIVITY_LOAD_ERR}\n${Const.ERR.INTERNET_CONNECTION}")
                }
            }
        }
    }

    override fun loadMap(view: ImageView, activity: DetailedActivityUi, imageType: ImageType) {
        imageLoader.load(view, prepareMapUrl(activity), imageType)
    }

    private fun prepareMapUrl(activity: DetailedActivityUi): String =
            if (activity.map != EMPTY) {
                val size = Point()
                display.getSize(size)

                StravaHelper.getActivityMapUrl(
                        activity.map,
                        activity.startLatlng,
                        activity.endLatlng,
                        size.x)
            } else {
                EMPTY
            }

    override fun loadKudoersProfile(view: LikeView, id: Long, imageType: ImageType,
                                    callback: ICallback<List<String>>) {
        executor.execute {
            try {
                val kudoers = repository.get()?.getKudoers(id)
                val profileUris = kudoers?.map { it.profileMedium }

                handler.post { if (profileUris != null) callback.onResult(profileUris) }
            } catch (e: Exception) {
                handler.post { callback.onError("Check Internet connection and try again") }
            }
        }
    }

    override fun handleKudos(view: LikeView, arr: Array<String>) {
        when (arr.size) {
            0 -> {
            }
            1 -> setKudosProfile(hashMapOf(view.firstImage to arr[0]))

            2 -> setKudosProfile(hashMapOf(view.firstImage to arr[0],
                    view.middleImage to arr[1]))

            else -> setKudosProfile(hashMapOf(view.firstImage to arr[0],
                    view.middleImage to arr[1],
                    view.lastImage to arr[2]))
        }
    }

    private fun setKudosProfile(map: HashMap<ImageView, String>) {
        map.forEach { (key, value) -> setKudoProfile(key, value) }
    }

    private fun setKudoProfile(key: ImageView, value: String) {
        key.visibility = View.VISIBLE
        imageLoader.load(key, value, ImageType.ROUNDED)
    }
}