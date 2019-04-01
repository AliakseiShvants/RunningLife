package com.example.hw_1403.themes.uicomponents.base;

import android.content.Context;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;

public interface ICompoundView {

    void onViewInflated(@NonNull final Context pContext);

    @LayoutRes
    int getLayoutResId();
}
