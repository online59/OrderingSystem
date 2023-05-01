package com.example.orderingsystem.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.orderingsystem.model.data.Order;

import java.util.List;

@Dao
public interface OrderDao {

    @Query("SELECT * FROM orders")
    LiveData<List<Order>> getAll();

    @Query("SELECT * FROM orders WHERE orderId IN (:orderIds)")
    LiveData<List<Order>> getAllByOrderIds(String[] orderIds);

    @Query("SELECT * FROM orders WHERE itemId IN (:itemIds)")
    LiveData<List<Order>> getAllByItemIds(String[] itemIds);

    @Query("SELECT * FROM orders WHERE userId IN (:userIds)")
    LiveData<List<Order>> getAllByUserIds(String[] userIds);

    @Query("SELECT * FROM orders WHERE purchaseDate IN (:dates)")
    LiveData<List<Order>> getAllByDates(String[] dates);

    @Query("SELECT * FROM orders WHERE purchaseDate == :date")
    LiveData<Order> getByDate(String date);

    @Query("SELECT * FROM orders WHERE itemName LIKE :str LIMIT 1")
    LiveData<Order> getByName(String str);

    @Insert
    void insertAll(List<Order> orders);

    @Delete
    void delete(Order order);
}
