package com.shvants.runninglife.ui.adapter

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.imageloader.ImageType
import com.shvants.runninglife.R
import com.shvants.runninglife.model.ui.SummaryActivityUi
import com.shvants.runninglife.model.ui.SummaryAthleteUi
import com.shvants.runninglife.mvp.contract.MyFeedContract
import com.shvants.runninglife.ui.activity.DetailedActivity
import com.shvants.runninglife.ui.view.KudoersView
import com.shvants.runninglife.ui.view.SummaryActivityView
import com.shvants.runninglife.utils.Const.ACTIVITY_ID
import com.shvants.runninglife.utils.Const.KUDOS
import com.shvants.runninglife.utils.ICallback
import com.shvants.runninglife.utils.LongDiffUtil
import kotlinx.android.synthetic.main.layout_summary_activity.view.*


class MyFeedAdapter(private val context: Context,
                    private val presenter: MyFeedContract.Presenter) :
        BaseRecyclerViewAdapter(context) {

    var athlete = presenter.getAthlete() as SummaryAthleteUi
        private set
    var activities = mutableListOf<SummaryActivityUi>()
        private set
    var kudoersProfileUrls = mutableListOf<String>()
        private set

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (getItemViewType(position) == ViewType.ITEM) {

            val view = holder.itemView as SummaryActivityView
            val activity = activities[position]

            val profileView = view.findViewById<ImageView>(R.id.athleteProfile)
            val likeView = view.findViewById<KudoersView>(R.id.kudoers)

            presenter.loadKudoersProfile(likeView, activity.id, ImageType.ROUNDED,
                    object : ICallback<List<String>> {
                        override fun onResult(result: List<String>) {
                            kudoersProfileUrls.addAll(result)
                            presenter.handleKudos(likeView, result)
                        }

                        override fun onError(message: String) {
                            presenter.showErr(message)
                        }
                    }
            )

            presenter.loadAthleteProfile(profileView, athlete.profileMedium, ImageType.ROUNDED)
            view.setAthleteView(athlete)

            presenter.loadActivityMap(view.summaryActivityMap, activity, ImageType.DEFAULT)
            view.setView(activity)
            view.setOnClickListener { showDetails(position) }
        }
    }

    private fun showDetails(position: Int) {
        val activity = activities[position]
        val id = activity.id

        val intent = Intent(context, DetailedActivity::class.java)
        intent.putExtra(ACTIVITY_ID, id)
        intent.putExtra(KUDOS, kudoersProfileUrls.toTypedArray())

        startActivity(context, intent, null)
    }

    override fun getItemCount() = if (isShowLastAsLoading) activities.size + 1 else activities.size

    override fun getItemViewType(position: Int): Int {
        return if (activities.isEmpty() || position > activities.size - 1) ViewType.LOADING else ViewType.ITEM
    }

    fun addActivities(newActivities: List<SummaryActivityUi>) {

        val newList = arrayListOf<SummaryActivityUi>()
        newList.addAll(activities)
        newList.addAll(newActivities)

        updateActivities(newList)
        notifyDataSetChanged()
    }

    private fun updateActivities(list: ArrayList<SummaryActivityUi>) {
        val diffUtil = LongDiffUtil(activities, list)
        val diffResult = DiffUtil.calculateDiff(diffUtil)

        activities.clear()
        activities.addAll(list)

        diffResult.dispatchUpdatesTo(this)
    }

    fun setAthlete(athlete: SummaryAthleteUi?) {
        if (athlete != null) this.athlete = athlete
    }

    fun setActivities(list: ArrayList<SummaryActivityUi>) {
        this.activities = list
    }

    fun setKudoersProfileUrls(list: ArrayList<String>) {
        this.kudoersProfileUrls = list
    }

    override fun getLayoutResId() = R.layout.adapter_summary_item
}