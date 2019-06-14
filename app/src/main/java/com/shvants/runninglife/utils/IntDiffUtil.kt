package com.shvants.runninglife.utils

import androidx.recyclerview.widget.DiffUtil
import com.shvants.runninglife.model.ui.EntityIntUi

class IntDiffUtil(private val oldList: List<EntityIntUi>,
                  private val newList: List<EntityIntUi>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]

}