package com.foodsoft.bikeshop.Interface;

import com.foodsoft.bikeshop.Service.StorageService;

import dagger.Component;

@Component
public interface IStorageService {

    StorageService getComponent();
    @Component.Builder
    interface BuilderData
    {
        IStorageService buildData();
    }
}
