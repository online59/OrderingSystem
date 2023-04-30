package com.example.orderingsystem.model.repository;

import androidx.lifecycle.LiveData;
import com.example.orderingsystem.model.data.User;

import java.util.List;

public abstract class MainRepository<T> {

    public abstract LiveData<List<T>> getAll(String key);

    public abstract LiveData<T> getById(String id, String key);

    public abstract void removeAll(String key);

    public abstract void removeById(String id, String key);

    public abstract void write(T obj, String key);
}
