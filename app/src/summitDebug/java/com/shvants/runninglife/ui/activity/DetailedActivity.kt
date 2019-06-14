package com.shvants.runninglife.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.imageloader.ImageType
import com.shvants.runninglife.R
import com.shvants.runninglife.model.ui.DetailedActivityUi
import com.shvants.runninglife.mvp.contract.DetailedActivityContract
import com.shvants.runninglife.mvp.presenter.DetailedActivityPresenter
import com.shvants.runninglife.ui.view.DetailedActivityView
import com.shvants.runninglife.utils.ICallback
import kotlinx.android.synthetic.summitDebug.activity_detailed_activity.*
import kotlinx.android.synthetic.summitDebug.layout_detailed_item.*
import kotlinx.android.synthetic.summitDebug.layout_detailed_item.view.*

class DetailedActivity : AppCompatActivity(), DetailedActivityContract.View {

    private lateinit var presenter: DetailedActivityContract.Presenter
    private lateinit var detailedActivityView: DetailedActivityView
//    private var activity = DetailedActivityUi


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())

        val toolbarView = toolbar as Toolbar
        setSupportActionBar(toolbarView)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        detailedActivityView = detailedActivity

        presenter = DetailedActivityPresenter(this)
        presenter.attachView(this)

        val id = intent.getLongExtra("ACTIVITY_ID", 0L)

        presenter.loadActivity(id, object : ICallback<DetailedActivityUi> {

            override fun onResult(result: DetailedActivityUi) {
//                activity = result
                supportActionBar?.title = result.type
                detailedActivityView.setView(result)

                presenter.loadMap(detailedActivity.detailedActivityMap,
                        result,
                        ImageType.DEFAULT)
            }

            override fun onError(message: String) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

        val athlete = presenter.getAthlete()
        if (athlete != null) {
            detailedActivity.setAthleteView(athlete)
            presenter.loadImage(detailedActivity.getDetailedAthleteProfile(),
                    athlete.profileMedium,
                    ImageType.ROUNDED)

//            presenter.loadKudoersProfile(detailedLikePanel, athlete.id, ImageType.ROUNDED,
//                    object : ICallback<List<String>> {
//                        override fun onResult(result: List<String>) {
//                            presenter.handleKudos(detailedLikePanel, result)
//                        }
//
//                        override fun onError(message: String) {
////                                presenter.showErr(message)
//                        }
//                    }
//            )
        }

        val kudosProfile = intent.getStringArrayExtra("kudos")
        presenter.handleKudos(detailedLikePanel, kudosProfile)
    }

    override fun showMessage(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutResId() = R.layout.activity_detailed_activity

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.active_bar, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> onBackPressed()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}
