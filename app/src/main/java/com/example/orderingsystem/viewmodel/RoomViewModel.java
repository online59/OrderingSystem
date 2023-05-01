package com.example.orderingsystem.viewmodel;

import androidx.lifecycle.LiveData;
import com.example.orderingsystem.model.data.Order;
import com.example.orderingsystem.model.repository.RoomRepository;

import java.util.List;

public class RoomViewModel {

    private RoomRepository repository;

    public RoomViewModel(RoomRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Order>> getAll() {
        return repository.getAll();
    }


    public LiveData<List<Order>> getAllByOrderIds(String[] orderIds) {
        return repository.getAllByOrderIds(orderIds);
    }


    public LiveData<List<Order>> getAllByItemIds(String[] itemIds) {
        return repository.getAllByItemIds(itemIds);
    }


    public LiveData<List<Order>> getAllByUserIds(String[] userIds) {
        return repository.getAllByUserIds(userIds);
    }


    public LiveData<List<Order>> getAllByDates(String[] dates) {
        return repository.getAllByDates(dates);
    }

    public LiveData<Order> getByDate(String date) {
        return repository.getByDate(date);
    }

    public LiveData<Order> getByName(String str) {
        return repository.getByName(str);
    }


    public void insertAll(List<Order> orders) {
        repository.insertAll(orders);
    }


    public void delete(Order user) {
        repository.delete(user);
    }
}
