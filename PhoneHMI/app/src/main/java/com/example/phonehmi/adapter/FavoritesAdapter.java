package com.example.phonehmi.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonehmi.R;
import com.example.phonehmi.presenter.MVPPresenter;
import com.example.phonehmi.view.Calling_Screen;
import com.example.phonehmi.view.DialerFragment;
import com.example.phonehmi.view.FavoritesFragment;
import com.example.phonehmi.view.IFavoritesView;

import java.util.List;

import ServicePackage.FavoritesModel;

// this is some random push file

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> implements IFavoritesView {
    public static List<FavoritesModel> favoriteList;
    Context context;

    MVPPresenter mvpPresenter = new MVPPresenter(this);
    FavoritesModel favoritesModel;

    public FavoritesAdapter(List<FavoritesModel> favoriteList, Context context) {
        FavoritesAdapter.favoriteList = favoriteList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorites_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        favoritesModel = favoriteList.get(position);
        holder.name.setText(favoritesModel.getName());
        holder.remBtn.setBackgroundResource(R.drawable.star_on_foreground);

    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        ImageButton remBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.favorite_name);
            remBtn = itemView.findViewById(R.id.btn_remove_favorite);
            // remBtn.setOnClickListener(this);
            itemView.setOnClickListener(this);
            remBtn.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    //dbHelper = new DBHelper(context);
                    favoritesModel = favoriteList.get(position);

                    mvpPresenter.removeContactFromFavorites(favoritesModel.getId());


                    favoriteList.remove(position);

//                    contactFragment.rvAll.invalidate();
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, favoriteList.size());
                    FavoritesFragment.favoritesAdapter.notifyDataSetChanged();
//                    favoritesAdapter.notifyDataSetChanged();


                }
            });


        }

        @Override
        public void onClick(View view) {
            Log.e("$$$$$$$", "Clickedfavorite item");
            TextView clickedContactName = view.findViewById(R.id.favorite_name);
            String contactName = clickedContactName.getText().toString();
            //Add contact to the main contact space
            DialerFragment.tvCallSelectedName.setText(contactName);
            DialerFragment.tvCallSelectedNumber.setText(favoritesModel.getNumber());
            Intent intent = new Intent(view.getContext(), Calling_Screen.class);
            context.startActivity(intent);

        }
    }
}
