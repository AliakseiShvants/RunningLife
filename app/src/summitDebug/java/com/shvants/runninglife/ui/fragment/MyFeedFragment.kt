package com.shvants.runninglife.ui.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imageloader.ImageType
import com.shvants.runninglife.R
import com.shvants.runninglife.model.ui.SummaryActivityUi
import com.shvants.runninglife.mvp.contract.MyFeedContract
import com.shvants.runninglife.mvp.presenter.MyFeedPresenter
import com.shvants.runninglife.ui.adapter.MyFeedAdapter
import com.shvants.runninglife.utils.ICallback
import kotlinx.android.synthetic.summitDebug.fragment_my_activities.*
import java.util.concurrent.atomic.AtomicInteger

class MyFeedFragment private constructor() : BaseFragment(), MyFeedContract.View {

    private lateinit var presenter: MyFeedContract.Presenter
    private lateinit var myFeedAdapter: MyFeedAdapter
    private var isLoading = false
    private var page = AtomicInteger(1)
    private val feedItemAnimator = object : DefaultItemAnimator() {}

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
//        if (savedInstanceState == null) {
//            loadActivities(page.get())
//        } else {
//            val savedActivities = savedInstanceState.getParcelableArrayList<SummaryActivityUi>(ENTITY_LIST)
//            myFeedAdapter.setActivities(savedActivities)
//        }
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()

        (activity as AppCompatActivity).supportActionBar?.title = resources.getString(R.string.my_activities)
    }

    override fun getLayoutResId() = R.layout.fragment_my_activities

//    override fun getEntityList(): ArrayList<out Parcelable> {
//        return myFeedAdapter.getActivities()
//    }

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

                myFeedAdapter.activities.forEach {
                    var images = arrayListOf<Bitmap>()
                    presenter.loadActivityMap(images, it, ImageType.DEFAULT)

                    myFeedAdapter.activityImages[it.id] = images
                }

                isLoading = false
            }

            override fun onError(message: String) {
                showMessage(message)
            }
        })
    }

    companion object {
        fun getInstance() = MyFeedFragment()
    }
}