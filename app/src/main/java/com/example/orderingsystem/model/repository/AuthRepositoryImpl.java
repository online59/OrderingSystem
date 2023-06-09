package com.example.orderingsystem.model.repository;

import androidx.lifecycle.LiveData;
import com.example.orderingsystem.model.data.User;
import com.example.orderingsystem.model.service.FirebaseAuthService;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import dagger.hilt.android.AndroidEntryPoint;

import javax.inject.Inject;

public class AuthRepositoryImpl implements AuthRepository{


    @Inject
    public FirebaseAuthService authService;

    @Inject
    public AuthRepositoryImpl(FirebaseAuthService authService) {
        this.authService = authService;
    }

    @Override
    public Task<AuthResult> signInWithEmailPassword(String email, String password) {
        return authService.signInWithEmailPassword(email, password);
    }

    @Override
    public Task<AuthResult> createUserWithEmailPassword(String email, String password) {
        return authService.createUserWithEmailPassword(email, password);
    }

    @Override
    public FirebaseUser getCurrentUser() {
        return authService.getCurrentUser();
    }

    @Override
    public void signOut() {
        authService.signOut();
    }
}
