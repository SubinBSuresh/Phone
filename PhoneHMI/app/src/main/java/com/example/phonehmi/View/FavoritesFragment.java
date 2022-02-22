package com.example.phonehmi.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.phonehmi.MainActivity;
import com.example.phonehmi.R;
import com.example.phonehmi.adapter.FavoritesAdapter;

import java.util.ArrayList;
import java.util.List;

import ServicePackage.FavoritesModel;


public class FavoritesFragment extends Fragment {
    private static List<FavoritesModel> favoriteList;
    @SuppressLint("StaticFieldLeak")

    //creating object for recycler view
    RecyclerView recyclerView;
    TextView textView;
    public static FavoritesAdapter favoritesAdapter;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        recyclerView = view.findViewById(R.id.recView);
        textView = view.findViewById(R.id.empty_view_favorite);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        favoriteList = new ArrayList<>();
        //swipeRefreshLayoutFavorites = view.findViewById(R.id.swipeRefreshLayoutFavorites);

        try {
            favoriteList = MainActivity.getAidl().getFavorites();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        favoritesAdapter = new FavoritesAdapter(favoriteList, getContext());
        recyclerView.setAdapter(favoritesAdapter);
        updateVisibility();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateFavoriteList();
        //to display empty call log message
        updateVisibility();
    }

    private void updateFavoriteList() {
        favoriteList = new ArrayList<>();
        //swipeRefreshLayoutFavorites = view.findViewById(R.id.swipeRefreshLayoutFavorites);

        try {
            favoriteList = MainActivity.getAidl().getFavorites();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        favoritesAdapter = new FavoritesAdapter(favoriteList, getContext());
        recyclerView.setAdapter(favoritesAdapter);
    }

    private void updateVisibility() {
        if (favoritesAdapter.getItemCount() == 0) {
            recyclerView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        }
    }

}