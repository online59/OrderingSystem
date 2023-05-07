package com.example.orderingsystem.di;

import com.example.orderingsystem.model.api.FirebaseAPI;
import com.example.orderingsystem.model.data.User;
import com.example.orderingsystem.model.repository.UserRepository;
import com.example.orderingsystem.model.repository.UserRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseUserService;
import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.scopes.ViewModelScoped;

@Module
@InstallIn(ActivityComponent.class)
public abstract class UserModule {

    @Binds
    public abstract FirebaseAPI<User> bindUserAPI(FirebaseUserService userService);

    @Binds
    public abstract UserRepository bindUserRepository(UserRepositoryImpl userRepository);
}
