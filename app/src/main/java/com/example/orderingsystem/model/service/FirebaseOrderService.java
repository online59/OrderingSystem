package com.example.orderingsystem.model.service;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.orderingsystem.model.api.FirebaseAPI;
import com.example.orderingsystem.model.data.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseOrderService implements FirebaseAPI<Order> {

    private final DatabaseReference reference;
    private final MutableLiveData<List<Order>> orderListMutable;
    private final MutableLiveData<Order> orderMutable;

    public FirebaseOrderService(DatabaseReference reference) {
        this.reference = reference;
        orderListMutable = new MutableLiveData<>();
        orderMutable = new MutableLiveData<>();
    }

    @Override
    public LiveData<List<Order>> getAll(String key) {

        reference.child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                List<Order> userList = new ArrayList<>();
                for (DataSnapshot snap: snapshot.getChildren()) {
                    userList.add(snap.getValue(Order.class));
                }
                orderListMutable.postValue(userList);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return orderListMutable;
    }

    @Override
    public LiveData<Order> getById(String id, String key) {

        reference.child(key).child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                orderMutable.postValue(snapshot.getValue(Order.class));
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

        return orderMutable;
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
    public void write(Order obj, String key) {
        reference.child(key).setValue(obj);
    }

    @Override
    public void update(Order obj, String key) {

        Map<String, Object> childUpdate = new HashMap<>();
        childUpdate.put(key, obj);

        reference.updateChildren(childUpdate);
    }
}
