package com.example.orderingsystem.model.room;

import androidx.lifecycle.LiveData;
import com.example.orderingsystem.model.data.Order;

import java.util.List;

public interface RoomRepository {


    LiveData<List<Order>> getAll();

    LiveData<List<Order>> getAllByOrderIds(String[] orderIds);

    LiveData<List<Order>> getAllByItemIds(String[] itemIds);

    LiveData<List<Order>> getAllByUserIds(String[] userIds);

    LiveData<List<Order>> getAllByDates(String[] dates);

    LiveData<Order> getByDate(String date);

    LiveData<Order> getByName(String str);

    void insertAll(List<Order> orders);

    void delete(Order order);
}
