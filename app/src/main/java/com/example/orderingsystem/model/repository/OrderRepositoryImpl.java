package com.example.orderingsystem.model.repository;

import androidx.lifecycle.LiveData;
import com.example.orderingsystem.model.api.FirebaseAPI;
import com.example.orderingsystem.model.data.Order;
import com.example.orderingsystem.model.service.FirebaseOrderService;

import javax.inject.Inject;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository{

    @Inject
    public FirebaseAPI<Order> orderService;

    @Inject
    public OrderRepositoryImpl(FirebaseAPI<Order> orderService) {
        this.orderService = orderService;
    }

    @Override
    public LiveData<List<Order>> getAll(String key) {
        return orderService.getAll(key);
    }

    @Override
    public LiveData<Order> getById(String id, String key) {
        return orderService.getById(id, key);
    }

    @Override
    public void removeAll(String key) {
        orderService.removeAll(key);
    }

    @Override
    public void removeById(String id, String key) {
        orderService.removeById(id, key);
    }

    @Override
    public void write(Order obj, String key) {
        orderService.write(obj, key);
    }

    @Override
    public void update(Order obj, String key) {
        orderService.update(obj, key);
    }
}
