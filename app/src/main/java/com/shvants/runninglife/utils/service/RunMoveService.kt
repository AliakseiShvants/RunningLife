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
        moves.add(RunMoveModelUi(1, 1000000, "Morning Run", 10.00, 60, 100, 500, 130, R.drawable.move0))
        moves.add(RunMoveModelUi(2, 2000000, "Afternoon Run", 15.00, 90, 150, 800, 140, R.drawable.move1))
        moves.add(RunMoveModelUi(3, 3000000, "Night Run", 6.00, 40, 10, 200, 110, R.drawable.move2))
        moves.add(RunMoveModelUi(1, 1000000, "Morning Run", 10.00, 60, 100, 500, 130, R.drawable.move0))
        moves.add(RunMoveModelUi(2, 2000000, "Afternoon Run", 15.00, 90, 150, 800, 140, R.drawable.move1))
        moves.add(RunMoveModelUi(3, 3000000, "Night Run", 6.00, 40, 10, 200, 110, R.drawable.move2))
        moves.add(RunMoveModelUi(1, 1000000, "Morning Run", 10.00, 60, 100, 500, 130, R.drawable.move0))
        moves.add(RunMoveModelUi(2, 2000000, "Afternoon Run", 15.00, 90, 150, 800, 140, R.drawable.move1))
        moves.add(RunMoveModelUi(3, 3000000, "Night Run", 6.00, 40, 10, 200, 110, R.drawable.move2))
        moves.add(RunMoveModelUi(1, 1000000, "Morning Run", 10.00, 60, 100, 500, 130, R.drawable.move0))
        moves.add(RunMoveModelUi(2, 2000000, "Afternoon Run", 15.00, 90, 150, 800, 140, R.drawable.move1))
        moves.add(RunMoveModelUi(3, 3000000, "Night Run", 6.00, 40, 10, 200, 110, R.drawable.move2))
        moves.add(RunMoveModelUi(1, 1000000, "Morning Run", 10.00, 60, 100, 500, 130, R.drawable.move0))
        moves.add(RunMoveModelUi(2, 2000000, "Afternoon Run", 15.00, 90, 150, 800, 140, R.drawable.move1))
        moves.add(RunMoveModelUi(3, 3000000, "Night Run", 6.00, 40, 10, 200, 110, R.drawable.move2))
        moves.add(RunMoveModelUi(1, 1000000, "Morning Run", 10.00, 60, 100, 500, 130, R.drawable.move0))
        moves.add(RunMoveModelUi(2, 2000000, "Afternoon Run", 15.00, 90, 150, 800, 140, R.drawable.move1))
        moves.add(RunMoveModelUi(3, 3000000, "Night Run", 6.00, 40, 10, 200, 110, R.drawable.move2))
        moves.add(RunMoveModelUi(1, 1000000, "Morning Run", 10.00, 60, 100, 500, 130, R.drawable.move0))
        moves.add(RunMoveModelUi(2, 2000000, "Afternoon Run", 15.00, 90, 150, 800, 140, R.drawable.move1))
        moves.add(RunMoveModelUi(3, 3000000, "Night Run", 6.00, 40, 10, 200, 110, R.drawable.move2))
        moves.add(RunMoveModelUi(1, 1000000, "Morning Run", 10.00, 60, 100, 500, 130, R.drawable.move0))
        moves.add(RunMoveModelUi(2, 2000000, "Afternoon Run", 15.00, 90, 150, 800, 140, R.drawable.move1))
        moves.add(RunMoveModelUi(3, 3000000, "Night Run", 6.00, 40, 10, 200, 110, R.drawable.move2))
        moves.add(RunMoveModelUi(1, 1000000, "Morning Run", 10.00, 60, 100, 500, 130, R.drawable.move0))
        moves.add(RunMoveModelUi(2, 2000000, "Afternoon Run", 15.00, 90, 150, 800, 140, R.drawable.move1))
        moves.add(RunMoveModelUi(3, 3000000, "Night Run", 6.00, 40, 10, 200, 110, R.drawable.move2))
        moves.add(RunMoveModelUi(1, 1000000, "Morning Run", 10.00, 60, 100, 500, 130, R.drawable.move0))
        moves.add(RunMoveModelUi(2, 2000000, "Afternoon Run", 15.00, 90, 150, 800, 140, R.drawable.move1))
        moves.add(RunMoveModelUi(3, 3000000, "Night Run", 6.00, 40, 10, 200, 110, R.drawable.move2))
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

    override fun loadMoreMoves(startRange: Int, endRange: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun size() = moves.size
}