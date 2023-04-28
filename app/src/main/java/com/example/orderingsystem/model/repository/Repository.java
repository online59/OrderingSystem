package com.example.orderingsystem.model.repository;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface Repository<T> {

    LiveData<List<T>> getAll(String key);

    LiveData<T> getById(int id, String key);

    void removeAll(String key);

    void removeById(int id, String key);

    void write(T obj, String key);
}
