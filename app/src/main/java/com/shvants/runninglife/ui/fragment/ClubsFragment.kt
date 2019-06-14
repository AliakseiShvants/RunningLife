package com.shvants.runninglife.ui.fragment

import android.os.Bundle
import android.view.View
import com.shvants.runninglife.R
import com.shvants.runninglife.mvp.contract.ClubsContract
import com.shvants.runninglife.mvp.presenter.ClubsPresenter
import kotlinx.android.synthetic.main.fragment_clubs.*

class ClubsFragment private constructor() : BaseFragment(), ClubsContract.View {

    private lateinit var presenter: ClubsContract.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = ClubsPresenter(requireContext().applicationContext)
        presenter.attachView(this)

        clubsImage.layoutParams.height = presenter.getClubsImageHeight()
        clubsImage.setImageResource(R.drawable.clubs_img)
    }

    override fun getLayoutResId() = R.layout.fragment_clubs

    override fun showMessage(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        fun getInstance() = ClubsFragment()
    }
}