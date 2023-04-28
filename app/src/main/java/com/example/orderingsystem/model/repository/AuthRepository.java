package com.example.orderingsystem.model.repository;

import androidx.lifecycle.LiveData;
import com.google.firebase.auth.FirebaseUser;

public abstract class AuthRepository {


    public abstract void signInWithEmailPassword(String email, String password);

    public abstract void createUserWithEmailPassword(String email, String password);

    public abstract LiveData<FirebaseUser> getCurrentUser();
}
