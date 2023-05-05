package com.example.orderingsystem.model.repository;

import androidx.lifecycle.LiveData;
import com.example.orderingsystem.database.dao.MaterialDatabase;
import com.example.orderingsystem.model.api.FirebaseAPI;
import com.example.orderingsystem.model.data.Material;
import com.example.orderingsystem.model.service.FirebaseMaterialService;

import javax.inject.Inject;
import java.util.List;

public class MaterialRepositoryImpl implements MaterialRepository{

    @Inject
    public FirebaseAPI<Material> materialService;

    @Inject
    public MaterialRepositoryImpl(FirebaseAPI<Material> materialService) {
        this.materialService = materialService;
    }

    @Override
    public LiveData<List<Material>> getAll(String key) {
        return materialService.getAll(key);
    }

    @Override
    public LiveData<Material> getById(String id, String key) {
        return materialService.getById(id, key);
    }

    @Override
    public void removeAll(String key) {
        materialService.removeAll(key);
    }

    @Override
    public void removeById(String id, String key) {
        materialService.removeById(id, key);
    }

    @Override
    public void write(Material obj, String key) {
        materialService.write(obj, key);
    }

    @Override
    public void update(Material obj, String key) {
        materialService.update(obj, key);
    }
}
