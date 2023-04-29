package com.example.orderingsystem.model.service;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.orderingsystem.model.data.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class FirebaseAuthService {
    private final FirebaseAuth firebaseAuth;

    public FirebaseAuthService() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public Task<AuthResult> signInWithEmailPassword(String email, String password) {
        return firebaseAuth.signInWithEmailAndPassword(email, password);
    }

    public Task<AuthResult> createUserWithEmailPassword(String email, String password) {
        return firebaseAuth.createUserWithEmailAndPassword(email, password);
    }

    public FirebaseUser getCurrentUser() {
        return firebaseAuth.getCurrentUser();
    }

    public void signOut() {
        firebaseAuth.signOut();
    }
}
