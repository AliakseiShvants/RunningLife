package com.shvants.runninglife.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shvants.runninglife.R
import com.shvants.runninglife.model.ui.SummaryActivityUi
import com.shvants.runninglife.mvp.contract.FeedContract
import com.shvants.runninglife.mvp.presenter.FeedPresenter
import com.shvants.runninglife.ui.adapter.FeedAdapter
import com.shvants.runninglife.utils.ICallback
import kotlinx.android.synthetic.standardDebug.fragment_feed.*
import java.lang.Boolean.FALSE
import java.lang.Boolean.TRUE
import java.util.concurrent.atomic.AtomicInteger

class MyFeedFragment private constructor() : BaseFragment(), FeedContract.View/*, RecyclerItemClickListener*/ {

    private lateinit var feedPresenter: FeedContract.Presenter
    private lateinit var feedAdapter: FeedAdapter
    private var isLoading = FALSE
    private var page = AtomicInteger(1)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        feedPresenter = FeedPresenter(requireContext().applicationContext)
        feedPresenter.attachView(this)

        feedAdapter = FeedAdapter(requireContext().applicationContext, feedPresenter)

        val divider = DividerItemDecoration(context, LinearLayout.VERTICAL)
        divider.setDrawable(resources.getDrawable(R.drawable.divider))

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = feedAdapter
            addOnScrollListener(feedScrollListener)
            itemAnimator = feedItemAnimator
            addItemDecoration(divider)
        }

        loadActivities(page.get())
    }

    override fun onDestroyView() {
        feedPresenter.detachView()
        super.onDestroyView()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {
        errTextView.visibility = View.INVISIBLE
    }

    override fun getLayoutResId() = R.layout.fragment_feed

    override fun showMessage(message: String) {
        errTextView.visibility = View.VISIBLE
        errTextView.text = message
    }

    private val feedScrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            hideLoading()

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val totalItemCount = layoutManager.itemCount
            val lastVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition()

            if (!isLoading && totalItemCount <= (lastVisibleItemPosition + MAX_VISIBLE_ITEMS)) {
                loadActivities(page.incrementAndGet())
                isLoading = true
            }
        }
    }

    private val feedItemAnimator = object : DefaultItemAnimator() {}

    fun loadActivities(page: Int) {
//        isLoading = TRUE
        feedAdapter.setShowLastItemAsLoading(TRUE)

        feedPresenter.loadActivities(page, object : ICallback<List<SummaryActivityUi>> {

            override fun onResult(result: List<SummaryActivityUi>) {
                feedAdapter.addActivities(result)
                isLoading = FALSE
            }

            override fun onError(message: String) {
                showMessage(message)
            }
        })
    }

    companion object {
        fun getInstance() = MyFeedFragment()
        const val MAX_VISIBLE_ITEMS = 4
    }
}