package com.example.hw_1403.backend;

import android.os.Handler;
import android.os.Looper;

import com.example.hw_1403.R;
import com.example.hw_1403.backend.entities.Student;
import com.example.hw_1403.util.ICallback;

import java.util.ArrayList;
import java.util.List;

public class StudentsWebService implements IWebService<Student> {

    private final List<Student> students = new ArrayList<>();
    private final Handler handler = new Handler(Looper.getMainLooper());

    {
        students.add(new Student(R.drawable.icon1, "Aliaksei Sh", 6));
        students.add(new Student(R.drawable.icon2, "Maryia", 5));
        students.add(new Student(R.drawable.icon3, "Pavel", 5));
        students.add(new Student(R.drawable.icon4, "Yahor Sh", 4));
        students.add(new Student(R.drawable.icon5, "Anton", 4));
        students.add(new Student(R.drawable.icon6, "Yahor B", 4));
        students.add(new Student(R.drawable.icon1, "Maksim Zh", 4));
        students.add(new Student(R.drawable.icon2, "Uladislau", 5));
        students.add(new Student(R.drawable.icon3, "Alexander", 5));
        students.add(new Student(R.drawable.icon4, "Maksim N", 6));
        students.add(new Student(R.drawable.icon5, "Vitali", 4));
        students.add(new Student(R.drawable.icon6, "Aliaksandr", 6));
        students.add(new Student(R.drawable.icon1, "Kiryl", 5));
        students.add(new Student(R.drawable.icon2, "Aleksei", 5));
        students.add(new Student(R.drawable.icon3, "Natallia", 4));
        students.add(new Student(R.drawable.icon4, "Aliaksei H", 5));
        students.add(new Student(R.drawable.icon5, "Maksim S", 6));
        students.add(new Student(R.drawable.icon6, "Vladyslav", 5));
    }

    @Override
    public List<Student> getEntities() {
        return students;
    }

    @Override
    public void getEntities(final ICallback<List<Student>> callback) {
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                callback.onResult(students);
            }
        }, 500);
    }


    @Override
    public void getEntities(final int startRange,
                            final int endRange,
                            final ICallback<List<Student>> callback) {
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                callback.onResult(students.subList(startRange, endRange));
            }
        }, 500);
    }

    @Override
    public void removeEntity(final Long id) {
        Student removedStudent = null;

        for (final Student student : students) {
            if (student.getId().equals(id)) {
                removedStudent = student;
            }
        }

        students.remove(removedStudent);
    }

    @Override
    public void addEntity(final Student student) {
        students.add(student);
    }

    @Override
    public int size() {
        return students.size();
    }

    @Override
    public void editStudent(final long id, final String name, final String hwCount) {

        students.get((int) id).setName(name);
        students.get((int) id).setHwCount(Integer.parseInt(hwCount));
    }
}
