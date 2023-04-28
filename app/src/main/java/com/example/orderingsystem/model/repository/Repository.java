package com.example.orderingsystem.model.repository;

import androidx.lifecycle.LiveData;
import com.example.orderingsystem.model.service.FirebaseAuthService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public abstract class Repository<T> {

    public abstract LiveData<List<T>> getAll(String key);

    public abstract LiveData<T> getById(int id, String key);

    public abstract void removeAll(String key);

    public abstract void removeById(int id, String key);

    public abstract void write(T obj, String key);


}
