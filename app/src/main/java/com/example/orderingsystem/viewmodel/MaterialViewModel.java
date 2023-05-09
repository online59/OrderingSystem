package com.example.orderingsystem.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.orderingsystem.model.data.Material;
import com.example.orderingsystem.model.repository.MaterialRepository;
import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.lifecycle.HiltViewModel;

import javax.inject.Inject;
import java.util.List;

public class MaterialViewModel extends ViewModel {

    @Inject
    public MaterialRepository materialRepository;

    @Inject
    public MaterialViewModel(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public LiveData<List<Material>> getAll(String key) {
        return materialRepository.getAll(key);
    }

    public LiveData<Material> getById(String id, String key) {
        return materialRepository.getById(id, key);
    }

    public void removeAll(String key) {
        materialRepository.removeAll(key);
    }

    public void removeById(String id, String key) {
        materialRepository.removeById(id, key);
    }

    public void write(Material obj, String key) {
        materialRepository.write(obj, key);
    }
}
