package com.example.phonehmi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ServicePackage.FavoritesModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoritesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoritesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //creating object for recycler view
    RecyclerView recyclerView;
    ArrayList<FavoritesModel> favData;
    private  FavoritesAdapter favoriteAdapter;
    TextView textView;

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
        favData=new ArrayList<>();

        // demo list for the recucler view
        FavoritesModel ob1 = new FavoritesModel("Aarch","8606702593");
        favData.add(ob1);
        FavoritesModel ob2= new FavoritesModel("Sachin","8606702593");
        favData.add(ob2);
        FavoritesModel ob3 = new FavoritesModel("Vijay","4365785423");
        favData.add(ob3);

        FavoritesModel ob4= new FavoritesModel("Vinitha","4523566778");
        favData.add(ob4);
        FavoritesModel ob5 = new FavoritesModel("Ziona","34124567898");
        favData.add(ob5);


        favoriteAdapter = new FavoritesAdapter(favData, getContext());
        recyclerView.setAdapter(favoriteAdapter);
        //recyclerView.setAdapter(new FavAdapter(favData, getContext()));

        //to display empty call log message
        updateVisibility();
        return view;
    }

    private void updateVisibility() {
        if (favoriteAdapter.getItemCount() == 0) {
            recyclerView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        }
    }
}