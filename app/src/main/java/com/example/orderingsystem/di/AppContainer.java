package com.example.orderingsystem.di;

import com.example.orderingsystem.di.factory.AuthViewModelFactory;
import com.example.orderingsystem.di.factory.MainViewModelFactory;
import com.example.orderingsystem.model.data.Material;
import com.example.orderingsystem.model.data.Order;
import com.example.orderingsystem.model.data.User;
import com.example.orderingsystem.model.repository.*;
import com.example.orderingsystem.model.service.FirebaseAuthService;
import com.example.orderingsystem.model.service.FirebaseMaterialService;
import com.example.orderingsystem.model.service.FirebaseOrderService;
import com.example.orderingsystem.model.service.FirebaseUserService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AppContainer {

    // Services dependency
    private final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    // Services
    private final FirebaseAuthService authService = new FirebaseAuthService();
    private final FirebaseMaterialService materialService = new FirebaseMaterialService(reference);
    private final FirebaseOrderService orderService = new FirebaseOrderService(reference);
    private final FirebaseUserService userService = new FirebaseUserService(reference);

    // Provided dependency
    public AuthRepositoryImpl authRepository = new AuthRepositoryImpl(authService);
    public MainRepository<Material> materialRepository = new MaterialRepositoryImpl(materialService);
    public MainRepository<Order> orderRepository = new OrderRepositoryImpl(orderService);
    public MainRepository<User> userRepository = new UserRepositoryImpl(userService);

    // View Model Factory
    public AuthViewModelFactory authViewModelFactory = new AuthViewModelFactory(authRepository);
    public MainViewModelFactory<Material> materialViewModelFactory = new MainViewModelFactory<>(materialRepository);
    public MainViewModelFactory<Order> orderViewModelFactory = new MainViewModelFactory<>(orderRepository);
    public MainViewModelFactory<User> userViewModelFactory = new MainViewModelFactory<>(userRepository);
}
