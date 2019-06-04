package com.shvants.runninglife.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
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
import com.shvants.runninglife.utils.listener.RecyclerItemClickListener
import kotlinx.android.synthetic.standardDebug.fragment_feed.*
import java.lang.Boolean.FALSE
import java.lang.Boolean.TRUE
import java.util.concurrent.atomic.AtomicInteger

class FeedFragment private constructor() : Fragment(), FeedContract.View, RecyclerItemClickListener {

    private lateinit var feedPresenter: FeedContract.Presenter
    private lateinit var feedAdapter: FeedAdapter
    private var isLoading = FALSE
    private var page = AtomicInteger(1)

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(getLayoutResId(), container, FALSE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        feedPresenter = FeedPresenter(requireContext().applicationContext, this)
        feedPresenter.attachedView(this)

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
        if (isLoading) {
            recyclerView.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }
    }

    override fun hideLoading() {
        if (!isLoading) {
            recyclerView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }
    }

    override fun getLayoutResId() = R.layout.fragment_feed

    override fun showMessage(message: String) =
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

    override fun onItemClickListener(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private val feedScrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

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
        showLoading()

        feedAdapter.setShowLastItemAsLoading(TRUE)

        feedPresenter.loadActivities(page, object : ICallback<List<SummaryActivityUi>> {

            override fun onResult(result: List<SummaryActivityUi>) {
                feedAdapter.addActivities(result)
                isLoading = FALSE
                hideLoading()
            }

            override fun onError(message: String) {
                showMessage(message)
            }
        })
    }

    companion object {
        fun getInstance() = FeedFragment()
        const val PAGE_SIZE = 30
        const val MAX_VISIBLE_ITEMS = 4
    }
}