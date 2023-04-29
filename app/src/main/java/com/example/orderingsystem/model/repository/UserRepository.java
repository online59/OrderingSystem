package com.example.orderingsystem.model.repository;

import androidx.lifecycle.LiveData;
import com.example.orderingsystem.model.data.User;

import java.util.List;

public abstract class UserRepository {

    public abstract LiveData<List<User>> getAll(String key);

    public abstract LiveData<User> getById(String id, String key);

    public abstract void removeAll(String key);

    public abstract void removeById(String id, String key);

    public abstract void write(User obj, String key);
}
