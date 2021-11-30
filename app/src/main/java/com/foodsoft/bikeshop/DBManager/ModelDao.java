package com.foodsoft.bikeshop.DBManager;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.foodsoft.bikeshop.Model.BikeModel;

import java.util.List;

@Dao
public interface ModelDao {

        @Query("SELECT * FROM bikeModel")
        List<BikeModel> getAll();

        @Query("DELETE FROM bikeModel")
        void deleteAll();

        @Insert
        void insertAll(BikeModel... bike);
}
