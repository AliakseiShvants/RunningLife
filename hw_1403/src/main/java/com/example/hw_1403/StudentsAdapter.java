package com.example.hw_1403;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hw_1403.backend.entities.Student;

import java.util.List;

class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Student> students;

    public StudentsAdapter(final Context context) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        students = Student.Holder.init();
    }

    @NonNull
    @Override
    public StudentsAdapter.StudentViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup,
                                                                final int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull final StudentsAdapter.StudentViewHolder studentViewHolder,
                                 final int i) {

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        public StudentViewHolder(@NonNull final View itemView) {
            super(itemView);
        }
    }
}
