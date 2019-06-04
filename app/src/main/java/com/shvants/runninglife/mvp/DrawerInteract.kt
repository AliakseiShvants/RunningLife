package com.shvants.runninglife.mvp

import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.shvants.runninglife.R
import com.shvants.runninglife.mvp.contract.DrawerContract
import com.shvants.runninglife.ui.fragment.FeedFragment
import com.shvants.runninglife.utils.listener.DrawerListener

class DrawerInteract : DrawerContract.Interact {

    override fun navigateTo(item: MenuItem, drawerLayout: DrawerLayout, listener: DrawerListener) {
        when (item.itemId) {
            R.id.navItemFeed -> listener.replaceFragment(FeedFragment.getInstance())
            R.id.navItemClubs -> listener.replaceFragment(FeedFragment.getInstance())
            R.id.navItemSettings -> listener.replaceFragment(FeedFragment.getInstance())
            R.id.navItemExit -> listener.replaceFragment(FeedFragment.getInstance())
        }

        drawerLayout.closeDrawer(GravityCompat.START)
    }
}