package com.example.orderingsystem.model.api;

import androidx.lifecycle.LiveData;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public interface FirebaseAPI<T> {

    LiveData<List<T>> getAll(String key);

    LiveData<T> getById(String id, String key);

    void removeAll(String key);

    void removeById(String id, String key);

    void write(T obj, String key);

    void update(T obj, String key);

}
