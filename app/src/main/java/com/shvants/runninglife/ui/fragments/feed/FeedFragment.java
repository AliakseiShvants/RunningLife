package com.shvants.runninglife.ui.fragments.feed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shvants.runninglife.R;
import com.shvants.runninglife.ui.activities.main.MainActivity;
import com.shvants.runninglife.ui.base.BaseFragment;
import com.shvants.runninglife.ui.model.UiMoveModel;
import com.shvants.runninglife.ui.model.UiUserModel;

import java.util.Arrays;
import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.VERTICAL;
import static com.shvants.runninglife.utils.Const.FeedFragment.TITLE;
import static java.lang.Boolean.FALSE;

public class FeedFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private FeedPagerAdapter feedPagerAdapter;

    private UiUserModel user;
    private List<UiMoveModel> moves;

    public FeedFragment() {
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

        user = getUserFromDb();
        moves = getMovesFromDb();
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {

        final View feedView = inflater.inflate(getLayoutId(), container, FALSE);

        recyclerView = feedView.findViewById(R.id.recyclerView);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        feedPagerAdapter = new FeedPagerAdapter(getContext(), user, moves);
        recyclerView.setAdapter(feedPagerAdapter);

        final DividerItemDecoration divider = new DividerItemDecoration(getContext(), VERTICAL);
        divider.setDrawable(getContext().getResources().getDrawable(R.drawable.divider));
        recyclerView.addItemDecoration(divider);

        return feedView;
    }

    private UiUserModel getUserFromDb() {
        final UiUserModel userModel = new UiUserModel();
        userModel.setId(1L);
        userModel.setAvatar(R.drawable.ic_avatar_stub);
        userModel.setFullName("Aliaksei Shvants");
        userModel.setLocation("Grodno, Grodno region");

        return userModel;
    }

    private List<UiMoveModel> getMovesFromDb() {
        //todo from database, it is'nt?
        return Arrays.asList(
                new UiMoveModel(1, 1000000, "Morning Run", 10.00, 60, 100, 500, 130, R.drawable.move0),
                new UiMoveModel(2, 2000000, "Afternoon Run", 15.00, 90, 150, 800, 140, R.drawable.move1),
                new UiMoveModel(3, 3000000, "Night Run", 6.00, 40, 10, 200, 110, R.drawable.move2)
        );
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_feed;
    }
}
