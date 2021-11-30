package com.foodsoft.bikeshop.Views.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import com.foodsoft.bikeshop.Interface.DaggerIStorageService;
import com.foodsoft.bikeshop.Interface.IStorageService;
import com.foodsoft.bikeshop.R;
import com.foodsoft.bikeshop.Views.Fragment.FragmentBikeList;

public class MainActivity extends AppCompatActivity {
    Fragment fragmentBikeList;

    private IStorageService storageService = DaggerIStorageService.builder().buildData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragmentsToLayout();
    }

    private void addFragmentsToLayout()
    {
        fragmentBikeList = new FragmentBikeList();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_bikeList, fragmentBikeList);
        transaction.addToBackStack(null);
        transaction.commit();


        /*
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_bikeList, new FragmentBikeList())
                .commit();*/
    }


    @Override
    public void onBackPressed() {

        if(storageService.getComponent().getPropertyInt(this,"currentFragmentDisplay", 1)==2) {
            FragmentBikeList fragmentBikeList= new FragmentBikeList();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_bikeList, fragmentBikeList);
            transaction.commit();
        }
        else
        {
            super.onBackPressed();
        }


    }
}