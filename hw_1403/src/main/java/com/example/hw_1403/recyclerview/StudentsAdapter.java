package com.example.hw_1403.recyclerview;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hw_1403.R;
import com.example.hw_1403.backend.IWebService;
import com.example.hw_1403.backend.StudentsWebService;
import com.example.hw_1403.backend.entities.Student;
import com.example.hw_1403.util.StudentDiffUtil;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Boolean.FALSE;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.ViewHolder> {

    private OnItemEditClickListener itemEditListener;
    private final LayoutInflater inflater;
    private final List<Student> students = new ArrayList<>();
    private final IWebService<Student> studentsWebService = new StudentsWebService();
    private boolean isShowLastViewAsLoading = FALSE;

    public StudentsAdapter(final Context context) {
        this.itemEditListener = (OnItemEditClickListener) context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent,
                                         @ViewType final int viewType) {
        if (viewType == ViewType.STUDENT) {
            return new ViewHolder(new StudentView(parent.getContext()));
        } else {
            return new ViewHolder(inflater.inflate(R.layout.layout_progress, parent, FALSE));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {

        if (getItemViewType(position) == ViewType.STUDENT) {

            final Student student = students.get(position);

            ((StudentView) viewHolder.itemView)
                    .setStudentId(student.getId())
                    .setStudentAvatar(student.getAvatarId())
                    .setStudentName(student.getName())
                    .setStudentHwCount(student.getHwCount());
        }
    }

    @ViewType
    @Override
    public int getItemViewType(final int position) {
        if (position < students.size()) {
            return ViewType.STUDENT;
        } else {
            return ViewType.LOADING;
        }
    }


    @Override
    public int getItemCount() {
        return students.size();
    }

    public void setShowLastViewAsLoading(final boolean isShow) {
        if (isShow != isShowLastViewAsLoading) {
            isShowLastViewAsLoading = isShow;
            notifyDataSetChanged();
        }
    }

    public void addItems(final List<Student> result) {
        students.addAll(result);
        notifyDataSetChanged();
    }

    public void onItemMove(final int fromPosition, final int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(students, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(students, i, i - 1);
            }
        }

        notifyItemMoved(fromPosition, toPosition);
    }

    public void onItemDismiss(final int adapterPosition) {
        deleteByIndex(adapterPosition);
    }

    public void deleteByIndex(final int i) {
        students.remove(i);
        studentsWebService.removeEntity((long) i);
        notifyItemRemoved(i);
    }

    private void updateItems(final List<Student> items) {

        final DiffUtil.DiffResult diffResult
                = DiffUtil.calculateDiff(new StudentDiffUtil(students, items));

        students.clear();
        students.addAll(items);

        diffResult.dispatchUpdatesTo(this);
    }

    public void addStudent(final Student student) {
        studentsWebService.addEntity(student);
        students.add(student);
        notifyDataSetChanged();
    }

    public void editStudent(final long id, final String name, final String hwCount) {

        studentsWebService.editStudent(id, name, hwCount);

        updateItems(studentsWebService.getEntities());
    }

    @IntDef({ViewType.STUDENT, ViewType.LOADING})
    @Retention(RetentionPolicy.SOURCE)
    @interface ViewType {

        int STUDENT = 0;
        int LOADING = 1;
    }

    public interface OnItemEditClickListener {
        void showEditDialog(final long id, final String name, final String hwCount);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(final View view) {
            super(view);

            final TextView idView = view.findViewById(R.id.idView);
            final TextView nameView = view.findViewById(R.id.nameView);
            final TextView hwCountView = view.findViewById(R.id.hwCountView);

            view.findViewById(R.id.editImageView).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(final View v) {

                    final long id = Long.parseLong(idView.getText().toString());
                    final String name = nameView.getText().toString();
                    final String hwCount = hwCountView.getText().toString();

                    itemEditListener.showEditDialog(id, name, hwCount);
                }
            });
        }
    }
}
