package com.example.orderingsystem.model.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public abstract class Order {

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

    public float getTotalPrice() {
        return quantity * price;
    }
}
