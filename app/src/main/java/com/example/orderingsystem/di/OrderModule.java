package com.example.orderingsystem.di;

import com.example.orderingsystem.model.api.FirebaseAPI;
import com.example.orderingsystem.model.data.Order;
import com.example.orderingsystem.model.repository.OrderRepository;
import com.example.orderingsystem.model.repository.OrderRepositoryImpl;
import com.example.orderingsystem.model.service.FirebaseOrderService;
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
@InstallIn(ViewModelComponent.class)
public class OrderModule {

//    @Binds
//    public abstract FirebaseAPI<Order> bindOrderAPI(FirebaseOrderService orderService);
//
//    @Binds
//    public abstract OrderRepository bindOrderRepository(OrderRepositoryImpl orderRepository);


    @Provides
    @ViewModelScoped
    public OrderRepository provideOrderRepository(FirebaseOrderService orderService) {
        return new OrderRepositoryImpl(orderService);
    }
}
