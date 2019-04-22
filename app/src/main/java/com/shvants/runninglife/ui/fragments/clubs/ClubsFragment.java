package com.shvants.runninglife.ui.fragments.clubs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shvants.runninglife.R;
import com.shvants.runninglife.ui.base.BaseFragment;
import com.shvants.runninglife.ui.activities.main.MainActivity;
import com.shvants.runninglife.utils.Const;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ClubsFragment extends BaseFragment {

    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        //todo
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MainActivity) getActivity()).setActionBarTitle(Const.ClubsFragment.TITLE);

    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_clubs;
    }
}
