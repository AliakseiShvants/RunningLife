package com.example.hw_1403.themes.backend;


import com.example.hw_1403.themes.util.ICallback;

import java.util.List;

public interface IWebService<T> {

    void getEntities(final ICallback<List<T>> pCallback);

    void getEntities(final int pStartRange,
                     final int pEndRange,
                     final ICallback<List<T>> pCallback);

    void removeEntity(final Long pId);
}
