package com.shvants.runninglife.mvp.presenter

import android.content.Context
import android.os.Handler
import android.view.WindowManager
import android.widget.ImageView
import com.example.imageloader.ImageLoader
import com.example.imageloader.ImageType
import com.shvants.runninglife.model.ui.DetailedClubUi
import com.shvants.runninglife.mvp.contract.DetailedClubContract
import com.shvants.runninglife.repository.Repository
import com.shvants.runninglife.utils.Const
import com.shvants.runninglife.utils.ICallback
import java.lang.ref.WeakReference
import java.util.concurrent.Executors

class DetailedClubPresenter(context: Context) : DetailedClubContract.Presenter {

    private var view: DetailedClubContract.View? = null
    private var repository: WeakReference<Repository> = WeakReference(Repository(context))

    private val imageLoader = ImageLoader.getInstance()
    private val display = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay

    private val executor = Executors.newCachedThreadPool()
    private val handler = Handler()

    override fun attachView(view: DetailedClubContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun loadImage(view: ImageView, url: String, imageType: ImageType, isGone: Boolean) {
        imageLoader.loadAndSet(view, url, imageType, isGone)
    }

    override fun loadClub(id: Int, callback: ICallback<DetailedClubUi>) {
        executor.execute {
            try {
                val club = repository.get()?.getClub(id)

                handler.post { if (club != null) callback.onResult(club) }
            } catch (e: Exception) {
                handler.post {
                    callback.onError("${Const.ERR.CLUB_LOAD}\n${Const.ERR.INTERNET_CONNECTION}")
                }
            }
        }
    }

    //    override fun getAthlete() = repository.get()?.getLoggedInAthlete()
//
//    override fun loadImage(view: ImageView, url: String, imageType: ImageType) {
//        imageLoader.loadAndSet(view, url, imageType)
//    }
//
//    override fun loadActivity(id: Long, callback: ICallback<DetailedActivityUi>) {
//        executor.execute {
//            try {
//                val activity = repository.get()?.getActivity(id)
//
//                handler.post { if (activity != null) callback.onResult(activity) }
//            } catch (e: Exception) {
//                view?.showMessage("${Const.ERR.ACTIVITY_LOAD}\n${Const.ERR.INTERNET_CONNECTION}")
//            }
//        }
//    }
//
//    override fun loadMap(view: ImageView, activity: DetailedActivityUi, imageType: ImageType) {
//        imageLoader.loadAndSet(view, prepareMapUrl(activity), imageType)
//    }
//
//    private fun prepareMapUrl(activity: DetailedActivityUi): String =
//            if (activity.map != EMPTY) {
//                val size = Point()
//                display.getSize(size)
//
//                StravaHelper.getActivityMapUrl(
//                        activity.map,
//                        activity.startLatlng,
//                        activity.endLatlng,
//                        size.x)
//            } else {
//                EMPTY
//            }
//
//    override fun loadKudoersProfile(view: KudoersView, id: Long, imageType: ImageType,
//                                    callback: ICallback<List<String>>) {
//        executor.execute {
//            try {
//                val kudoers = repository.get()?.getKudoers(id)
//                val profileUris = kudoers?.map { it.profileMedium }
//
//                handler.post { if (profileUris != null) callback.onResult(profileUris) }
//            } catch (e: Exception) {
//                handler.post { callback.onError("Check Internet connection and try again") }
//            }
//        }
//    }
//
//    override fun handleKudos(view: KudoersView, arr: Array<String>) {
//        when (arr.size) {
//            0 -> {
//            }
//            1 -> setKudosProfile(hashMapOf(view.firstImage to arr[0]))
//
//            2 -> setKudosProfile(hashMapOf(view.firstImage to arr[0],
//                    view.middleImage to arr[1]))
//
//            else -> setKudosProfile(hashMapOf(view.firstImage to arr[0],
//                    view.middleImage to arr[1],
//                    view.lastImage to arr[2]))
//        }
//    }
//
//    private fun setKudosProfile(map: HashMap<ImageView, String>) {
//        map.forEach { (key, value) -> setKudoProfile(key, value) }
//    }
//
//    private fun setKudoProfile(key: ImageView, value: String) {
//        key.visibility = View.VISIBLE
//        imageLoader.loadAndSet(key, value, ImageType.ROUNDED)
//    }
}