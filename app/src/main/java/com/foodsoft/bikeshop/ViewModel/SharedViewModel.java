package com.foodsoft.bikeshop.ViewModel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.foodsoft.bikeshop.DBManager.AppDatabase;
import com.foodsoft.bikeshop.Model.BikeModel;
import com.foodsoft.bikeshop.R;
import com.foodsoft.bikeshop.Views.Fragment.FragmentBasket;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

public class SharedViewModel  extends ViewModel implements ViewModelProvider.Factory{

    Context context;
    AppDatabase db;
    FragmentManager fragmentManager;
    private MutableLiveData<List<BikeModel>> buyList = new MutableLiveData<>();

    public LiveData<List<BikeModel>> getBikeBuyList() {
        return buyList;
    }

    public void setBikeBuyList(List<BikeModel> input) {
        buyList.setValue(input);
    }

    private MutableLiveData<List<BikeModel>> bikeModelList = new MutableLiveData<>();

    public LiveData<List<BikeModel>> getBikeModelList() {
        return bikeModelList;
    }

    public void setBikeModelList(List<BikeModel> input) {
        bikeModelList.setValue(input);
    }

    @Inject
    public SharedViewModel(@Named("context") Context context,@Named("db") AppDatabase db,@Named("fragmentManager") FragmentManager fragmentManager) {
        this.context = context;
        this.db = db;
        this.fragmentManager = fragmentManager;
        createBikeList();
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SharedViewModel(context,db,fragmentManager);
    }
    public void getBuyListFromDatabase()
    {
        List<BikeModel> tempBuyList = db.bikeDao().getAll();
        if(tempBuyList.size()>0)
        {
            setBikeBuyList(tempBuyList);
        }
    }

    public void createBikeList()
    {
        List<BikeModel>bikeModelList = new ArrayList<>();
        Bitmap bitmap1 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.rower1);
        Bitmap bitmap2 = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.rower2);

        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
        ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
        bitmap2.compress(Bitmap.CompressFormat.JPEG,80,stream1);
        byte[] byteArray2 = stream1.toByteArray();
        bitmap1.compress(Bitmap.CompressFormat.JPEG,80,stream2);
        byte[] byteArray1 = stream2.toByteArray();
        bikeModelList.add(new BikeModel(0,123123,"Górski rower","Czarno-limonkowy",5000, "Górski","Romet",byteArray1));
        bikeModelList.add(new BikeModel(0,124125,"Szosowy rower","Niebieski",12000, "Szosowy","Romet",byteArray2));
        setBikeModelList(bikeModelList);
    }

    public void addBuyListToDatabase()
    {
        int i =1;
        db.bikeDao().deleteAll();
        for(BikeModel model: buyList.getValue())
        {
            model.setDbId(i);
            db.bikeDao().insertAll(model);
            i++;
        }
    }

    public void openBasketFragment()
    {
        FragmentBasket fragmentBasket = new FragmentBasket();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_bikeList, fragmentBasket);
        fragmentTransaction.commit();
    }

}

