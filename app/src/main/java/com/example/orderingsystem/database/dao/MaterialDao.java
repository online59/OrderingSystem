package com.example.orderingsystem.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.orderingsystem.model.data.Material;

import java.util.List;

public interface MaterialDao {


    @Query("SELECT * FROM material")
    LiveData<List<Material>> getAll();

    @Query("SELECT * FROM material WHERE item_id IN (:materialIds)")
    LiveData<List<Material>> getAllByIds(int[] materialIds);

    @Query("SELECT * FROM material WHERE item_name LIKE :str LIMIT 1")
    LiveData<Material> getByName(String str);

    @Insert
    void insertAll(List<Material> materials);

    @Delete
    void delete(Material material);
}
