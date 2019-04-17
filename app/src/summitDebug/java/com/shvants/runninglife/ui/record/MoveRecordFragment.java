package com.shvants.runninglife.ui.record;

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

public class MoveRecordFragment extends BaseFragment {

    @Override
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        //todo
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MainActivity) getActivity()).setActionBarTitle(Const.MoveRecordFragment.TITLE);

    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {

        final View moveRecordView = inflater.inflate(getLayoutId(), container, false);

        return moveRecordView;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_move_record;
    }
}
