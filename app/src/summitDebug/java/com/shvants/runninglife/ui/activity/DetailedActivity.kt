package com.shvants.runninglife.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.example.imageloader.ImageType
import com.shvants.runninglife.R
import com.shvants.runninglife.model.ui.DetailedActivityUi
import com.shvants.runninglife.mvp.contract.DetailedActivityContract
import com.shvants.runninglife.mvp.presenter.DetailedActivityPresenter
import com.shvants.runninglife.utils.Const.ACTIVITY_ID
import com.shvants.runninglife.utils.Const.EMPTY
import com.shvants.runninglife.utils.Const.KUDOS
import com.shvants.runninglife.utils.Const.ZERO_LONG
import com.shvants.runninglife.utils.ICallback
import kotlinx.android.synthetic.summitDebug.activity_detailed_activity.*
import kotlinx.android.synthetic.summitDebug.layout_detailed_activity.*
import kotlinx.android.synthetic.summitDebug.layout_detailed_activity.view.*

class DetailedActivity : BaseActivity(), DetailedActivityContract.View {

    private lateinit var presenter: DetailedActivityContract.Presenter
    private var activityId = ZERO_LONG
    private val activityCallback = object : ICallback<DetailedActivityUi> {

        override fun onResult(result: DetailedActivityUi) {
            supportActionBar?.title = result.type
            detailedActivity.setView(result)
            loadMap(detailedActivity.detailedActivityMap, result, ImageType.DEFAULT)
        }

        override fun onError(message: String) {
            showMessage(message)
        }
    }

    private val deleteActivityCallback = object : ICallback<Boolean> {

        override fun onResult(result: Boolean) {
            back(result)
        }

        override fun onError(message: String) {
            back(message = message)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())

        setSupportActionBar()

        presenter = DetailedActivityPresenter(this)
        presenter.attachView(this)

        activityId = intent.getLongExtra(ACTIVITY_ID, ZERO_LONG)
        loadActivity(activityId, activityCallback)

        val athlete = presenter.getAthlete()
        if (athlete != null) {
            detailedActivity.setAthleteView(athlete)
            loadImage(detailedActivity.getDetailedAthleteProfile(), athlete.profileMedium, ImageType.ROUNDED)
        }

        val kudosProfile = intent.getStringArrayExtra(KUDOS)
        presenter.handleKudos(detailedLikePanel, kudosProfile)
    }

    private fun loadActivity(id: Long, callback: ICallback<DetailedActivityUi>) {
        presenter.loadActivity(id, callback)
    }

    private fun loadMap(view: ImageView, activityUi: DetailedActivityUi, imageType: ImageType) {
        presenter.loadMap(view, activityUi, imageType)
    }

    private fun loadImage(view: ImageView, url: String, imageType: ImageType) {
        presenter.loadImage(view, url, imageType)
    }

    override fun showMessage(message: String) {
        errorView.visibility = View.VISIBLE
        (errorView as TextView).text = message
    }

    override fun getLayoutResId() = R.layout.activity_detailed_activity

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_bar, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()

                return true
            }
            R.id.share -> {
                share()

                return true
            }
            R.id.delete -> {
                deleteActivity(activityId, deleteActivityCallback)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }

    private fun share() {

    }

    private fun deleteActivity(id: Long, callback: ICallback<Boolean>) {
        presenter.deleteActivity(id, callback)
    }

    private fun back(result: Boolean = false, message: String = EMPTY) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("isDeleted", result)
        intent.putExtra("delete_err", message)

        startActivity(intent)
        finish()
    }
}
