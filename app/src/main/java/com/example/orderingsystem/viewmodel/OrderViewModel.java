package com.example.orderingsystem.viewmodel;

import androidx.lifecycle.LiveData;
import com.example.orderingsystem.model.data.Order;
import com.example.orderingsystem.model.repository.OrderRepository;

import java.util.List;

public class OrderViewModel {

    private final OrderRepository orderRepository;

    public OrderViewModel(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public LiveData<List<Order>> getAll(String key) {
        return orderRepository.getAll(key);
    }

    public LiveData<Order> getById(String id, String key) {
        return orderRepository.getById(id, key);
    }

    public void removeAll(String key) {
        orderRepository.removeAll(key);
    }

    public void removeById(String id, String key) {
        orderRepository.removeById(id, key);
    }

    public void write(Order obj, String key) {
        orderRepository.write(obj, key);
    }
}
