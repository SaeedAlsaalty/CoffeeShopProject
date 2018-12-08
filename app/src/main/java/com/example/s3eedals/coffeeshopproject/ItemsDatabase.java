package com.example.s3eedals.coffeeshopproject;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Items.class}, version = 1)
public abstract class ItemsDatabase extends RoomDatabase {

    public abstract ItemsDAO itemsDAO();

    private static volatile ItemsDatabase INSTANCE;

    static ItemsDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ItemsDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ItemsDatabase.class, "CoffeeShopProject")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
