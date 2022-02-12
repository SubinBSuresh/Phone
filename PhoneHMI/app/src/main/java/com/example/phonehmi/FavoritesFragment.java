package com.example.phonehmi;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ServicePackage.FavoritesModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoritesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoritesFragment extends Fragment {
    @SuppressLint("StaticFieldLeak")

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //creating object for recycler view
    RecyclerView recyclerView;
    private  FavoritesAdapter favoritesAdapter;
    TextView textView;


    SwipeRefreshLayout swipeRefreshLayoutFavorites;
    private static List<FavoritesModel> favoriteList;


    public static List<FavoritesModel> refreshContacts() {
        try {
            favoriteList=MainActivity.getAidl().getFavorites();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        //favoriteList = MainActivity.getAidl().getFAvorites();
        return favoriteList;
    }

    public FavoritesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoritesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoritesFragment newInstance(String param1, String param2) {
        FavoritesFragment fragment = new FavoritesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
          View view =  inflater.inflate(R.layout.fragment_favorites, container, false);
        recyclerView=view.findViewById(R.id.recView);
        textView = view.findViewById(R.id.empty_view_favorite);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        favoriteList = new ArrayList<>();
        swipeRefreshLayoutFavorites = view.findViewById(R.id.swipeRefreshLayoutFavorites);

        try {
            favoriteList=MainActivity.getAidl().getFavorites();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        swipeRefreshLayoutFavorites.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayoutFavorites.setRefreshing(false);
                recyclerView.setAdapter(favoritesAdapter);
                favoritesAdapter.notifyDataSetChanged();
                favoritesAdapter = new FavoritesAdapter((ArrayList<FavoritesModel>) refreshContacts(), getContext());
            }
        });
        favoritesAdapter = new FavoritesAdapter((ArrayList<FavoritesModel>) favoriteList, getContext());
        recyclerView.setAdapter(favoritesAdapter);
        favoritesAdapter.notifyDataSetChanged();
        updateVisibility();


        return view;
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