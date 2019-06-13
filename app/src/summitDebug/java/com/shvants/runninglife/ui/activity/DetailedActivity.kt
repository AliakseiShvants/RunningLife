package com.shvants.runninglife.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.shvants.runninglife.R
import com.shvants.runninglife.mvp.contract.DetailedActivityContract
import com.shvants.runninglife.mvp.presenter.DetailedActivityPresenter
import kotlinx.android.synthetic.summitDebug.activity_detailed.*

class DetailedActivity : AppCompatActivity(), DetailedActivityContract.View {

    private lateinit var presenter: DetailedActivityContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        presenter = DetailedActivityPresenter(this)
        presenter.attachView(this)

        val athleteView = activityAthlete
        val id = intent.getLongExtra("ACTIVITY_ID", 0L)

        presenter.setAthlete(athleteView)
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}
