package com.shvants.runninglife.ui.fragment

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.shvants.runninglife.utils.Const

abstract class BaseFragment : Fragment() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        retainInstance = true
//
//        super.onCreate(savedInstanceState)
//    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutResId(), container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

//        outState.putParcelable("athlete", myFeedAdapter.athlete)
        outState.putParcelableArrayList(Const.ENTITY_LIST, getEntityList())
    }

    @LayoutRes
    abstract fun getLayoutResId(): Int

    abstract fun getEntityList(): ArrayList<out Parcelable>
}