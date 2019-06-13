package com.shvants.runninglife.ui.fragment

import android.content.Intent
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
import com.shvants.runninglife.ui.activity.DetailedActivity
import com.shvants.runninglife.ui.adapter.FeedAdapter
import com.shvants.runninglife.utils.ICallback
import com.shvants.runninglife.utils.listener.RecyclerItemClickListener
import kotlinx.android.synthetic.summitDebug.fragment_my_activities.*
import java.lang.Boolean.FALSE
import java.lang.Boolean.TRUE
import java.util.concurrent.atomic.AtomicInteger

class FeedFragment private constructor() : Fragment(), FeedContract.View, RecyclerItemClickListener {

    private lateinit var presenter: FeedContract.Presenter
    private lateinit var feedAdapter: FeedAdapter
    private var isLoading = FALSE
    private var page = AtomicInteger(1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(getLayoutResId(), container, FALSE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = FeedPresenter(requireContext().applicationContext)
        presenter.attachView(this)

        feedAdapter = FeedAdapter(requireContext().applicationContext, presenter)

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
        presenter.detachView()
        super.onDestroyView()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {
        errTextView.visibility = View.INVISIBLE
    }

    override fun getLayoutResId() = R.layout.fragment_my_activities

    override fun showMessage(message: String) {
        errTextView.visibility = View.VISIBLE
        errTextView.text = message
    }

    override fun onItemClickListener(position: Int) {
        val activity = feedAdapter.activities[position]
        val id = activity.id

        val intent = Intent(requireContext(), DetailedActivity::class.java)
        intent.putExtra("ACTIVITY_ID", id)

        startActivity(intent)
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

        presenter.loadActivities(page, object : ICallback<List<SummaryActivityUi>> {

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
        fun getInstance() = FeedFragment()
        const val MAX_VISIBLE_ITEMS = 4
    }
}