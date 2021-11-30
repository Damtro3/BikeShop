package com.foodsoft.bikeshop.Interface;

import android.content.Context;

import com.foodsoft.bikeshop.Model.BikeModel;
import com.foodsoft.bikeshop.ViewModel.SharedViewModel;
import com.foodsoft.bikeshop.Views.Adapter.ListViewAdapter;

import java.util.ArrayList;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Component;

@Component
public interface IListViewAdapter {


    ListViewAdapter getComponent();

    @Component.Builder
    interface BuilderData
    {
        @BindsInstance
        BuilderData context(@Named("context") Context context);

        @BindsInstance
        BuilderData textViewResourceId(@Named("textViewResourceId") int textViewResourceId);

        @BindsInstance
        BuilderData records(@Named("records") ArrayList<BikeModel> records);

        @BindsInstance
        BuilderData viewModel (@Named("viewModel") SharedViewModel viewModel );

        IListViewAdapter buildData();
    }
}
