package com.shvants.runninglife.ui.feed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shvants.runninglife.R;
import com.shvants.runninglife.ui.base.BaseFragment;
import com.shvants.runninglife.ui.main.MainActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

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

//        recyclerView = feedView.findViewById(android.R.id.list);
//        layoutManager = new LinearLayoutManager(getActivity(),
//                VERTICAL, FALSE);
//        recyclerView.setLayoutManager(layoutManager);
//
//        feedPagerAdapter = new FeedPagerAdapter();
//        recyclerView.setAdapter(feedPagerAdapter);

        return feedView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_feed;
    }
}
