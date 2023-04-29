package com.example.orderingsystem.model.repository;

import androidx.lifecycle.LiveData;
import com.example.orderingsystem.model.api.FirebaseAPI;
import com.example.orderingsystem.model.data.User;

import java.util.List;

public class UserRepositoryImpl extends UserRepository{

    private FirebaseAPI<User> service;

    public UserRepositoryImpl(FirebaseAPI<User> service) {
        this.service = service;
    }

    @Override
    public LiveData<List<User>> getAll(String key) {
        return service.getAll(key);
    }

    @Override
    public LiveData<User> getById(String id, String key) {
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
    public void write(User obj, String key) {
        service.write(obj, key);
    }
}
