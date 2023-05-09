package com.example.orderingsystem.di;

import com.example.orderingsystem.model.repository.AuthRepository;
import com.example.orderingsystem.model.repository.AuthRepositoryImpl;
import com.example.orderingsystem.model.repository.MaterialRepository;
import com.example.orderingsystem.model.repository.MaterialRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseAuthService;
import com.example.orderingsystem.model.service.FirebaseMaterialService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.scopes.ActivityScoped;
import dagger.hilt.components.SingletonComponent;

import javax.inject.Singleton;


@Module
@InstallIn(ActivityComponent.class)
public class AppModule {

    @Provides
    @ActivityScoped
    public FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    @ActivityScoped
    public DatabaseReference provideDatabaseReference() {
        return FirebaseDatabase.getInstance().getReference();
    }
}
