package com.shvants.runninglife.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.imageloader.ImageType
import com.shvants.runninglife.R
import com.shvants.runninglife.model.ui.DetailedClubUi
import com.shvants.runninglife.mvp.contract.DetailedClubContract
import com.shvants.runninglife.mvp.presenter.DetailedClubPresenter
import com.shvants.runninglife.utils.Const.CLUB_ID
import com.shvants.runninglife.utils.Const.ZERO
import com.shvants.runninglife.utils.ICallback
import kotlinx.android.synthetic.summitDebug.activity_detailed_club.*
import kotlinx.android.synthetic.summitDebug.layout_detailed_club.*
import kotlinx.android.synthetic.summitDebug.layout_detailed_club.view.*

class DetailedClubActivity : AppCompatActivity(), DetailedClubContract.View {

    private lateinit var presenter: DetailedClubContract.Presenter
    private val clubCallback = object : ICallback<DetailedClubUi> {

        override fun onResult(result: DetailedClubUi) {
            supportActionBar?.title = resources.getString(R.string.club)
            detailedClub.setView(result)
            loadImages(result, ImageType.DEFAULT, false)
        }

        override fun onError(message: String) {
            showMessage(message)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_club)

        val toolbarView = toolbar as Toolbar
        setSupportActionBar(toolbarView)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        presenter = DetailedClubPresenter(this)
        presenter.attachView(this)

        val clubId = intent.getIntExtra(CLUB_ID, ZERO)
        loadClub(clubId, clubCallback)
    }

    private fun loadImages(club: DetailedClubUi, imageType: ImageType, isGone: Boolean) {
        presenter.loadImage(detailedClub.clubCoverFoto, club.coverPhoto, imageType, isGone)
        presenter.loadImage(detailedClub.clubProfile, club.profileMedium, imageType, isGone)
    }

    private fun loadClub(id: Int, callback: ICallback<DetailedClubUi>) {
        presenter.loadClub(id, callback)
    }

    override fun showMessage(message: String) {
        errorView.visibility = View.VISIBLE
        (errorView as TextView).text = message
    }

    override fun getLayoutResId() = R.layout.activity_detailed_club

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
}
