package com.foodsoft.bikeshop.ViewModel;

import androidx.core.util.Pair;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.foodsoft.bikeshop.Model.BikeModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SharedViewModel  extends ViewModel {

    private MutableLiveData<List<BikeModel>> bikeModelList = new MutableLiveData<>();

    public LiveData<List<BikeModel>> getBikeBuyList() {
        return bikeModelList;
    }

    public void setBikeBuyList(List<BikeModel> input) {
        bikeModelList.setValue(input);
    }
}

