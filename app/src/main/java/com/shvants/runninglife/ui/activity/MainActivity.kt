package com.shvants.runninglife.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.imageloader.ImageLoader
import com.google.android.material.navigation.NavigationView
import com.shvants.runninglife.R
import com.shvants.runninglife.model.ui.SummaryAthleteUi
import com.shvants.runninglife.mvp.contract.MainContract
import com.shvants.runninglife.mvp.presenter.MainPresenter
import com.shvants.runninglife.ui.view.NavAthleteView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_athlete_view.view.*

class MainActivity : AppCompatActivity(),
        MainContract.View,
        NavigationView.OnNavigationItemSelectedListener {

    private lateinit var toolbar: Toolbar
    private lateinit var navigationView: NavigationView
    private lateinit var navAthleteView: NavAthleteView
    private var presenter: MainContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)

        presenter = MainPresenter(this)

        navigationView = findViewById(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener(this)

        navAthleteView = navigationView.getHeaderView(0).findViewById(R.id.navAthleteView)

        presenter?.attachView(this)
        presenter?.loadAthlete()

        navigationView.menu.performIdentifierAction(R.id.navActivitiesFeed, 0)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.active_bar, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        presenter?.detachView()
        super.onDestroy()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        presenter?.navigationItemSelected(item, drawerLayout)
        setActionBarTitle(item.title.toString())

        return true
    }

    override fun setCheckedItem(item: MenuItem) {
        navigationView.setCheckedItem(item)
    }

    override fun replaceFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_fragment_container, fragment)
                .commit()
    }

    private fun setActionBarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun setAthlete(athlete: SummaryAthleteUi) {
        navAthleteView.setView(athlete)
    }

    fun getAthleteProfile() = navAthleteView.navAthleteProfile

    override fun logout() {
        ImageLoader.instance.clearCache()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}