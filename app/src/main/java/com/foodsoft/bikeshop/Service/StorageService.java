package com.foodsoft.bikeshop.Service;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.security.GeneralSecurityException;
import java.util.List;

import javax.inject.Inject;

public class StorageService {

    @Inject
    StorageService()
    {}

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    public static final String SharedPreferencesVault = "mBikeShopPreferences";


    public  void setPropertyDate(Context context, String key, Long value)
    {
        sharedPref = context.getSharedPreferences(SharedPreferencesVault,Context.MODE_PRIVATE);
        context.getSharedPreferences("a",Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.putLong(key,value);
        editor.commit();
    }

    public Long getPropertyDate (Context context, String key , Long value )
    {
        sharedPref = context.getSharedPreferences(SharedPreferencesVault,Context.MODE_PRIVATE);
        Long result = sharedPref.getLong(key, value);
        return result;
    }
    public void setPropertyInt(Context context, String key, int value)
    {
        sharedPref = context.getSharedPreferences(SharedPreferencesVault,Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.putInt(key,value);
        editor.commit();
    }

    public int getPropertyInt (Context context, String key , int value )
    {
        sharedPref = context.getSharedPreferences(SharedPreferencesVault,Context.MODE_PRIVATE);
        int result = sharedPref.getInt(key, value);
        return result;
    }

    public void setPropertyString(Context context, String key, String value)
    {
        sharedPref = context.getSharedPreferences(SharedPreferencesVault,Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.putString(key,value);
        editor.commit();
    }
    public String getPropertyString (Context context, String key , String value )
    {
        sharedPref = context.getSharedPreferences(SharedPreferencesVault,Context.MODE_PRIVATE);
        String result = sharedPref.getString(key, value);
        return result;
    }
    public void setPropertyBool(Context context, String key, Boolean value)
    {
        sharedPref = context.getSharedPreferences(SharedPreferencesVault,Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.putBoolean(key,value);
        editor.commit();

    }

    public boolean getPropertyBool(Context context, String key , boolean value )
    {
        sharedPref = context.getSharedPreferences(SharedPreferencesVault,Context.MODE_PRIVATE);
        boolean result = sharedPref.getBoolean(key,value);
        return result;
    }
    public void setPropertyLong(Context context, String key, Long value)
    {
        sharedPref = context.getSharedPreferences(SharedPreferencesVault,Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        editor.putLong(key,value);
        editor.commit();
    }


    public long getPropertyLong (Context context, String key , long value )
    {
        sharedPref = context.getSharedPreferences(SharedPreferencesVault,Context.MODE_PRIVATE);
        long result = sharedPref.getLong(key, value);
        return result;
    }

}
