package com.shvants.runninglife.utils

import androidx.annotation.NonNull
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class ItemTouchCallback(recycler: RecyclerView,
                        private val adapter: IAdapter) :
        ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                ItemTouchHelper.START or ItemTouchHelper.END) {

    override fun onMove(@NonNull recyclerView: RecyclerView,
                        @NonNull viewHolder: RecyclerView.ViewHolder,
                        @NonNull viewHolder1: RecyclerView.ViewHolder): Boolean {

//        adapter.onItemMove(viewHolder.adapterPosition, viewHolder1.adapterPosition)

        return true
    }

    override fun onSwiped(@NonNull viewHolder: RecyclerView.ViewHolder, direction: Int) {}
}

