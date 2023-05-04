package com.example.orderingsystem.model.repository;

import androidx.lifecycle.LiveData;
import com.example.orderingsystem.model.data.Order;

import java.util.List;

public interface OrderRepository {

    LiveData<List<Order>> getAll(String key);

    LiveData<Order> getById(String id, String key);

    void removeAll(String key);

    void removeById(String id, String key);

    void write(Order obj, String key);

    void update(Order obj, String key);
}
