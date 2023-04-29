package com.example.orderingsystem.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.orderingsystem.model.data.User;
import com.example.orderingsystem.model.repository.AuthRepository;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class AuthViewModel extends ViewModel {

    private final AuthRepository authRepository;

    public AuthViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }


    public Task<AuthResult> signInWithEmailPassword(String email, String password) {
        return authRepository.signInWithEmailPassword(email, password);
    }


    public Task<AuthResult> createUserWithEmailPassword(String email, String password) {
        return authRepository.createUserWithEmailPassword(email, password);
    }


    public LiveData<FirebaseUser> getCurrentUser() {
        return authRepository.getCurrentUser();
    }

    public Task<Void> write(User user, String key) {
        return authRepository.write(user, key);
    }
}
