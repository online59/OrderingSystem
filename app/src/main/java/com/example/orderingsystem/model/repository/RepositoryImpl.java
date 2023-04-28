package com.example.orderingsystem.model.repository;

import androidx.lifecycle.LiveData;
import com.example.orderingsystem.model.api.FirebaseAPI;
import com.example.orderingsystem.model.data.Item;
import com.example.orderingsystem.model.service.FirebaseService;

import java.util.List;

public class RepositoryImpl implements Repository<Item>{

    private final FirebaseAPI<Item> service;

    public RepositoryImpl(FirebaseAPI<Item> service) {
        this.service = service;
    }

    @Override
    public LiveData<List<Item>> getAll(String key) {
        return service.getAll(key);
    }

    @Override
    public LiveData<Item> getById(int id, String key) {
        return service.getById(id, key);
    }

    @Override
    public void removeAll(String key) {
        service.removeAll(key);
    }

    @Override
    public void removeById(int id, String key) {
        service.removeById(id, key);
    }

    @Override
    public void write(Item obj, String key) {
        service.write(obj, key);
    }
}
