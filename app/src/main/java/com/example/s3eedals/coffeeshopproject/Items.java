package com.example.s3eedals.coffeeshopproject;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "coffee")
public class Items {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "food_item")
    private String foodItem;

    @NonNull
    @ColumnInfo(name = "type")
    private String type;

    @NonNull
    @ColumnInfo(name = "price")
    private int price;

    public Items(String foodItem, String type, int price) {
        this.foodItem = foodItem;
        this.type = type;
        this.price = price;
    }

    public void setFoodItem(String foodItem) {
        this.foodItem = foodItem;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFoodItem() {
        return foodItem;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }


}
