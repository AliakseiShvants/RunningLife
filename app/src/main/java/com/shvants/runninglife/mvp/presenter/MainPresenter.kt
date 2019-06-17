package com.shvants.runninglife.mvp.presenter

import android.content.Context
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.imageloader.ImageLoader
import com.example.imageloader.ImageType
import com.shvants.runninglife.R
import com.shvants.runninglife.model.ui.SummaryAthleteUi
import com.shvants.runninglife.mvp.contract.MainContract
import com.shvants.runninglife.repository.Repository
import com.shvants.runninglife.strava.StravaPreferences
import com.shvants.runninglife.ui.activity.MainActivity
import com.shvants.runninglife.ui.fragment.ClubsFragment
import com.shvants.runninglife.ui.fragment.MyFeedFragment

class MainPresenter(private val context: Context) : MainContract.Presenter {

    private var repository = Repository(context)
    private var view: MainContract.View? = null
    private val imageLoader = ImageLoader.getInstance()

    override fun attachView(view: MainContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun loadAthlete() {
        val athlete = repository.getLoggedInAthlete()
        val profileView = (view as MainActivity).getAthleteProfile()

        imageLoader.load(profileView, athlete.profile, ImageType.ROUNDED)
        view?.setAthlete(athlete)
    }

    override fun setAthleteProfile(athlete: SummaryAthleteUi?) {
        if (athlete != null) {
            val profileView = (view as MainActivity).getAthleteProfile()
            imageLoader.load(profileView, athlete.profile, ImageType.ROUNDED)
        }
    }

    override fun getAthlete() = repository.getLoggedInAthlete()

    override fun navigationItemSelected(item: MenuItem, drawerLayout: DrawerLayout) {
        navigateTo(item, drawerLayout)
        view?.setCheckedItem(item)
    }

    private fun navigateTo(item: MenuItem, drawerLayout: DrawerLayout) {
        when (item.itemId) {
            R.id.navMyActivities -> replaceFragment(MyFeedFragment())
            R.id.navItemClubs -> replaceFragment(ClubsFragment())
            R.id.navItemExit -> logout()
        }

        drawerLayout.closeDrawer(GravityCompat.START)
    }

    private fun replaceFragment(fragment: Fragment) {
        view?.replaceFragment(fragment)
    }

    private fun logout() {
        StravaPreferences(context).logout()
        view?.logout()
    }
}