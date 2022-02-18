package com.example.phonehmi;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import ServicePackage.FavoritesModel;


public class FavoritesFragment extends Fragment {
    private static List<FavoritesModel> favoriteList;
    @SuppressLint("StaticFieldLeak")

    //creating object for recycler view
    RecyclerView recyclerView;
    TextView textView;


    SwipeRefreshLayout swipeRefreshLayoutFavorites;
    public static FavoritesAdapter favoritesAdapter;


    public FavoritesFragment() {
        // Required empty public constructor
    }

    public static List<FavoritesModel> refreshContacts() {
        try {
            favoriteList = MainActivity.getAidl().getFavorites();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        //favoriteList = MainActivity.getAidl().getFAvorites();
        return favoriteList;
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
        /*

        swipeRefreshLayoutFavorites.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayoutFavorites.setRefreshing(false);
listUpdate();
            }
        });
listUpdate();

         */
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
    /*

    private void listUpdate(){
        favoritesAdapter = new FavoritesAdapter(refreshContacts(), getContext());
        recyclerView.setAdapter(favoritesAdapter);
        favoritesAdapter.notifyDataSetChanged();
    }

     */


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