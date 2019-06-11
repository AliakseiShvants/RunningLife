package com.shvants.runninglife.mvp.contract

import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.shvants.runninglife.model.ui.SummaryAthleteUi
import com.shvants.runninglife.mvp.presenter.BasePresenter

interface MainContract {

    interface View {

        fun replaceFragment(fragment: Fragment)

        fun setCheckedItem(item: MenuItem)

//        fun setActionBarTitle(title: String)

        fun setAthlete(athlete: SummaryAthleteUi)

        fun logout()
    }

    interface Presenter : BasePresenter<View> {

        fun loadAthlete()

        fun navigationItemSelected(item: MenuItem, drawerLayout: DrawerLayout)
    }
}