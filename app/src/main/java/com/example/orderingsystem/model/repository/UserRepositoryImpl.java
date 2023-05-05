package com.example.orderingsystem.model.repository;

import androidx.lifecycle.LiveData;
import com.example.orderingsystem.model.api.FirebaseAPI;
import com.example.orderingsystem.model.data.User;

import javax.inject.Inject;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    @Inject
    public FirebaseAPI<User> userService;

    @Inject
    public UserRepositoryImpl(FirebaseAPI<User> userService) {
        this.userService = userService;
    }

    @Override
    public LiveData<List<User>> getAll(String key) {
        return userService.getAll(key);
    }

    @Override
    public LiveData<User> getById(String id, String key) {
        return userService.getById(id, key);
    }

    @Override
    public void removeAll(String key) {
        userService.removeAll(key);
    }

    @Override
    public void removeById(String id, String key) {
        userService.removeById(id, key);
    }

    @Override
    public void write(User obj, String key) {
        userService.write(obj, key);
    }

    @Override
    public void update(User obj, String key) {
        userService.update(obj, key);
    }
}
