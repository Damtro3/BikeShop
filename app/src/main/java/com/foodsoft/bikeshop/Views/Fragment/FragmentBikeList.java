package com.foodsoft.bikeshop.Views.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.foodsoft.bikeshop.DBManager.AppDatabase;
import com.foodsoft.bikeshop.DBManager.ModelDao;
import com.foodsoft.bikeshop.Interface.DaggerIRecyclerViewAdapter;
import com.foodsoft.bikeshop.Interface.DaggerIStorageService;
import com.foodsoft.bikeshop.Interface.IRecyclerViewAdapter;
import com.foodsoft.bikeshop.Interface.IStorageService;
import com.foodsoft.bikeshop.Model.BikeModel;
import com.foodsoft.bikeshop.R;
import com.foodsoft.bikeshop.ViewModel.SharedViewModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FragmentBikeList extends Fragment {
    private SharedViewModel viewModel;
    private IStorageService storageService = DaggerIStorageService.builder().buildData();
    private Button startBasketFragment;
    private RecyclerView bikeList;
    private FragmentBikeList instance;
    private List<BikeModel> bikeModelList = new ArrayList<>();
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
                    FragmentBasket fragmentBasket = new FragmentBasket();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_bikeList, fragmentBasket);
                    fragmentTransaction.commit();
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
        viewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
        checkRecordFromDatabase();
        uiChanger();
        liveDataChanger();
    }
    private void checkRecordFromDatabase()
    {
        List<BikeModel> tempBuyList = db.bikeDao().getAll();
        if(tempBuyList.size()>0)
        {
            buylList=tempBuyList;
            viewModel.setBikeBuyList(buylList);
        }
    }
    public void uiChanger() {
        createRecords();
        IRecyclerViewAdapter adapter = DaggerIRecyclerViewAdapter.builder().records((ArrayList)bikeModelList).viewModel(viewModel).context(getContext()).buildData();
        adapter.getComponent().notifyDataSetChanged();
        bikeList.setAdapter(adapter.getComponent());
    }
    private void createRecords()
    {
        Bitmap bitmap1 = BitmapFactory.decodeResource(getContext().getResources(),
                R.drawable.rower1);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getContext().getResources(),
                R.drawable.rower2);

        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
        ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
        bitmap2.compress(Bitmap.CompressFormat.JPEG,80,stream1);
        byte[] byteArray2 = stream1.toByteArray();
        bitmap1.compress(Bitmap.CompressFormat.JPEG,80,stream2);
        byte[] byteArray1 = stream2.toByteArray();
        bikeModelList.add(new BikeModel(0,123123,"Górski rower","Czarno-limonkowy",5000, "Górski","Romet",byteArray1));
        bikeModelList.add(new BikeModel(0,124125,"Szosowy rower","Niebieski",12000, "Szosowy","Romet",byteArray2));
    }

    public void liveDataChanger() {
        viewModel.getBikeBuyList().observe(getViewLifecycleOwner(), new Observer<List<BikeModel>>() {
            @Override
            public void onChanged(List<BikeModel> value) {
                startBasketFragment.setText("Liczba produktów w koszyku "+value.size() );
                buylList=value;
                addRecordsToDatabase(buylList);
            }
        });
    }
    private void addRecordsToDatabase( List<BikeModel> buylList)
    {
        int i =1;
        db.bikeDao().deleteAll();
        for(BikeModel model: buylList)
        {
            model.setDbId(i);
            db.bikeDao().insertAll(model);
            i++;
        }
    }
}