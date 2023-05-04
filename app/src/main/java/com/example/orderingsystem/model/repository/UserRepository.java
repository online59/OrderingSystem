package com.example.orderingsystem.model.repository;

import androidx.lifecycle.LiveData;
import com.example.orderingsystem.model.data.User;

import java.util.List;

public interface UserRepository {

    LiveData<List<User>> getAll(String key);

    LiveData<User> getById(String id, String key);

    void removeAll(String key);

    void removeById(String id, String key);

    void write(User obj, String key);

    void update(User obj, String key);
}
