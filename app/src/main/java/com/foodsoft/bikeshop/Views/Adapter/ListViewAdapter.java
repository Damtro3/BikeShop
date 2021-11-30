package com.foodsoft.bikeshop.Views.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.foodsoft.bikeshop.DBManager.AppDatabase;
import com.foodsoft.bikeshop.Model.BikeModel;
import com.foodsoft.bikeshop.R;
import com.foodsoft.bikeshop.ViewModel.SharedViewModel;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

public class ListViewAdapter extends ArrayAdapter<BikeModel> {

    private LayoutInflater mInflater;
    private ArrayList<BikeModel> records;
    private int mViewResourceId;
    TextView name;
    TextView price;
    ImageView image;
    Button dellButton;
    SharedViewModel viewModel;
    @Inject
    public ListViewAdapter(@Named("context") Context context, @Named("textViewResourceId") int textViewResourceId, @Named("records") ArrayList<BikeModel> records, @Named("viewModel") SharedViewModel viewModel) {
        super(context, textViewResourceId, records);
        this.records = records;
        this.viewModel = viewModel;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);
        BikeModel model = records.get(position);
        name = convertView.findViewById(R.id.name);
        price = convertView.findViewById(R.id.price);
        dellButton = convertView.findViewById(R.id.dellButton);
        image = convertView.findViewById(R.id.image);
        if (model != null) {

            if (name != null) {
                name.setText(model.getName());
            }
            if (price != null) {
                price.setText(String.valueOf(model.getPrice()) + " zł");
            }
            if (image != null) {
                image.setImageBitmap(BitmapFactory.decodeByteArray(model.getBitmap(),0,model.getBitmap().length));
            }
        }

        dellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                records.remove(position);
                AppDatabase db = AppDatabase.getDbInstance(getContext());
                db.bikeDao().deleteAll();
                viewModel.setBikeBuyList(records);
            }
        });
        return convertView;
    }
}