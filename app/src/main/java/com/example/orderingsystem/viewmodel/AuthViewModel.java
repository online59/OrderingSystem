package com.example.orderingsystem.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.orderingsystem.model.repository.AuthRepository;
import com.google.firebase.auth.FirebaseUser;

public class AuthViewModel extends ViewModel {

    private final AuthRepository authRepository;

    public AuthViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }


    public void signInWithEmailPassword(String email, String password) {
        authRepository.signInWithEmailPassword(email, password);
    }


    public void createUserWithEmailPassword(String email, String password) {
        authRepository.createUserWithEmailPassword(email, password);
    }


    public LiveData<FirebaseUser> getCurrentUser() {
        return authRepository.getCurrentUser();
    }
}
