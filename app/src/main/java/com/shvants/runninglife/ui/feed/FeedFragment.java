package com.shvants.runninglife.ui.feed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shvants.runninglife.R;
import com.shvants.runninglife.ui.base.BaseFragment;
import com.shvants.runninglife.ui.main.MainActivity;
import com.shvants.runninglife.ui.model.UiMoveModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.VERTICAL;
import static com.shvants.runninglife.utils.Const.FeedFragment.TITLE;
import static java.lang.Boolean.FALSE;

public class FeedFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private FeedPagerAdapter feedPagerAdapter;

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

        recyclerView = feedView.findViewById(android.R.id.list);
        layoutManager = new LinearLayoutManager(getActivity(), VERTICAL, FALSE);
        recyclerView.setLayoutManager(layoutManager);

        final List<UiMoveModel> moves = getMovesFromDb();
        feedPagerAdapter = new FeedPagerAdapter(getContext(), moves);
//        recyclerView.setAdapter(feedPagerAdapter);

        return feedView;
    }

    private List<UiMoveModel> getMovesFromDb() {
        //todo from database, it is'nt?
        return Arrays.asList(
                new UiMoveModel(1, 10.00, 60L, "Morning Run", 100, 500, 130, R.drawable.move0),
                new UiMoveModel(1, 15.00, 90L, "Afternoon Run", 150, 800, 140, R.drawable.move1),
                new UiMoveModel(1, 6.00, 40L, "Night Run", 10, 200, 110, R.drawable.move2)
        );
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_feed;
    }
}
