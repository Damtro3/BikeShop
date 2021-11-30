package com.foodsoft.bikeshop.Model;

import android.graphics.Bitmap;
import android.media.Image;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BikeModel {

    @PrimaryKey
    long dbId;
    @ColumnInfo(name = "id")
    long id;
    @ColumnInfo(name = "name")
    String name;
    @ColumnInfo(name = "color")
    String color;
    @ColumnInfo(name = "price")
    int price;
    @ColumnInfo(name = "model")
    String model;
    @ColumnInfo(name = "brand")
    String brand;
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] bitmap;

    public BikeModel(long dbId, long id, String name, String color, int price, String model, String brand, byte[] bitmap) {
        this.dbId = dbId;
        this.id = id;
        this.name = name;
        this.color = color;
        this.price = price;
        this.model = model;
        this.brand = brand;
        this.bitmap = bitmap;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public byte[] getBitmap() {
        return bitmap;
    }

    public void setBitmap(byte[] bitmap) {
        this.bitmap = bitmap;
    }

    public long getDbId() {
        return dbId;
    }

    public void setDbId(long dbId) {
        this.dbId = dbId;
    }
}
