package com.example.orderingsystem.model.repository;

import androidx.lifecycle.LiveData;
import com.example.orderingsystem.model.data.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public abstract class AuthRepository {


    public abstract Task<AuthResult> signInWithEmailPassword(String email, String password);

    public abstract Task<AuthResult> createUserWithEmailPassword(String email, String password);

    public abstract FirebaseUser getCurrentUser();

    public abstract void signOut();
}
