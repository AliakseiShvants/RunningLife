package com.shvants.runninglife.utils.service

import android.os.Handler
import android.os.Looper
import com.shvants.runninglife.R
import com.shvants.runninglife.ui.model.MoveModelUi
import com.shvants.runninglife.ui.model.RunMoveModelUi
import com.shvants.runninglife.utils.ICallback

class RunMoveService : IService<MoveModelUi> {

    private val moves: MutableList<MoveModelUi> = ArrayList()
    private val handler = Handler(Looper.getMainLooper())

    init {
        moves.add(RunMoveModelUi(1, 1000000, "Morning Run0", 10.00, (1 * 3600 + 23 * 60 + 45), 100, 500, 130))
        moves.add(RunMoveModelUi(2, 2000000, "Afternoon Run1", 15.00, (0 * 3600 + 23 * 60 + 45), 150, 800, 140, R.drawable.move1))
        moves.add(RunMoveModelUi(3, 3000000, "Night Run2", 6.00, (1 * 3600 + 0 * 60 + 45), 10, 200, 110, R.drawable.move0))
        moves.add(RunMoveModelUi(3, 3000000, "Night Run3", 6.00, (1 * 3600 + 0 * 60 + 45), 10, 200, 110, R.drawable.move0))
        moves.add(RunMoveModelUi(3, 3000000, "Night Run4", 6.00, (1 * 3600 + 0 * 60 + 45), 10, 200, 110))
        moves.add(RunMoveModelUi(3, 3000000, "Night Run5", 6.00, (1 * 3600 + 0 * 60 + 45), 10, 200, 110, R.drawable.move0))
        moves.add(RunMoveModelUi(3, 3000000, "Night Run6", 6.00, (1 * 3600 + 0 * 60 + 45), 10, 200, 110, R.drawable.move0))
    }

    override fun getEntity(position: Int) = moves[position]

    override fun getEntities() = moves

    override fun getEntities(startRange: Int,
                             endRange: Int,
                             callback: ICallback<List<MoveModelUi>>) {
        handler.postDelayed({
            callback.onResult(moves.subList(startRange, endRange))
        }, 1000)
    }

    override fun size() = moves.size
}