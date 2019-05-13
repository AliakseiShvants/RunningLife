package com.shvants.runninglife.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shvants.runninglife.R
import com.shvants.runninglife.data.web.model.WebRepository
import com.shvants.runninglife.ui.main.MainActivity
import com.shvants.runninglife.utils.Const.FeedFragment.TITLE
import com.shvants.runninglife.utils.Const.ZERO
import java.lang.Boolean.FALSE

class FeedFragment private constructor() : Fragment(), FeedContract.Fragment {

    private lateinit var presenter: FeedContract.Presenter
    private lateinit var adapter: FeedAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (activity as MainActivity).setActionBarTitle(TITLE)

        presenter = FeedPresenter(context, this, WebRepository.instance)
        presenter.onCreate()

        layoutManager = LinearLayoutManager(activity)
        adapter = FeedAdapter(context, presenter)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val feedView = inflater.inflate(getLayoutResId(), container, FALSE)

        recyclerView = feedView.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (totalItemCount >= MAX_VISIBLE_ITEMS) {
                    adapter.setShowLastItemAsLoading(FALSE)

                    return
                }

                if (!adapter.isLoading
                        && visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= ZERO
                        && totalItemCount >= PAGE_SIZE) {

                    var end = totalItemCount + PAGE_SIZE

                    if (end > adapter.itemCount) {
                        end = adapter.itemCount
                    }

                    adapter.loadMoreItems(totalItemCount, end)
                }
            }
        })

        recyclerView.itemAnimator = object : DefaultItemAnimator() {
        }

        recyclerView.addItemDecoration(DividerItemDecoration(context!!,
                DividerItemDecoration.VERTICAL))

        presenter.attachView(this)

        return feedView
    }

    override fun getLayoutResId() = R.layout.fragment_feed

    companion object {
        val instance = FeedFragment()
        const val PAGE_SIZE = 2
        const val MAX_VISIBLE_ITEMS = 4
    }
}