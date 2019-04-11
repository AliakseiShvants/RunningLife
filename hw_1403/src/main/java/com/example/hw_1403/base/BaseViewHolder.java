package com.example.hw_1403.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BaseViewHolder<T extends View> extends RecyclerView.ViewHolder {

    public BaseViewHolder(@NonNull final T itemView) {
        super(itemView);
    }

    public T getView() {
        return (T) itemView;
    }
}
