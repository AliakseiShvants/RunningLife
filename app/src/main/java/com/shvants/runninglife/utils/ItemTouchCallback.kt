package com.shvants.runninglife.utils

import androidx.annotation.NonNull
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

//class ItemTouchCallback(
//        recycler: RecyclerView,
//        adapter: IAdapter
//) : ItemTouchHelper.Callback(
//        ItemTouchHelper.UP or ItemTouchHelper.DOWN,
//        ItemTouchHelper.START or ItemTouchHelper.END
//){
//
//    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//
//}
class ItemTouchCallback(recycler: RecyclerView,
                        private val adapter: IAdapter) :
        ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                ItemTouchHelper.START or ItemTouchHelper.END) {

    override fun onMove(@NonNull recyclerView: RecyclerView,
                        @NonNull viewHolder: RecyclerView.ViewHolder,
                        @NonNull viewHolder1: RecyclerView.ViewHolder): Boolean {

        adapter.onItemMove(viewHolder.adapterPosition, viewHolder1.adapterPosition)

        return true
    }

    override fun onSwiped(@NonNull viewHolder: RecyclerView.ViewHolder, direction: Int) {}
}

