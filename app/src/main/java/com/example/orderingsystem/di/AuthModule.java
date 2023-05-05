package com.example.orderingsystem.di;

import com.example.orderingsystem.model.repository.AuthRepository;
import com.example.orderingsystem.model.repository.AuthRepositoryImpl;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;

@Module
@InstallIn(ActivityComponent.class)
public abstract class AuthModule {

    @Binds
    public abstract AuthRepository bindAuthRepository(AuthRepositoryImpl authRepository);
}
