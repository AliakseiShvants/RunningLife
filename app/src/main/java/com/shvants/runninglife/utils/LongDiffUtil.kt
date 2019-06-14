package com.shvants.runninglife.utils

import androidx.recyclerview.widget.DiffUtil
import com.shvants.runninglife.model.ui.EntityLongUi

class LongDiffUtil(private val oldList: List<EntityLongUi>,
                   private val newList: List<EntityLongUi>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]

}