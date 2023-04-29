package com.example.orderingsystem.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.orderingsystem.model.data.ShopItem;
import com.example.orderingsystem.model.repository.ShopItemRepository;

import java.util.List;

public class ItemViewModel extends ViewModel {

    private final ShopItemRepository shopItemRepository;

    public ItemViewModel(ShopItemRepository shopItemRepository) {
        this.shopItemRepository = shopItemRepository;
    }

    public LiveData<List<ShopItem>> getAll(String key) {
        return shopItemRepository.getAll(key);
    }

    public LiveData<ShopItem> getById(int id, String key) {
        return shopItemRepository.getById(id, key);
    }

    public void removeAll(String key) {
        shopItemRepository.removeAll(key);
    }

    public void removeById(int id, String key) {
        shopItemRepository.removeById(id, key);
    }

    public void write(ShopItem obj, String key) {
        shopItemRepository.write(obj, key);
    }
}
