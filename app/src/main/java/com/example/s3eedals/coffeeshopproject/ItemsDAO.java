package com.example.s3eedals.coffeeshopproject;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ItemsDAO {

    @Insert
    void insert(Items coffee);

    @Query("SELECT * FROM coffee")
    List<Items> getAllCoffee();

    @Query("SELECT * FROM coffee WHERE food_item=:foodItem")
    Items getCoffee(String foodItem);

    @Update
    int updateCoffee(Items coffee);

    @Delete
    int deleteCoffee(Items coffee);

    @Query("DELETE FROM coffee WHERE food_item=:foodItem")
    int deleteByFoodItem(String foodItem);
}
