package com.example.orderingsystem.model.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MaterialEntity.class}, version = 1)
public abstract class MaterialDatabase  extends RoomDatabase {
    public abstract MaterialDao materialDao();

    public MaterialDatabase getMaterialDatabase(Context context) {
        return Room.databaseBuilder(context, MaterialDatabase.class, "Material.db").build();
    }
}
