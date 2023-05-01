package com.example.orderingsystem.model.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.orderingsystem.model.dao.OrderDao;
import com.example.orderingsystem.model.data.Order;

@Database(entities = {Order.class}, version = 1)
public abstract class OrderDatabase extends RoomDatabase {

    public abstract OrderDao orderDao();
}
