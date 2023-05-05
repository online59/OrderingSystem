package com.example.orderingsystem.model.service;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;


public class FirebaseAuthService {

    @Inject
    public FirebaseAuth firebaseAuth;

    @Inject
    public FirebaseAuthService() {
        this.firebaseAuth = FirebaseAuth.getInstance();
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
