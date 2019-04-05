package com.example.hw_1403.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

public interface ICompoundView {

    void onViewInflated(@NonNull final Context context);

    @LayoutRes
    int getLayoutResId();
}
