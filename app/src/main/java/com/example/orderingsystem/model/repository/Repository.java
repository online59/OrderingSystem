package com.example.orderingsystem.model.repository;

import androidx.lifecycle.LiveData;
import com.example.orderingsystem.model.data.Item;
import com.example.orderingsystem.model.service.FirebaseAuthService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public abstract class Repository {

    public abstract LiveData<List<Item>> getAll(String key);

    public abstract LiveData<Item> getById(int id, String key);

    public abstract void removeAll(String key);

    public abstract void removeById(int id, String key);

    public abstract void write(Item obj, String key);


}
