package com.shvants.runninglife.mvp.contract

import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.shvants.runninglife.utils.listener.DrawerListener

interface DrawerContract {

    interface Interact {
        fun navigateTo(item: MenuItem, drawerLayout: DrawerLayout, listener: DrawerListener)
    }

    interface View {
        fun navigateTo(fragment: Fragment)

        fun setCheckedItem(item: MenuItem)
    }

    interface Presenter {
        fun navigationItemSelected(item: MenuItem, drawerLayout: DrawerLayout)
    }
}