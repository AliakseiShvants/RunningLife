package com.shvants.runninglife.mvp.presenter

import android.content.Context
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.imageloader.ImageLoader
import com.example.imageloader.ImageType
import com.shvants.runninglife.R
import com.shvants.runninglife.mvp.contract.MainContract
import com.shvants.runninglife.repository.Repository
import com.shvants.runninglife.strava.StravaPreferences
import com.shvants.runninglife.ui.activity.MainActivity
import com.shvants.runninglife.ui.fragment.MyFeedFragment

class MainPresenter(private val context: Context) : MainContract.Presenter {

    private var repository: Repository = Repository(context)
    private var view: MainContract.View? = null
    private val imageLoader = ImageLoader.instance

    override fun attachView(view: MainContract.View) {
        this.view = view
    }

    override fun detachView() {
//        repository = null
        view = null
    }


    override fun loadAthlete() {
        val athlete = repository.getLoggedInAthlete()
        val profileView = (view as MainActivity).getAthleteProfile()

        imageLoader.load(profileView, athlete.profile, ImageType.ROUNDED)
        view?.setAthlete(athlete)
    }

    override fun navigationItemSelected(item: MenuItem, drawerLayout: DrawerLayout) {
        navigateTo(item, drawerLayout)
        view?.setCheckedItem(item)
    }

    private fun navigateTo(item: MenuItem, drawerLayout: DrawerLayout) {
        when (item.itemId) {
            R.id.navMyActivities -> replaceFragment(MyFeedFragment.getInstance())
            R.id.navActivitiesFeed -> replaceFragment(MyFeedFragment.getInstance())
            R.id.navItemClubs -> replaceFragment(MyFeedFragment.getInstance())
            R.id.navItemSettings -> replaceFragment(MyFeedFragment.getInstance())
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