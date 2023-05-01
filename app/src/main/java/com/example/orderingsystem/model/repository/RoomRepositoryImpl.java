package com.example.orderingsystem.model.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.orderingsystem.model.dao.OrderDao;
import com.example.orderingsystem.model.data.Order;
import com.example.orderingsystem.model.database.OrderDatabase;

import java.util.List;

public class RoomRepositoryImpl extends RoomRepository{

    private final OrderDao orderDao;

    public RoomRepositoryImpl(OrderDatabase database) {
        orderDao = database.orderDao();
    }

    @Override
    public LiveData<List<Order>> getAll() {
        return orderDao.getAll();
    }

    @Override
    public LiveData<List<Order>> getAllByOrderIds(String[] orderIds) {
        return orderDao.getAllByOrderIds(orderIds);
    }

    @Override
    public LiveData<List<Order>> getAllByItemIds(String[] itemIds) {
        return orderDao.getAllByItemIds(itemIds);
    }

    @Override
    public LiveData<List<Order>> getAllByUserIds(String[] userIds) {
        return orderDao.getAllByUserIds(userIds);
    }

    @Override
    public LiveData<List<Order>> getAllByDates(String[] dates) {
        return orderDao.getAllByDates(dates);
    }

    @Override
    public LiveData<Order> getByDate(String date) {
        return orderDao.getByDate(date);
    }

    @Override
    public LiveData<Order> getByName(String str) {
        return orderDao.getByName(str);
    }

    @Override
    public void insertAll(List<Order> orders) {
        orderDao.insertAll(orders);
    }

    @Override
    public void delete(Order order) {
        orderDao.delete(order);
    }
}
