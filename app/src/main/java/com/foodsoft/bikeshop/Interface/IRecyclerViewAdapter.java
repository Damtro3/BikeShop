package com.foodsoft.bikeshop.Interface;

import android.content.Context;

import com.foodsoft.bikeshop.Model.BikeModel;
import com.foodsoft.bikeshop.Service.StorageService;
import com.foodsoft.bikeshop.ViewModel.SharedViewModel;
import com.foodsoft.bikeshop.Views.Adapter.RecyclerViewAdapter;

import java.util.ArrayList;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Component;

@Component
public interface IRecyclerViewAdapter {

    RecyclerViewAdapter getComponent();
    @Component.Builder
    interface BuilderData
    {
        @BindsInstance
        BuilderData records (@Named("records") ArrayList<BikeModel> recordsm );

        @BindsInstance
        BuilderData viewModel (@Named("viewModel") SharedViewModel viewModel );

        @BindsInstance
        BuilderData context ( @Named("Context") Context context );

        IRecyclerViewAdapter buildData();
    }
}
