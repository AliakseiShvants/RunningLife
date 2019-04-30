package com.shvants.runninglife.ui.fragments.clubs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.shvants.runninglife.R;
import com.shvants.runninglife.ui.base.BaseFragment;
import com.shvants.runninglife.ui.main.MainActivity;
import com.shvants.runninglife.ui.model.MoveModelUi;
import com.shvants.runninglife.utils.Const;
import com.shvants.runninglife.utils.IAdapter;
import com.shvants.runninglife.utils.service.IService;

import org.jetbrains.annotations.NotNull;

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

    @Override
    public void loadMoreItems(final int start, final int end) {

    }

    @NotNull
    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return null;
    }

    @NotNull
    @Override
    public IAdapter getAdapter() {
        return null;
    }

    @Override
    public boolean isLoading() {
        return false;
    }

    @NotNull
    @Override
    public IService<MoveModelUi> getService() {
        return null;
    }
}
