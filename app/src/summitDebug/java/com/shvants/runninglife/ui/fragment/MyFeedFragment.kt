package com.shvants.runninglife.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
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
import java.util.concurrent.atomic.AtomicInteger

class MyFeedFragment : BaseFragment(), MyFeedContract.View {

    private lateinit var presenter: MyFeedContract.Presenter
    private lateinit var myFeedAdapter: MyFeedAdapter
    private var isLoading = false
    private var page = AtomicInteger(1)
    private val feedItemAnimator = object : DefaultItemAnimator() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        retainInstance = true

        if (savedInstanceState != null) {
//            myFeedAdapter.setAthlete(savedInstanceState.getParcelable("athlete"))

//            myFeedAdapter.setKudoersProfileUrls(savedInstanceState.getStringArrayList("kudoersUrl"))
//            page = AtomicInteger(savedInstanceState.getInt("page"))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

//        outState.putParcelable("athlete", myFeedAdapter.athlete)
        outState.putParcelableArrayList("activities", ArrayList(myFeedAdapter.activities))
    }

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

        if (savedInstanceState == null) {
            loadActivities(page.get())
        } else {
            val savedActivities = savedInstanceState.getParcelableArrayList<SummaryActivityUi>("activities")
            myFeedAdapter.setActivities(savedActivities)
        }
    }


    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    override fun getLayoutResId() = R.layout.fragment_my_activities

    override fun showMessage(message: String) {
        errTextView.visibility = View.VISIBLE
        (errTextView as TextView).text = message
    }

    private val feedScrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val lastVisibleItemPosition = layoutManager.findLastCompletelyVisibleItemPosition()

            if (!isLoading
                    && firstVisibleItemPosition > 0
                    && (firstVisibleItemPosition + visibleItemCount) >= totalItemCount) {
                loadActivities(page.incrementAndGet())
            }
        }
    }

    fun loadActivities(page: Int) {
        isLoading = true
        myFeedAdapter.setShowLastItemAsLoading(true)

        presenter.loadActivities(page, object : ICallback<List<SummaryActivityUi>> {

            override fun onResult(result: List<SummaryActivityUi>) {
                myFeedAdapter.addActivities(result)
                isLoading = false
            }

            override fun onError(message: String) {
                showMessage(message)
            }
        })
    }

    companion object {
        fun newInstance() = MyFeedFragment()
        fun getInstance(tag: String): MyFeedFragment {
            val args = Bundle()
            args.putString("tag", tag)

            val newInstance = MyFeedFragment()
            newInstance.arguments = args

            return newInstance
        }
    }
}