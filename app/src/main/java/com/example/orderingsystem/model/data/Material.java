package com.example.orderingsystem.model.data;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Material {

    @SerializedName("item_id")
    private String itemId;
    @SerializedName("item_name")
    private String itemName;
    @SerializedName("produce_year")
    private int produceYear;
    @SerializedName("description")
    private String description;
    @SerializedName("remaining")
    private int remaining;
    @SerializedName("price")
    private float price;
}
