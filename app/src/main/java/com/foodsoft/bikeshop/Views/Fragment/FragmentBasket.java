package com.foodsoft.bikeshop.Views.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.foodsoft.bikeshop.Interface.DaggerIListViewAdapter;
import com.foodsoft.bikeshop.Interface.DaggerIStorageService;
import com.foodsoft.bikeshop.Interface.IListViewAdapter;
import com.foodsoft.bikeshop.Interface.IStorageService;
import com.foodsoft.bikeshop.Model.BikeModel;
import com.foodsoft.bikeshop.R;
import com.foodsoft.bikeshop.ViewModel.SharedViewModel;
import java.util.ArrayList;
import java.util.List;

public class FragmentBasket  extends Fragment  {
    private SharedViewModel viewModel;
    private IStorageService storageService = DaggerIStorageService.builder().buildData();

    ListView bikeList;
    FragmentBasket instance;
    IListViewAdapter adapter;
    TextView total;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_basket, container, false);
        bikeList = v.findViewById(R.id.basketListView);
        total = v.findViewById(R.id.total);
        instance =this;
        storageService.getComponent().setPropertyInt(v.getContext(),"currentFragmentDisplay",2);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        liveDataChanger();
    }

    public  void liveDataChanger()
    {
        viewModel.getBikeBuyList().observe(getViewLifecycleOwner(), new Observer<List<BikeModel>>() {
            @Override
            public void onChanged(List<BikeModel> value) {
                adapter = DaggerIListViewAdapter.builder().context(getContext()).records((ArrayList) value).textViewResourceId(R.layout.adapter_basket).viewModel(viewModel).buildData();
                bikeList.setAdapter(adapter.getComponent());
                total.setText(String.valueOf(sumValueOfPrice(value)));
            }
        });

    }
    private int sumValueOfPrice(List<BikeModel> value)
    {
        int total=0;
        for(BikeModel model: value)
        {
            total+= model.getPrice();
        }
        return  total;

    }
}