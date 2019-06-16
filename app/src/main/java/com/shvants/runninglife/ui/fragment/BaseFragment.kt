package com.shvants.runninglife.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

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

    @LayoutRes
    abstract fun getLayoutResId(): Int

}