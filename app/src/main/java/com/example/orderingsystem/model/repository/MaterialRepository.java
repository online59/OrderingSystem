package com.example.orderingsystem.model.repository;

import androidx.lifecycle.LiveData;
import com.example.orderingsystem.model.data.Material;

import java.util.List;

public interface MaterialRepository {

    LiveData<List<Material>> getAll(String key);

    LiveData<Material> getById(String id, String key);

    void removeAll(String key);

    void removeById(String id, String key);

    void write(Material obj, String key);

    void update(Material obj, String key);
}
