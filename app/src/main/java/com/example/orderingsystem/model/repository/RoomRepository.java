package com.example.orderingsystem.model.repository;

import androidx.lifecycle.LiveData;
import com.example.orderingsystem.model.data.Order;

import java.util.List;

public abstract class RoomRepository {


    public abstract LiveData<List<Order>> getAll();

    public abstract LiveData<List<Order>> getAllByOrderIds(String[] orderIds);

    public abstract LiveData<List<Order>> getAllByItemIds(String[] itemIds);

    public abstract LiveData<List<Order>> getAllByUserIds(String[] userIds);

    public abstract LiveData<List<Order>> getAllByDates(String[] dates);

    public abstract LiveData<Order> getByDate(String date);

    public abstract LiveData<Order> getByName(String str);

    public abstract void insertAll(List<Order> orders);

    public abstract void delete(Order order);
}
