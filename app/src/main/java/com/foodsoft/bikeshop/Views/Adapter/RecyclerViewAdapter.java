
package com.foodsoft.bikeshop.Views.Adapter;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.foodsoft.bikeshop.DBManager.AppDatabase;
import com.foodsoft.bikeshop.Model.BikeModel;
import com.foodsoft.bikeshop.R;
import com.foodsoft.bikeshop.ViewModel.SharedViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<BikeModel> records;
    private View view;
    private List<BikeModel> buylList = new ArrayList<>();
    private SharedViewModel viewModel;
    private  Context context;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView color;
        private final TextView id;
        private final TextView price;
        private final TextView model;
        private final TextView brand;
        private final Button addButton;
        private final ImageView image;


        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            color = view.findViewById(R.id.color);
            id = view.findViewById(R.id.id);
            addButton = view.findViewById(R.id.addButton);
            price = view.findViewById(R.id.price);
            model = view.findViewById(R.id.model);
            brand = view.findViewById(R.id.brand);
            image = view.findViewById(R.id.image);
        }
        public TextView getName() {
            return name;
        }
        public TextView getColor() {
            return color;
        }
        public TextView getId() {
            return id;
        }
        public TextView getPrice() {
            return price;
        }
        public TextView getModel() {
            return model;
        }
        public TextView getBrand() {
            return brand;
        }
        public Button getAddButton() {
            return addButton;
        }
        public ImageView getImage(){return image;}
    }

    @Inject
    public RecyclerViewAdapter(@Named("records") ArrayList<BikeModel> records, @Named("viewModel") SharedViewModel viewModel, @Named("Context") Context context) {
        this.records = records;
        this.viewModel = viewModel;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_bikelist, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder viewHolder, final int position) {

        viewHolder.getName().setText(records.get(position).getName());
        viewHolder.getModel().setText("Model:  "+records.get(position).getModel());
        viewHolder.getBrand().setText("Marka:  "+records.get(position).getBrand());
        viewHolder.getId().setText(String.valueOf(records.get(position).getId()));
        viewHolder.getColor().setText("Kolor:  "+records.get(position).getColor());
        viewHolder.getPrice().setText(records.get(position).getPrice()+ " zł");
        viewHolder.getImage().setImageBitmap( BitmapFactory.decodeByteArray(records.get(position).getBitmap(),0,records.get(position).getBitmap().length));

        viewHolder.getAddButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buylList.add(records.get(position));
                viewModel.setBikeBuyList(buylList);
            }
        });

    }

    @Override
    public int getItemCount() {
        return records.size();
    }

}
