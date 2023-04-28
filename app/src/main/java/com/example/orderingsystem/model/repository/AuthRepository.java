package com.example.orderingsystem.model.repository;

import androidx.lifecycle.LiveData;
import com.example.orderingsystem.model.data.User;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public abstract class AuthRepository {


    public abstract void signInWithEmailPassword(String email, String password);

    public abstract void createUserWithEmailPassword(String email, String password);

    public abstract LiveData<FirebaseUser> getCurrentUser();

    public abstract void write(User user, String key);
}
