package com.example.orderingsystem.di;

import com.example.orderingsystem.model.api.FirebaseAPI;
import com.example.orderingsystem.model.data.Material;
import com.example.orderingsystem.model.repository.MaterialRepository;
import com.example.orderingsystem.model.repository.MaterialRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseMaterialService;
import com.google.firebase.database.DatabaseReference;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.scopes.ActivityScoped;
import dagger.hilt.android.scopes.ViewModelScoped;
import dagger.hilt.components.SingletonComponent;

import javax.inject.Singleton;

@Module
@InstallIn(ActivityComponent.class)
public abstract class MaterialModule {

    @Binds
    @ActivityScoped
    public abstract FirebaseAPI<Material> bindMaterialAPI(FirebaseMaterialService materialService);

    @Binds
    @ActivityScoped
    public abstract MaterialRepository bindMaterialRepository(MaterialRepositoryImpl materialRepository);
}
