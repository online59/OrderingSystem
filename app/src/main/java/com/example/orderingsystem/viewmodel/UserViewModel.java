package com.example.orderingsystem.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.orderingsystem.model.data.User;
import com.example.orderingsystem.model.repository.UserRepository;

import java.util.List;


public class UserViewModel extends ViewModel {

    private final UserRepository userRepository;

    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LiveData<List<User>> getAll(String key) {
        return userRepository.getAll(key);
    }

    public LiveData<User> getById(String id, String key) {
        return userRepository.getById(id, key);
    }

    public void removeAll(String key) {
        userRepository.removeAll(key);
    }

    public void removeById(String id, String key) {
        userRepository.removeById(id, key);
    }

    public void write(User obj, String key) {
        userRepository.write(obj, key);
    }
}
