package com.shvants.runninglife.ui.fragments.feed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shvants.runninglife.R;
import com.shvants.runninglife.ui.activities.main.MainActivity;
import com.shvants.runninglife.ui.base.BaseFragment;
import com.shvants.runninglife.ui.model.MoveModelUi;
import com.shvants.runninglife.utils.IAdapter;
import com.shvants.runninglife.utils.ICallback;
import com.shvants.runninglife.utils.ItemTouchCallback;
import com.shvants.runninglife.utils.listener.RecyclerViewScrollListener;
import com.shvants.runninglife.utils.service.IService;
import com.shvants.runninglife.utils.service.RunMoveService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.shvants.runninglife.utils.Const.FeedFragment.TITLE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class FeedFragment extends BaseFragment {

    private static FeedFragment instance;
    private final IService<MoveModelUi> moveService;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FeedPagerAdapter adapter;
    private boolean isLoading = false;

    private FeedFragment() {
        moveService = new RunMoveService();
    }

    public static FeedFragment getInstance() {

        if (instance == null) {
            instance = new FeedFragment();
        }

        return instance;
    }

    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        //todo
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MainActivity) getActivity()).setActionBarTitle(TITLE);
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {

        final View feedView = inflater.inflate(getLayoutId(), container, FALSE);

        recyclerView = feedView.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new FeedPagerAdapter(getContext());
        recyclerView.setAdapter(adapter);

        new RecyclerViewScrollListener(FeedFragment.getInstance());

        loadMoreItems(0, RecyclerViewScrollListener.PAGE_SIZE);

        new ItemTouchHelper(new ItemTouchCallback(recyclerView, adapter))
                .attachToRecyclerView(recyclerView);

        recyclerView.setItemAnimator(new DefaultItemAnimator() {
            @Override
            public boolean animateMove(final RecyclerView.ViewHolder holder,
                                       final int fromX,
                                       final int fromY,
                                       final int toX,
                                       final int toY) {
                return super.animateMove(holder, fromX, fromY, toX, toY);
            }
        });

        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));

        // todo summit version add on click listener (full version)
        return feedView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_feed;
    }


    @Override
    public void loadMoreItems(final int start, final int end) {
        isLoading = TRUE;
        adapter.setShowLastViewAsLoading(TRUE);
        moveService.getEntities(start, end, new ICallback<List<MoveModelUi>>() {

            @Override
            public void onResult(final List<MoveModelUi> result) {
                adapter.addItems(result);
                isLoading = FALSE;
            }
        });
    }

    @NotNull
    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    @NotNull
    @Override
    public IAdapter getAdapter() {
        return adapter;
    }

    @Override
    public boolean isLoading() {
        return isLoading;
    }

    @NotNull
    @Override
    public IService<MoveModelUi> getService() {
        return moveService;
    }
}
