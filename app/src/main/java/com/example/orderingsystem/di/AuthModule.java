package com.example.orderingsystem.di;

import com.example.orderingsystem.model.repository.AuthRepository;
import com.example.orderingsystem.model.repository.AuthRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseAuthService;
import com.google.firebase.auth.FirebaseAuth;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.scopes.ActivityScoped;
import dagger.hilt.android.scopes.ViewModelScoped;

@Module
@InstallIn(ActivityComponent.class)
public abstract class AuthModule {

    @Binds
    @ActivityScoped
    public abstract AuthRepository bindAuthRepository(AuthRepositoryImpl authRepository);
}
