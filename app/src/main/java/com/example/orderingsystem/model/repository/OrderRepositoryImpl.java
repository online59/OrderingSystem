package com.example.orderingsystem.model.repository;

import androidx.lifecycle.LiveData;
import com.example.orderingsystem.model.api.FirebaseAPI;
import com.example.orderingsystem.model.data.Order;

import java.util.List;

public class OrderRepositoryImpl extends MainRepository<Order>{

    private FirebaseAPI<Order> service;

    public OrderRepositoryImpl(FirebaseAPI<Order> service) {
        this.service = service;
    }

    @Override
    public LiveData<List<Order>> getAll(String key) {
        return service.getAll(key);
    }

    @Override
    public LiveData<Order> getById(String id, String key) {
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
    public void write(Order obj, String key) {
        service.write(obj, key);
    }

    @Override
    public void update(Order obj, String key) {
        service.update(obj, key);
    }
}
