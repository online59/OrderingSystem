package com.example.orderingsystem.di;

import com.example.orderingsystem.model.api.FirebaseAPI;
import com.example.orderingsystem.model.data.User;
import com.example.orderingsystem.model.repository.UserRepository;
import com.example.orderingsystem.model.repository.UserRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseUserService;
import com.google.firebase.database.DatabaseReference;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
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

//    @Provides
//    @ViewModelScoped
//    public FirebaseAPI<User> provideUseAPI(DatabaseReference reference) {
//        return new FirebaseUserService(reference);
//    }
//
//    @Provides
//    @ViewModelScoped
//    public UserRepository provideUserRepository(FirebaseUserService userService) {
//        return new UserRepositoryImpl(userService);
//    }
}
