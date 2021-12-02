package com.foodsoft.bikeshop.Views.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.foodsoft.bikeshop.DBManager.AppDatabase;
import com.foodsoft.bikeshop.Interface.DaggerIRecyclerViewAdapter;
import com.foodsoft.bikeshop.Interface.DaggerIStorageService;
import com.foodsoft.bikeshop.Interface.IRecyclerViewAdapter;
import com.foodsoft.bikeshop.Interface.IStorageService;
import com.foodsoft.bikeshop.Model.BikeModel;
import com.foodsoft.bikeshop.R;
import com.foodsoft.bikeshop.ViewModel.SharedViewModel;

import java.util.ArrayList;
import java.util.List;

public class FragmentBikeList extends Fragment  {
    private SharedViewModel viewModel;
    private IStorageService storageService = DaggerIStorageService.builder().buildData();
    private Button startBasketFragment;
    private RecyclerView bikeList;
    private FragmentBikeList instance;
    private List<BikeModel> buylList = new ArrayList<>();
    private  AppDatabase db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bike_list, container, false);
        startBasketFragment = v.findViewById(R.id.startBasketFragment);
        bikeList =(RecyclerView) v.findViewById(R.id.bikeList);
        bikeList.setLayoutManager(new LinearLayoutManager(getContext()));
        instance = this;
        storageService.getComponent().setPropertyInt(v.getContext(),"currentFragmentDisplay",1);
        startBasketFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(buylList.size()>0)
                {
                    viewModel.openBasketFragment();
                }
                else
                {
                    Toast.makeText(getContext(), "Koszyk jest pusty", Toast.LENGTH_SHORT).show();
                }
            }
        });
         db = AppDatabase.getDbInstance(getContext());
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       // viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        viewModel = ViewModelProviders.of(getActivity(), new SharedViewModel(getContext(), db,getFragmentManager())).get(SharedViewModel.class);
        viewModel.getBuyListFromDatabase();
        liveDataChanger();
    }

    public void uiChanger(List<BikeModel> value) {
        IRecyclerViewAdapter adapter = DaggerIRecyclerViewAdapter.builder().records((ArrayList)value).viewModel(viewModel).context(getContext()).buyList(buylList).buildData();
        adapter.getComponent().notifyDataSetChanged();
        bikeList.setAdapter(adapter.getComponent());
    }
    public void liveDataChanger() {
        viewModel.getBikeBuyList().observe(getViewLifecycleOwner(), new Observer<List<BikeModel>>() {
            @Override
            public void onChanged(List<BikeModel> value) {
                startBasketFragment.setText("Liczba produktów w koszyku "+value.size() );
z                buylList=value;
                viewModel.addBuyListToDatabase();
            }
        });

        viewModel.getBikeModelList().observe(getViewLifecycleOwner(), new Observer<List<BikeModel>>() {
            @Override
            public void onChanged(List<BikeModel> value) {
               uiChanger(value);
            }
        });
    }

}