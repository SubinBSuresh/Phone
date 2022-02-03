package com.example.phonehmi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ServicePackage.FavoritesModel;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder>{
    ArrayList<FavoritesModel> favData;
    private Context context;

    public FavoritesAdapter(ArrayList<FavoritesModel> favData, Context context) {
        this.favData = favData;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(favData.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return favData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        ToggleButton remBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.favorite_name);
            remBtn = itemView.findViewById(R.id.btn_remove_favorite);
            remBtn.setOnClickListener(this);

            remBtn.setBackgroundDrawable(ContextCompat.getDrawable(context, android.R.drawable.btn_star_big_on));

            //name.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            int position = this.getAdapterPosition();
            favData.remove(position);
            //remBtn.setBackgroundDrawable(ContextCompat.getDrawable(context, android.R.drawable.btn_star_big_off));

            notifyItemRemoved(position);
        }
    }
}
