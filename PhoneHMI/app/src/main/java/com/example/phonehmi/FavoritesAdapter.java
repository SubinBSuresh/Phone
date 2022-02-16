package com.example.phonehmi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ServicePackage.FavoritesModel;

// this is some random push file

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder>{
    ArrayList<FavoritesModel> favoriteList;
    private final Context context;

    private LayoutInflater layoutInflater;
    FavoritesFragment favoritesFragment;
    FavoritesAdapter favoritesAdapter;

    public FavoritesAdapter(ArrayList<FavoritesModel> favoriteList, Context context) {
        this.favoriteList = favoriteList;
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

        holder.name.setText(favoriteList.get(position).getName());
        holder.remBtn.setBackgroundResource(R.drawable.star_on_foreground);

    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder  {

        TextView name;
        ImageButton remBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.favorite_name);
            remBtn = itemView.findViewById(R.id.btn_remove_favorite);
           // remBtn.setOnClickListener(this);

            remBtn.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    //dbHelper = new DBHelper(context);
                    final FavoritesModel favoritesModel = favoriteList.get(position);
                    try {
                        MainActivity.getAidl().removeContactFromFavorites(favoritesModel.getId());

                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                    favoriteList.remove(position);


                    //contactFragment.rvAll.invalidate();
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, favoriteList.size());
                    //FavoritesFragment.favoritesAdapter.notifyDataSetChanged();
                    favoritesAdapter.notifyDataSetChanged();


                }
            });

          /* name.setOnLongClickListener(new View.OnLongClickListener() {
               @Override
               public boolean onLongClick(View v) {
                   try {
                       MainActivity.getAidl().placeCall(favoriteList.get(getAdapterPosition()).getNumber());
                   } catch (RemoteException e) {
                       e.printStackTrace();
                   }
                   return false;
               }
           });

           */

        }


    }
}
