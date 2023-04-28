package com.example.orderingsystem.model.repository;

import androidx.lifecycle.LiveData;
import com.example.orderingsystem.model.service.FirebaseAuthService;
import com.google.firebase.auth.FirebaseUser;

public class AuthRepositoryImpl extends AuthRepository{

    private final FirebaseAuthService authService;

    public AuthRepositoryImpl(FirebaseAuthService authService) {
        this.authService = authService;
    }

    @Override
    public void signInWithEmailPassword(String email, String password) {
        authService.signInWithEmailPassword(email, password);
    }

    @Override
    public void createUserWithEmailPassword(String email, String password) {
        authService.createUserWithEmailPassword(email, password);
    }

    @Override
    public LiveData<FirebaseUser> getCurrentUser() {
        return authService.getCurrentUser();
    }
}
