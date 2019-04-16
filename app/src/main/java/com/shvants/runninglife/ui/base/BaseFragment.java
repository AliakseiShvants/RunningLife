package com.shvants.runninglife.ui.base;

import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    @LayoutRes
    public abstract int getLayoutId();
}
