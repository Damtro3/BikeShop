package com.foodsoft.bikeshop.DBManager;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.foodsoft.bikeshop.Model.BikeModel;

@Database(entities = {BikeModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ModelDao bikeDao();


    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context) {

        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "db2")
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }
}