package com.example.orderingsystem.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.orderingsystem.model.data.Item;
import com.example.orderingsystem.model.repository.Repository;

import java.util.List;

public class MainViewModel extends ViewModel {

    private final Repository repository;

    public MainViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<List<Item>> getAll(String key) {
        return repository.getAll(key);
    }

    public LiveData<Item> getById(int id, String key) {
        return repository.getById(id, key);
    }

    public void removeAll(String key) {
        repository.removeAll(key);
    }

    public void removeById(int id, String key) {
        repository.removeById(id, key);
    }

    public void write(Item obj, String key) {
        repository.write(obj, key);
    }
}
