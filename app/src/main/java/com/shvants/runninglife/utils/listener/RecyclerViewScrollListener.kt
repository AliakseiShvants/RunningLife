package com.shvants.runninglife.utils.listener

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shvants.runninglife.ui.base.BaseFragment
import com.shvants.runninglife.utils.Const.ZERO
import java.lang.Boolean.FALSE

class RecyclerViewScrollListener(private val fragment: BaseFragment) :
        RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val layoutManager = fragment.getLayoutManager()
        val adapter = fragment.getAdapter()
        val isLoading = fragment.isLoading()
        val service = fragment.getService()

        val totalItemCount = layoutManager.itemCount
        val visibleItemCount = layoutManager.childCount
        val firstVisibleItemPosition =
                (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

        if (totalItemCount >= MAX_VISIBLE_ITEMS) {
            adapter.setShowLastViewAsLoading(FALSE)

            return
        }

        if (!isLoading
                && visibleItemCount + firstVisibleItemPosition >= totalItemCount
                && firstVisibleItemPosition >= ZERO
                && totalItemCount >= PAGE_SIZE) {

            var end = totalItemCount + PAGE_SIZE

            if (end > service.size()) {
                end = service.size()
            }

            service.loadMoreMoves(totalItemCount, end)
        }
    }

    companion object {
        const val PAGE_SIZE = 1
        const val MAX_VISIBLE_ITEMS = 3
    }
}