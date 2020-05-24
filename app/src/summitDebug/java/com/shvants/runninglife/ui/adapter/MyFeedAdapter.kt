package com.shvants.runninglife.ui.adapter

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shvants.runninglife.R
import com.shvants.runninglife.model.ui.SummaryActivityUi
import com.shvants.runninglife.model.ui.SummaryAthleteUi
import com.shvants.runninglife.mvp.contract.MyFeedContract
import com.shvants.runninglife.ui.activity.DetailedActivity
import com.shvants.runninglife.ui.view.KudoersView
import com.shvants.runninglife.ui.view.SummaryActivityView
import com.shvants.runninglife.utils.Const.ACTIVITY_ID
import com.shvants.runninglife.utils.Const.KUDOS
import com.shvants.runninglife.utils.LongDiffUtil

class MyFeedAdapter(
        private val context: Context,
        private val presenter: MyFeedContract.Presenter
) :
        BaseRecyclerViewAdapter(context) {

    var athlete = presenter.getAthlete() as SummaryAthleteUi
        private set
    var activities = mutableListOf<SummaryActivityUi>()
        private set

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (getItemViewType(position) == ViewType.ITEM) {

            val view = holder.itemView as SummaryActivityView
            val activity = activities[position]

            val profileView = view.findViewById<ImageView>(R.id.athleteProfile)
            val kudoersView = view.findViewById<KudoersView>(R.id.kudoers)

//            if (activity.kudos.isEmpty()) {
//                presenter.loadKudoersProfile(kudoersView, activity.id, ImageType.ROUNDED,
//                        object : ICallback<List<String>> {
//                            override fun onResult(result: List<String>) {
//                                activity.kudos.addAll(result)
//
//                                notifyItemChanged(position)
//                            }
//
//                            override fun onError(message: String) {
//                                presenter.showErr(message)
//                            }
//                        }
//                )
//            }

            presenter.handleKudos(kudoersView, activity.kudos)

            presenter.loadAthleteProfile(profileView, athlete.profileMedium)
            view.setAthleteView(athlete)

//            presenter.loadActivityMap(view.summaryActivityMap, activity, ImageType.DEFAULT)
            view.setView(activity)

            view.setOnClickListener { showDetails(position) }
        }
    }

    private fun showDetails(position: Int) {
        val activity = activities[position]
        val id = activity.id

        val intent = Intent(context, DetailedActivity::class.java)
        intent.putExtra(ACTIVITY_ID, id)
        intent.putExtra(KUDOS, activity.kudos.toTypedArray())

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

    fun getActivities() = ArrayList<SummaryActivityUi>(activities)

    fun setActivities(list: ArrayList<SummaryActivityUi>) {
        this.activities = list
    }

    override fun getLayoutResId() = R.layout.adapter_summary_item
}