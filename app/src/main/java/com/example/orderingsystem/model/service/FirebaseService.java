package com.example.orderingsystem.model.service;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.orderingsystem.model.api.FirebaseAPI;
import com.example.orderingsystem.model.data.Item;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FirebaseService implements FirebaseAPI<Item> {

    private final DatabaseReference reference;
    private final MutableLiveData<List<Item>> itemListMutable;
    private final MutableLiveData<Item> itemMutable;

    public FirebaseService(DatabaseReference reference) {
        this.reference = reference;
        itemListMutable = new MutableLiveData<>();
        itemMutable = new MutableLiveData<>();
    }

    @Override
    public LiveData<List<Item>> getAll(String key) {

        reference.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                List<Item> itemList = new ArrayList<>();
                for (DataSnapshot snap: snapshot.getChildren()) {
                    Item item = snap.getValue(Item.class);
                    itemList.add(item);
                }
                itemListMutable.postValue(itemList);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return itemListMutable;
    }

    @Override
    public LiveData<Item> getById(int id, String key) {

        reference.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                itemMutable.postValue(snapshot.getValue(Item.class));
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return itemMutable;
    }

    @Override
    public void removeAll(String key) {
        reference.child(key).removeValue();
    }

    @Override
    public void removeById(int id, String key) {
        reference.child(key).child(String.valueOf(id)).removeValue();
    }

    @Override
    public void write(Item obj, String key) {
        reference.child(key).setValue(obj);
    }
}
