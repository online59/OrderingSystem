package com.example.orderingsystem.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.orderingsystem.model.data.Order;
import com.example.orderingsystem.model.repository.OrderRepository;
import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.lifecycle.HiltViewModel;

import javax.inject.Inject;
import java.util.List;

public class OrderViewModel extends ViewModel {

    @Inject
    public OrderRepository orderRepository;

    @Inject
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
