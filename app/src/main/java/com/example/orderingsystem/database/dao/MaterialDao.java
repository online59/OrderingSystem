package com.example.orderingsystem.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.orderingsystem.database.entity.MaterialEntity;
import com.example.orderingsystem.model.data.Material;
import io.reactivex.Completable;

import java.util.List;

@Dao
public interface MaterialDao {


    @Query("SELECT * FROM materialentity")
    LiveData<List<MaterialEntity>> getAll();

    @Query("SELECT * FROM materialentity WHERE item_id IN (:materialIds)")
    LiveData<List<MaterialEntity>> getAllByIds(int[] materialIds);

    @Query("SELECT * FROM materialentity WHERE item_name LIKE :str LIMIT 1")
    LiveData<MaterialEntity> getByName(String str);

    @Insert
    Completable insertAll(List<MaterialEntity> materials);

    @Delete
    Completable delete(MaterialEntity material);
}
