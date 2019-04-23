package com.shvants.runninglife.ui.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.shvants.runninglife.ui.model.MoveModelUi
import com.shvants.runninglife.utils.IAdapter
import com.shvants.runninglife.utils.service.IService

abstract class BaseFragment : Fragment() {

    abstract fun loadMoreItems(start: Int, end: Int)

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getLayoutManager(): RecyclerView.LayoutManager

    abstract fun getAdapter(): IAdapter

    abstract fun isLoading(): Boolean

    abstract fun getService(): IService<MoveModelUi>
}
