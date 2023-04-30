package com.example.orderingsystem.model.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public abstract class Ordering {

    @SerializedName("order_id")
    private String orderId;
    @SerializedName("item_id")
    private String itemId;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("number_order")
    private int numberItemOrdered;
    @SerializedName("price")
    private float price;

    public float getTotalPrice() {
        return numberItemOrdered * price;
    }
}
