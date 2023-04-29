package com.example.orderingsystem.model.repository;

import androidx.lifecycle.LiveData;
import com.example.orderingsystem.model.data.ShopItem;

import java.util.List;

public abstract class ShopItemRepository {

    public abstract LiveData<List<ShopItem>> getAll(String key);

    public abstract LiveData<ShopItem> getById(String id, String key);

    public abstract void removeAll(String key);

    public abstract void removeById(String id, String key);

    public abstract void write(ShopItem obj, String key);


}
