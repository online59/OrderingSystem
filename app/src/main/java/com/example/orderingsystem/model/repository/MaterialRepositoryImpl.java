package com.example.orderingsystem.model.repository;

import androidx.lifecycle.LiveData;
import com.example.orderingsystem.model.api.FirebaseAPI;
import com.example.orderingsystem.model.data.Material;

import java.util.List;

public class MaterialRepositoryImpl extends MainRepository<Material> {

    private final FirebaseAPI<Material> service;

    public MaterialRepositoryImpl(FirebaseAPI<Material> service) {
        this.service = service;
    }

    @Override
    public LiveData<List<Material>> getAll(String key) {
        return service.getAll(key);
    }

    @Override
    public LiveData<Material> getById(String id, String key) {
        return service.getById(id, key);
    }

    @Override
    public void removeAll(String key) {
        service.removeAll(key);
    }

    @Override
    public void removeById(String id, String key) {
        service.removeById(id, key);
    }

    @Override
    public void write(Material obj, String key) {
        service.write(obj, key);
    }
}
