package com.shvants.runninglife.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shvants.runninglife.R
import com.shvants.runninglife.model.ui.SummaryActivityUi
import com.shvants.runninglife.mvp.contract.MyFeedContract
import com.shvants.runninglife.mvp.presenter.MyFeedPresenter
import com.shvants.runninglife.ui.adapter.MyFeedAdapter
import com.shvants.runninglife.utils.ICallback
import kotlinx.android.synthetic.summitDebug.fragment_my_activities.*
import java.lang.Boolean.FALSE
import java.lang.Boolean.TRUE
import java.util.concurrent.atomic.AtomicInteger

class MyFeedFragment private constructor() : BaseFragment(), MyFeedContract.View {

    private lateinit var presenter: MyFeedContract.Presenter
    private lateinit var myFeedAdapter: MyFeedAdapter
    private var isLoading = FALSE
    private var page = AtomicInteger(1)

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        retainInstance = true
//    }

//    override fun onCreateView(inflater: LayoutInflater,
//                              container: ViewGroup?,
//                              savedInstanceState: Bundle?): View? {
//
//        return inflater.inflate(getLayoutResId(), container, FALSE)
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = MyFeedPresenter(requireContext().applicationContext)
        presenter.attachView(this)

        myFeedAdapter = MyFeedAdapter(requireContext().applicationContext, presenter)

        val divider = DividerItemDecoration(context, LinearLayout.VERTICAL)
        divider.setDrawable(resources.getDrawable(R.drawable.divider))

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = myFeedAdapter
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

    override fun getLayoutResId() = R.layout.fragment_my_activities

    override fun showMessage(message: String) {
        errTextView.visibility = View.VISIBLE
        errTextView.text = message
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
        myFeedAdapter.setShowLastItemAsLoading(TRUE)

        presenter.loadActivities(page, object : ICallback<List<SummaryActivityUi>> {

            override fun onResult(result: List<SummaryActivityUi>) {
                myFeedAdapter.addActivities(result)
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