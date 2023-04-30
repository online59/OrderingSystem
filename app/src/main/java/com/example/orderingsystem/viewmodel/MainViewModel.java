package com.example.orderingsystem.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.orderingsystem.model.repository.MainRepository;

import java.util.List;

public class MainViewModel<T> extends ViewModel {

    private final MainRepository<T> shopItemRepository;

    public MainViewModel(MainRepository<T> shopItemRepository) {
        this.shopItemRepository = shopItemRepository;
    }

    public LiveData<List<T>> getAll(String key) {
        return shopItemRepository.getAll(key);
    }

    public LiveData<T> getById(String id, String key) {
        return shopItemRepository.getById(id, key);
    }

    public void removeAll(String key) {
        shopItemRepository.removeAll(key);
    }

    public void removeById(String id, String key) {
        shopItemRepository.removeById(id, key);
    }

    public void write(T obj, String key) {
        shopItemRepository.write(obj, key);
    }
}
