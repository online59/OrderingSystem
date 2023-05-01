package com.example.orderingsystem.model.service;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.orderingsystem.model.api.FirebaseAPI;
import com.example.orderingsystem.model.data.Material;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseMaterialService implements FirebaseAPI<Material> {

    private final DatabaseReference reference;
    private final MutableLiveData<List<Material>> shopItemListMutable;
    private final MutableLiveData<Material> shopItemMutable;

    public FirebaseMaterialService(DatabaseReference reference) {
        this.reference = reference;
        shopItemListMutable = new MutableLiveData<>();
        shopItemMutable = new MutableLiveData<>();
    }

    @Override
    public LiveData<List<Material>> getAll(String key) {

        reference.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                List<Material> itemList = new ArrayList<>();
                for (DataSnapshot snap: snapshot.getChildren()) {
                    Material item = snap.getValue(Material.class);
                    itemList.add(item);
                }
                shopItemListMutable.postValue(itemList);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return shopItemListMutable;
    }

    @Override
    public LiveData<Material> getById(String id, String key) {

        reference.child(key).child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                shopItemMutable.postValue(snapshot.getValue(Material.class));
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return shopItemMutable;
    }

    @Override
    public void removeAll(String key) {
        reference.child(key).removeValue();
    }

    @Override
    public void removeById(String id, String key) {
        reference.child(key).child(id).removeValue();
    }

    @Override
    public void write(Material obj, String key) {
        reference.child(key).setValue(obj);
    }

    @Override
    public void update(Material obj, String key) {

        Map<String, Object> childUpdate = new HashMap<>();
        childUpdate.put(key, obj);

        reference.updateChildren(childUpdate);
    }
}
