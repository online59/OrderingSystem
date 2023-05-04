package com.example.orderingsystem.model.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.firebase.database.Exclude;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NonNull;

@Data
public class Order {

    @SerializedName("order_id")
    private String orderId;
    @SerializedName("material_id")
    private String itemId;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("material_name")
    private String itemName;
    @SerializedName("number_order")
    private int quantity;
    @SerializedName("price")
    private float price;
    @SerializedName("date")
    private String purchaseDate;

    public float getTotalPrice() {
        return quantity * price;
    }
}
