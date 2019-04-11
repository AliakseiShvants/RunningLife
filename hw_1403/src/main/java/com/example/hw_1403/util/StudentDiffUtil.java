package com.example.hw_1403.util;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.example.hw_1403.backend.entities.Student;

import java.util.List;

public class StudentDiffUtil extends DiffUtil.Callback {

    private List<Student> oldList;
    private List<Student> newList;

    public StudentDiffUtil(final List<Student> oldList, final List<Student> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(final int oldItemPosition, final int newItemPosition) {

        final Student oldItem = oldList.get(oldItemPosition);
        final Student newItem = newList.get(newItemPosition);

        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(final int oldItemPosition, final int newItemPosition) {

        final Student oldItem = oldList.get(oldItemPosition);
        final Student newItem = newList.get(newItemPosition);

        return oldItem.getName().equals(newItem.getName())
                && oldItem.getHwCount() == newItem.getHwCount();
    }

    @Nullable
    @Override
    public Object getChangePayload(final int oldItemPosition, final int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
