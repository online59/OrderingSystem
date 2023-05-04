package com.example.orderingsystem.model.repository;

import androidx.lifecycle.LiveData;
import com.example.orderingsystem.model.data.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public interface AuthRepository {


    Task<AuthResult> signInWithEmailPassword(String email, String password);

    Task<AuthResult> createUserWithEmailPassword(String email, String password);

    FirebaseUser getCurrentUser();

     void signOut();
}
