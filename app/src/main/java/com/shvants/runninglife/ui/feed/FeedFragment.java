package com.shvants.runninglife.ui.feed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shvants.runninglife.R;
import com.shvants.runninglife.ui.base.BaseFragment;
import com.shvants.runninglife.ui.main.MainActivity;
import com.shvants.runninglife.utils.Const;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FeedFragment extends BaseFragment {

    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        //todo
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MainActivity) getActivity()).setActionBarTitle(Const.FeedFragment.TITLE);

    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {

        final View feedView = inflater.inflate(getLayoutId(), container, false);
//        final RecyclerView recyclerView = feedView.findViewById(android.R.id.list);
//        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

//        recyclerView.setLayoutManager(layoutManager);

//        final FeedPagerAdapter feedPagerAdapter = new FeedPagerAdapter(getActivity());
//        recyclerView.setAdapter(feedPagerAdapter);

        return feedView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_feed;
    }
}
