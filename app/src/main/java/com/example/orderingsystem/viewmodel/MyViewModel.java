package com.example.orderingsystem.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.orderingsystem.model.data.Item;
import com.example.orderingsystem.model.repository.Repository;

import java.util.List;

public class MyViewModel<T> extends ViewModel {

    private final Repository<T> repository;

    public MyViewModel(Repository<T> repository) {
        this.repository = repository;
    }

    public LiveData<List<T>> getAll(String key) {
        return repository.getAll(key);
    }

    public LiveData<T> getById(int id, String key) {
        return repository.getById(id, key);
    }

    public void removeAll(String key) {
        repository.removeAll(key);
    }

    public void removeById(int id, String key) {
        repository.removeById(id, key);
    }

    public void write(T obj, String key) {
        repository.write(obj, key);
    }
}
