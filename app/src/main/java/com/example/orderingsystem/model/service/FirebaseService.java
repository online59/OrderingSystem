package com.example.orderingsystem.model.service;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.orderingsystem.model.api.FirebaseAPI;
import com.example.orderingsystem.model.data.ShopItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FirebaseService implements FirebaseAPI<ShopItem> {

    private final DatabaseReference reference;
    private final MutableLiveData<List<ShopItem>> shopItemListMutable;
    private final MutableLiveData<ShopItem> shopItemMutable;

    public FirebaseService(DatabaseReference reference) {
        this.reference = reference;
        shopItemListMutable = new MutableLiveData<>();
        shopItemMutable = new MutableLiveData<>();
    }

    @Override
    public LiveData<List<ShopItem>> getAll(String key) {

        reference.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                List<ShopItem> itemList = new ArrayList<>();
                for (DataSnapshot snap: snapshot.getChildren()) {
                    ShopItem item = snap.getValue(ShopItem.class);
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
    public LiveData<ShopItem> getById(int id, String key) {

        reference.child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                shopItemMutable.postValue(snapshot.getValue(ShopItem.class));
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
    public void removeById(int id, String key) {
        reference.child(key).child(String.valueOf(id)).removeValue();
    }

    @Override
    public void write(ShopItem obj, String key) {
        reference.child(key).setValue(obj);
    }
}
