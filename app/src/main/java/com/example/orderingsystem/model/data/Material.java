package com.example.orderingsystem.model.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Entity
@Data
public class Material {

    @PrimaryKey()
    @ColumnInfo(name = "item_id")
    private String itemId;
    @ColumnInfo(name = "item_name")
    private String itemName;
    @ColumnInfo(name = "produce_year")
    private int produceYear;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "remaining")
    private int remaining;
    @ColumnInfo(name = "price")
    private float price;
}
