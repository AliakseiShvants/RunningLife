package com.shvants.runninglife.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.IntDef
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.imageloader.ImageType
import com.shvants.runninglife.R
import com.shvants.runninglife.model.ui.SummaryActivityUi
import com.shvants.runninglife.mvp.contract.FeedContract
import com.shvants.runninglife.ui.view.SummaryActivityView
import com.shvants.runninglife.utils.ActivitiesDiffUtil
import java.lang.Boolean.FALSE


class FeedAdapter(context: Context,
                  private val presenter: FeedContract.Presenter) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var isShowLastAsLoading = FALSE
    private var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private var athlete = presenter.getAthlete()
    var activities = mutableListOf<SummaryActivityUi>()
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == ViewType.ITEM) {
            ActivityViewHolder(inflater.inflate(R.layout.adapter_summary_item, parent, FALSE))
        } else {
            ActivityViewHolder(inflater.inflate(R.layout.layout_progress, parent, FALSE))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val view = holder.itemView as SummaryActivityView
        val activity = activities[position]

        val profileView = view.findViewById<ImageView>(R.id.athleteProfile)
        presenter.loadAthleteProfile(profileView, athlete.profileMedium, ImageType.ROUNDED)

        val mapView = view.findViewById<ImageView>(R.id.summaryActivityMap)
        presenter.loadActivityMap(mapView, activity, ImageType.DEFAULT)

        view.setAthleteView(athlete)
        view.setView(activities[position])
    }

    override fun getItemCount() = activities.size

    override fun getItemViewType(position: Int): Int {
        return if (position < activities.size) ViewType.ITEM else ViewType.LOADING
    }

    fun addActivities(newActivities: List<SummaryActivityUi>) {
        val newList = arrayListOf<SummaryActivityUi>()
        newList.addAll(activities)
        newList.addAll(newActivities)

        updateActivities(newList)
        notifyDataSetChanged()
    }

    private fun updateActivities(list: List<SummaryActivityUi>) {
        val diffUtil = ActivitiesDiffUtil(activities, list)
        val diffResult = DiffUtil.calculateDiff(diffUtil)

        activities.clear()
        activities.addAll(list)

        diffResult.dispatchUpdatesTo(this)
    }

    fun setShowLastItemAsLoading(flag: Boolean) {
        if (flag != isShowLastAsLoading) {
            isShowLastAsLoading = flag
            notifyDataSetChanged()
        }
    }

//    fun loadMoreItems(start: Int, end: Int) {
//        isLoading = TRUE
//        isShowLastAsLoading = TRUE
//
//        presenter.loadActivities(start, end, )
//    }


    private inner class ActivityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    @IntDef(ViewType.ITEM, ViewType.LOADING)
    @Retention(AnnotationRetention.SOURCE)
    internal annotation class ViewType {
        companion object {
            const val ITEM = 0
            const val LOADING = 1
        }
    }
}