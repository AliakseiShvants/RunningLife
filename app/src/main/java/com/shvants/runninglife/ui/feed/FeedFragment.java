package com.shvants.runninglife.ui.feed;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

public class FeedFragment extends Fragment {

    public static FeedFragment newInstance() {
        final Bundle args = new Bundle();
        final FeedFragment fragment = new FeedFragment();
        fragment.setArguments(args);

        return fragment;
    }


}
