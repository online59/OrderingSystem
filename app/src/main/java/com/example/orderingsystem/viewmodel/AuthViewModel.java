package com.example.orderingsystem.viewmodel;

import androidx.lifecycle.ViewModel;
import com.example.orderingsystem.model.repository.AuthRepository;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import dagger.hilt.android.lifecycle.HiltViewModel;

import javax.inject.Inject;

public class AuthViewModel extends ViewModel {

    @Inject
    public AuthRepository authRepository;

    @Inject
    public AuthViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }


    public Task<AuthResult> signInWithEmailPassword(String email, String password) {
        return authRepository.signInWithEmailPassword(email, password);
    }


    public Task<AuthResult> createUserWithEmailPassword(String email, String password) {
        return authRepository.createUserWithEmailPassword(email, password);
    }


    public FirebaseUser getCurrentUser() {
        return authRepository.getCurrentUser();
    }

    public void signOut() {
        authRepository.signOut();
    }
}
