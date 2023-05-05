package com.example.orderingsystem.database.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.orderingsystem.database.entity.MaterialEntity;

@Database(entities = {MaterialEntity.class}, version = 1)
public abstract class MaterialDatabase  extends RoomDatabase {
    public abstract MaterialDao materialDao();
}
