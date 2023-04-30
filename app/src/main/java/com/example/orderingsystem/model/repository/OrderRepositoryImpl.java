package com.example.orderingsystem.model.repository;

import androidx.lifecycle.LiveData;
import com.example.orderingsystem.model.api.FirebaseAPI;
import com.example.orderingsystem.model.data.Ordering;

import java.util.List;

public class OrderRepositoryImpl extends MainRepository<Ordering>{

    private FirebaseAPI<Ordering> service;

    public OrderRepositoryImpl(FirebaseAPI<Ordering> service) {
        this.service = service;
    }

    @Override
    public LiveData<List<Ordering>> getAll(String key) {
        return service.getAll(key);
    }

    @Override
    public LiveData<Ordering> getById(String id, String key) {
        return service.getById(id, key);
    }

    @Override
    public void removeAll(String key) {
        service.removeAll(key);
    }

    @Override
    public void removeById(String id, String key) {
        service.removeById(id, key);
    }

    @Override
    public void write(Ordering obj, String key) {
        service.write(obj, key);
    }
}
