package com.example.orderingsystem.model.api;

import androidx.lifecycle.LiveData;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public interface FirebaseAPI<T> {

    public LiveData<List<T>> getAll(String key);

    public LiveData<T> getById(String id, String key);

    public void removeAll(String key);

    public void removeById(String id, String key);

    public void write(T obj, String key);
}
