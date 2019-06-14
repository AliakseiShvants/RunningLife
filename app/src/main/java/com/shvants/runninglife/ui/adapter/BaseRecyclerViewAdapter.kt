package com.shvants.runninglife.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IntDef
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.shvants.runninglife.R
import java.lang.Boolean

abstract class BaseRecyclerViewAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ViewType.ITEM) {
            ViewHolder(inflater.inflate(getLayoutResId(), parent, Boolean.FALSE))
        } else {
            ViewHolder(inflater.inflate(R.layout.layout_progress, parent, Boolean.FALSE))
        }
    }

    @LayoutRes
    abstract fun getLayoutResId(): Int

    private inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    @IntDef(ViewType.ITEM, ViewType.LOADING)
    @Retention(AnnotationRetention.SOURCE)
    internal annotation class ViewType {
        companion object {
            const val ITEM = 0
            const val LOADING = 1
        }
    }
}