package com.shvants.runninglife.ui.adapter

import android.content.Context
import android.content.Intent
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shvants.runninglife.R
import com.shvants.runninglife.model.ui.SummaryClubUi
import com.shvants.runninglife.mvp.contract.ClubsContract
import com.shvants.runninglife.ui.activity.DetailedClubActivity
import com.shvants.runninglife.utils.Const.CLUB_ID
import com.shvants.runninglife.utils.IntDiffUtil

class ClubsAdapter(private val context: Context,
                   private val presenter: ClubsContract.Presenter) : BaseRecyclerViewAdapter(context) {

    private var clubs = mutableListOf<SummaryClubUi>()

    override fun getItemCount() = clubs.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val clubView = holder.itemView
        val club = clubs[position]

        presenter.setClubsCount(itemCount)
        presenter.loadClubImage(clubView.findViewById(R.id.clubImage), club.profileMedium)
        clubView.findViewById<TextView>(R.id.clubName).text = club.name
        clubView.setOnClickListener { showItemDetails(position) }
    }

    private fun showItemDetails(position: Int) {
        val clubId = clubs[position].id
        val intent = Intent(context, DetailedClubActivity::class.java)
        intent.putExtra(CLUB_ID, clubId)

        startActivity(context, intent, null)
    }

    override fun getItemViewType(position: Int): Int {
        return if (clubs.isEmpty() || position > clubs.size - 1) ViewType.LOADING else ViewType.ITEM
    }

    override fun getLayoutResId() = R.layout.layout_club_item

    fun addClubs(newClubs: List<SummaryClubUi>) {
        val newList = mutableListOf<SummaryClubUi>()
        newList.addAll(clubs)
        newList.addAll(newClubs)

        updateClubs(newList)
        notifyDataSetChanged()
    }

    private fun updateClubs(list: List<SummaryClubUi>) {
        val diffUtil = IntDiffUtil(clubs, list)
        val diffResult = DiffUtil.calculateDiff(diffUtil)

        clubs.clear()
        clubs.addAll(list)

        diffResult.dispatchUpdatesTo(this)
    }

    fun getClubs() = ArrayList<SummaryClubUi>(clubs)

    fun setClubs(savedClubs: ArrayList<SummaryClubUi>) {
        clubs = savedClubs
    }
}