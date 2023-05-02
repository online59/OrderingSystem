package com.example.orderingsystem.di.factory;

import com.example.orderingsystem.model.repository.AuthRepository;
import com.example.orderingsystem.viewmodel.AuthViewModel;

public class AuthViewModelFactory implements Factory{

    private final AuthRepository authRepository;

    public AuthViewModelFactory(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public AuthViewModel create() {
        return new AuthViewModel(authRepository);
    }
}
