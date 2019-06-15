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
import com.shvants.runninglife.mvp.contract.MyFeedContract
import com.shvants.runninglife.ui.activity.DetailedActivity
import com.shvants.runninglife.ui.view.LikeView
import com.shvants.runninglife.ui.view.SummaryActivityView
import com.shvants.runninglife.utils.Const.ACTIVITY_ID
import com.shvants.runninglife.utils.Const.KUDOS
import com.shvants.runninglife.utils.ICallback
import com.shvants.runninglife.utils.LongDiffUtil
import kotlinx.android.synthetic.main.layout_summary_item.view.*
import java.lang.Boolean.FALSE


class MyFeedAdapter(private val context: Context,
                    private val presenter: MyFeedContract.Presenter) :
        BaseRecyclerViewAdapter(context) {

    private var isShowLastAsLoading = FALSE
    private var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    private val athlete = presenter.getAthlete()
    var activities = mutableListOf<SummaryActivityUi>()
        private set

    var kudoersProfileUrls = mutableListOf<String>()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val view = holder.itemView as SummaryActivityView
        val activity = activities[position]

        val profileView = view.findViewById<ImageView>(R.id.athleteProfile)
        val likeView = view.findViewById<LikeView>(R.id.likePanel)

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

        if (athlete != null) {
            presenter.loadAthleteProfile(profileView, athlete.profileMedium, ImageType.ROUNDED)
            view.setAthleteView(athlete)
        }

        presenter.loadActivityMap(view.summaryActivityMap, activity, ImageType.DEFAULT)
        view.setView(activity)
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
        val diffUtil = LongDiffUtil(activities, list)
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

    override fun getLayoutResId() = R.layout.adapter_summary_item
}