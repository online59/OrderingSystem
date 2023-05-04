package com.example.orderingsystem.di;

import com.example.orderingsystem.model.repository.AuthRepository;
import com.example.orderingsystem.model.repository.AuthRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseAuthService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

import javax.inject.Singleton;


@Module
@InstallIn(SingletonComponent.class)
public class AppModule {


    @Provides
    @Singleton
    public DatabaseReference provideDatabaseReference() {
        return FirebaseDatabase.getInstance().getReference();
    }

    @Provides
    @Singleton
    public AuthRepository provideAuthRepository(FirebaseAuthService authService) {
        return new AuthRepositoryImpl(authService);
    }

    @Provides
    @Singleton
    public FirebaseAuthService provideAuthService() {
        return new FirebaseAuthService();
    }
}
