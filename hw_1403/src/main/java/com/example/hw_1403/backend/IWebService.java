package com.example.hw_1403.backend;

import com.example.hw_1403.backend.entities.Student;
import com.example.hw_1403.util.ICallback;

import java.util.List;

public interface IWebService<T> {

    List<T> getEntities();

    void getEntities(final ICallback<List<T>> callback);

    void getEntities(final int startRange,
                     final int endRange,
                     final ICallback<List<T>> callback);

    void removeEntity(final Long id);

    void addEntity(final Student student);

    int size();

    void editStudent(final long id, final String name, final String hwCount);
}
