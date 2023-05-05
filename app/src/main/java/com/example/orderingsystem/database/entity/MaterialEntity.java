package com.example.orderingsystem.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import lombok.Data;

@Entity
@Data
public class MaterialEntity {

    public MaterialEntity() {
    }

    @PrimaryKey()
    @ColumnInfo(name = "item_id")
    @NonNull
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
