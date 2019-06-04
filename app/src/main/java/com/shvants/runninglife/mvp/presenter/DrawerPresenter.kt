package com.shvants.runninglife.mvp.presenter

import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.shvants.runninglife.mvp.DrawerInteract
import com.shvants.runninglife.mvp.contract.DrawerContract
import com.shvants.runninglife.utils.listener.DrawerListener

class DrawerPresenter(private val view: DrawerContract.View) : DrawerContract.Presenter,
        DrawerListener {

    private val drawerInteract = DrawerInteract()

    override fun navigationItemSelected(item: MenuItem, drawerLayout: DrawerLayout) {
        drawerInteract.navigateTo(item, drawerLayout, this)
        view.setCheckedItem(item)
    }

    override fun replaceFragment(fragment: Fragment) {
        view.navigateTo(fragment)
    }
}