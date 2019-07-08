package com.shvants.runninglife.mvp.presenter

import android.content.Context
import android.graphics.Point
import android.os.Handler
import android.view.WindowManager
import android.widget.ImageView
import com.example.imageloader.ImageLoader
import com.example.imageloader.ImageType
import com.shvants.runninglife.model.ui.SummaryClubUi
import com.shvants.runninglife.mvp.contract.ClubsContract
import com.shvants.runninglife.repository.Repository
import com.shvants.runninglife.utils.Const
import com.shvants.runninglife.utils.ICallback
import java.util.concurrent.Executors

class ClubsPresenter(context: Context) : ClubsContract.Presenter {

    private var view: ClubsContract.View? = null
    private val repository = Repository(context)

    private val display = (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay
    private val imageLoader = ImageLoader.getInstance()
    private val executor = Executors.newCachedThreadPool()
    private val handler = Handler()

    override fun getClubsImageHeight(): Int {
        val size = Point()
        display.getSize(size)

        return (size.x / Const.FI).toInt()
    }

    override fun attachView(view: ClubsContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun loadClubs(callback: ICallback<List<SummaryClubUi>>) {
        executor.execute {
            try {
                val clubs = repository.getClubs()

                handler.post { callback.onResult(clubs) }
            } catch (e: Exception) {
                callback.onError(Const.ERR.INTERNET_CONNECTION)
            }
        }
    }

    override fun loadClubImage(view: ImageView, profileMedium: String) {
        imageLoader.loadAndSet(view, profileMedium, ImageType.DEFAULT)
    }

    override fun setClubsCount(size: Int) {
        view?.setClubsCount(size)
    }
}