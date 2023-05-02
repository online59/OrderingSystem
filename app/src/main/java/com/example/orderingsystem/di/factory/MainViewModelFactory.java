package com.example.orderingsystem.di.factory;

import com.example.orderingsystem.model.repository.MainRepository;
import com.example.orderingsystem.viewmodel.MainViewModel;

public class MainViewModelFactory<T> implements Factory{

    private final MainRepository<T> mainRepository;

    public MainViewModelFactory(MainRepository<T> mainRepository) {
        this.mainRepository = mainRepository;
    }

    @Override
    public MainViewModel<T> create() {
        return new MainViewModel<>(mainRepository);
    }
}
