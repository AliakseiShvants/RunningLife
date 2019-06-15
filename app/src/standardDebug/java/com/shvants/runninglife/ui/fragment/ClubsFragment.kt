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
import com.shvants.runninglife.model.ui.SummaryClubUi
import com.shvants.runninglife.mvp.contract.ClubsContract
import com.shvants.runninglife.mvp.presenter.ClubsPresenter
import com.shvants.runninglife.ui.adapter.ClubsAdapter
import com.shvants.runninglife.utils.ICallback
import kotlinx.android.synthetic.main.fragment_clubs.*

class ClubsFragment private constructor() : BaseFragment(), ClubsContract.View {

    private lateinit var presenter: ClubsContract.Presenter
    private lateinit var clubsAdapter: ClubsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = ClubsPresenter(requireContext().applicationContext)
        presenter.attachView(this)

        clubsAdapter = ClubsAdapter(requireContext().applicationContext, presenter)

        clubsImage.layoutParams.height = presenter.getClubsImageHeight()
        clubsImage.setImageResource(R.drawable.clubs_img)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = clubsAdapter
            itemAnimator = object : DefaultItemAnimator() {}
            addItemDecoration(DividerItemDecoration(context, LinearLayout.HORIZONTAL))
        }

        presenter.loadClubs(object : ICallback<List<SummaryClubUi>> {

            override fun onResult(result: List<SummaryClubUi>) {
                clubsAdapter.addClubs(result)
                clubsCount.text = result.size.toString()
            }

            override fun onError(message: String) {
                showMessage(message)
            }
        })
    }

    override fun getLayoutResId() = R.layout.fragment_clubs

    override fun showMessage(message: String) {
        errTextView.visibility = View.VISIBLE
        (errTextView as TextView).text = message
    }

    companion object {
        fun getInstance() = ClubsFragment()
    }
}