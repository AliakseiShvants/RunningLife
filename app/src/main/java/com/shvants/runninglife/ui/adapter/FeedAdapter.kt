package com.shvants.runninglife.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.IntDef
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.imageloader.ImageType
import com.shvants.runninglife.R
import com.shvants.runninglife.model.ui.SummaryActivityUi
import com.shvants.runninglife.mvp.contract.FeedContract
import com.shvants.runninglife.ui.activity.DetailedActivity
import com.shvants.runninglife.ui.view.LikeView
import com.shvants.runninglife.ui.view.SummaryActivityView
import com.shvants.runninglife.utils.ActivitiesDiffUtil
import com.shvants.runninglife.utils.ICallback
import kotlinx.android.synthetic.main.layout_summary_item.view.*
import java.lang.Boolean.FALSE


class FeedAdapter(private val context: Context,
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
        val likeView = view.findViewById<LikeView>(R.id.likePanel)

        presenter.loadAthleteProfile(profileView, athlete.profileMedium, ImageType.ROUNDED)
        presenter.loadActivityMap(view.summaryActivityMap, activity, ImageType.DEFAULT)
        presenter.loadKudoersProfile(likeView, activity.id, ImageType.ROUNDED,
                object : ICallback<List<String>> {
                    override fun onResult(result: List<String>) {
                        presenter.handleKudos(likeView, result)
                    }

                    override fun onError(message: String) {
                        presenter.showErr(message)
                    }
                })

        view.setAthleteView(athlete)
        view.setView(activity)

        view.setOnClickListener { showDetails(position) }
    }

    private fun showDetails(position: Int) {
        val activity = activities[position]
        val id = activity.id

        val intent = Intent(context, DetailedActivity::class.java)
        intent.putExtra("ACTIVITY_ID", id)

        startActivity(context, intent, null)
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