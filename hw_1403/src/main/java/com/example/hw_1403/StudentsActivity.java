package com.example.hw_1403;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hw_1403.backend.IWebService;
import com.example.hw_1403.backend.StudentsWebService;
import com.example.hw_1403.backend.entities.Student;
import com.example.hw_1403.recyclerview.ItemTouchCallback;
import com.example.hw_1403.recyclerview.StudentsAdapter;
import com.example.hw_1403.util.ICallback;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class StudentsActivity extends AppCompatActivity implements StudentsAdapter.OnItemEditClickListener {

    public static final int PAGE_SIZE = 8;
    public static final int MAX_VISIBLE_ITEMS = 18;
    private final IWebService<Student> studentsWebService = new StudentsWebService();

    private boolean isLoading = false;
    private StudentsAdapter adapter;
    private LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(final @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.recycler_view);
        actionBar.setDisplayShowTitleEnabled(TRUE);

        recyclerView = findViewById(android.R.id.list);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, FALSE);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new StudentsAdapter(this);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(final RecyclerView recyclerView, final int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
                super.onScrolled(recyclerView, dx, dy);

                final int totalItemCount = layoutManager.getItemCount();
                final int visibleItemCount = layoutManager.getChildCount();
                final int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (totalItemCount >= MAX_VISIBLE_ITEMS) {
                    adapter.setShowLastViewAsLoading(FALSE);

                    return;
                }

                if (!isLoading
                        && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0
                        && totalItemCount >= PAGE_SIZE) {

                    int end = totalItemCount + PAGE_SIZE;

                    if (end > studentsWebService.size()) {
                        end = studentsWebService.size();
                    }
                    loadMoreItems(totalItemCount, end);
                }
            }
        });

        loadMoreItems(0, PAGE_SIZE);

        new ItemTouchHelper(new ItemTouchCallback(recyclerView, adapter))
                .attachToRecyclerView(recyclerView);

        recyclerView.setItemAnimator(new DefaultItemAnimator() {
            @Override
            public boolean animateMove(final RecyclerView.ViewHolder holder, final int fromX,
                                       final int fromY, final int toX, final int toY) {
                return super.animateMove(holder, fromX, fromY, toX, toY);
            }
        });

        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

        findViewById(R.id.add_image_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                showAddDialog();
            }
        });
    }

    private void showAddDialog() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.student_dialog, null);

        final TextView titleView = dialogView.findViewById(R.id.dialog_title_text_view);
        titleView.setText(R.string.add_new_student);

        final EditText nameText = dialogView.findViewById(R.id.dialog_name_edit_text);
        final EditText hwCountText = dialogView.findViewById(R.id.dialog_hw_edit_text);

        builder.setView(dialogView).setPositiveButton(R.string.add,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        final String name = nameText.getText().toString();
                        final String hwCount = hwCountText.getText().toString();

                        adapter.addStudent(new Student(name, Integer.parseInt(hwCount)));
                        dialog.dismiss();
                    }
                });

        builder.setView(dialogView).setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        dialog.dismiss();
                    }
                });

        builder.show();
    }

    @Override
    public void showEditDialog(final long id, final String name, final String hwCount) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.student_dialog, null);

        final TextView titleView = dialogView.findViewById(R.id.dialog_title_text_view);
        titleView.setText(R.string.edit_student);

        final EditText nameText = dialogView.findViewById(R.id.dialog_name_edit_text);
        nameText.setText(name);

        final EditText hwCountText = dialogView.findViewById(R.id.dialog_hw_edit_text);
        hwCountText.setText(hwCount);

        builder.setView(dialogView).setPositiveButton(R.string.save,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {

                        final String name = nameText.getText().toString();
                        final String hwCount = hwCountText.getText().toString();

                        adapter.editStudent(id, name, hwCount);
                        dialog.dismiss();
                    }
                });

        builder.setView(dialogView).setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(final DialogInterface dialog, final int which) {
                        dialog.dismiss();
                    }
                });

        builder.show();
    }

    private void loadMoreItems(final int pStartPosition, final int pEndPosition) {
        isLoading = TRUE;
        adapter.setShowLastViewAsLoading(TRUE);
        studentsWebService.getEntities(pStartPosition, pEndPosition, new ICallback<List<Student>>() {

            @Override
            public void onResult(final List<Student> result) {
                adapter.addItems(result);
                isLoading = FALSE;
            }
        });
    }
}
