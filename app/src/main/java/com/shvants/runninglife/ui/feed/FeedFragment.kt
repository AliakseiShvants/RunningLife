package com.shvants.runninglife.ui.feed

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.shvants.runninglife.data.Data
import com.shvants.runninglife.ui.base.BaseFragment
import com.shvants.runninglife.ui.base.IView
import com.shvants.runninglife.ui.model.MoveModelUi
import com.shvants.runninglife.utils.IAdapter
import com.shvants.runninglife.utils.service.IService

class FeedFragment : BaseFragment(), FeedView {


    override fun loadMoreItems(start: Int, end: Int) {
    }

    override fun getLayoutManager(): RecyclerView.LayoutManager {
    }

    override fun getAdapter(): IAdapter {
    }

    override fun isLoading(): Boolean {
    }

    override fun getService(): IService<MoveModelUi> {
    }

    override fun onViewInflated(context: Context) {
    }

    override fun getLayoutResId(): Int {
    }

    override fun setView(vararg data: Data): IView {
    }
}