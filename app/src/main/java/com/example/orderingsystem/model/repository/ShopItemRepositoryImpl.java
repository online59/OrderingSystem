package com.example.orderingsystem.model.repository;

import androidx.lifecycle.LiveData;
import com.example.orderingsystem.model.api.FirebaseAPI;
import com.example.orderingsystem.model.data.ShopItem;

import java.util.List;

public class ShopItemRepositoryImpl extends MainRepository<ShopItem> {

    private final FirebaseAPI<ShopItem> service;

    public ShopItemRepositoryImpl(FirebaseAPI<ShopItem> service) {
        this.service = service;
    }

    @Override
    public LiveData<List<ShopItem>> getAll(String key) {
        return service.getAll(key);
    }

    @Override
    public LiveData<ShopItem> getById(String id, String key) {
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
    public void write(ShopItem obj, String key) {
        service.write(obj, key);
    }
}
