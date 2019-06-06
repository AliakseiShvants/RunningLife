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
import com.shvants.runninglife.ui.activity.MainActivity
import com.shvants.runninglife.ui.fragment.FeedFragment

class MainPresenter(context: Context) : MainContract.Presenter {

    private var repository: Repository = Repository(context)
    private lateinit var view: MainContract.View
    private val imageLoader = ImageLoader.instance

    override fun attachedView(view: MainContract.View) {
        this.view = view
    }

    override fun detachView() {
//        repository = null
//        view = null
    }

    override fun onResume() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun loadAthlete() {
        val athlete = repository.getLoggedInAthlete()
        val profileView = (view as MainActivity).getAthleteProfile()

        imageLoader.load(profileView, athlete.profile, ImageType.ROUNDED)
        view.setAthlete(athlete)
    }

    override fun navigationItemSelected(item: MenuItem, drawerLayout: DrawerLayout) {
        navigateTo(item, drawerLayout)
        view.setCheckedItem(item)
    }

    private fun navigateTo(item: MenuItem, drawerLayout: DrawerLayout) {
        when (item.itemId) {
            R.id.navItemFeed -> replaceFragment(FeedFragment.getInstance())
            R.id.navItemClubs -> replaceFragment(FeedFragment.getInstance())
            R.id.navItemSettings -> replaceFragment(FeedFragment.getInstance())
            R.id.navItemExit -> replaceFragment(FeedFragment.getInstance())
        }

        drawerLayout.closeDrawer(GravityCompat.START)
    }

    private fun replaceFragment(fragment: Fragment) {
        view.replaceFragment(fragment)
    }
}