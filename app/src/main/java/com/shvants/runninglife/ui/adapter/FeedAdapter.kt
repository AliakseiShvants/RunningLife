package com.shvants.runninglife.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IntDef
import androidx.recyclerview.widget.RecyclerView
import com.shvants.runninglife.R
import com.shvants.runninglife.mvp.contract.FeedContract
import com.shvants.runninglife.ui.view.SummaryActivityView
import java.lang.Boolean.FALSE
import java.lang.Boolean.TRUE

class FeedAdapter(context: Context?,
                  private val presenter: FeedContract.Presenter) :
        RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    private var isShowLastAsLoading = FALSE
    private var inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    internal var isLoading = FALSE

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == ViewType.ITEM) {
            ViewHolder(inflater.inflate(R.layout.adapter_summary_item, parent, FALSE))
        } else {
            ViewHolder(inflater.inflate(R.layout.layout_progress, parent, FALSE))
        }
    }

    override fun getItemCount() = presenter.size()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (getItemViewType(position) == ViewType.ITEM) {
            (holder.itemView as SummaryActivityView).setView(presenter.getLoggedAthlete(),
                    presenter.getActivity(position))
        }
    }

    fun setShowLastItemAsLoading(flag: Boolean) {
        isShowLastAsLoading = flag
    }

    fun loadMoreItems(start: Int, end: Int) {
        isLoading = TRUE
        isShowLastAsLoading = TRUE

        presenter.loadMoreItems(start, end)
//        moveService.getEntities(start, end, object : ICallback<List<MoveModelUi>>() {
//
//            fun onResult(result: List<MoveModelUi>) {
//                adapter.addItems(result)
//                isLoading = FALSE
//            }
//        })
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    @IntDef(ViewType.ITEM, ViewType.LOADING)
    @Retention(AnnotationRetention.SOURCE)
    internal annotation class ViewType {
        companion object {
            const val ITEM = 0
            const val LOADING = 1
        }
    }
}