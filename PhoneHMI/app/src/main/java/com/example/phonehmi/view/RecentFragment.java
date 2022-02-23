package com.example.phonehmi.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonehmi.R;
import com.example.phonehmi.adapter.RecentAdapter;
import com.example.phonehmi.presenter.MVPPresenter;

import java.util.ArrayList;
import java.util.List;

import ServicePackage.RecentModel;


public class RecentFragment extends Fragment implements IRecentView {
    private RecyclerView recyclerView;
    private View v;

    private RecentAdapter recentAdapter;
    private TextView textView;
    MVPPresenter mvpPresenter;


    public RecentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mvpPresenter = new MVPPresenter(this);

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_recent, container, false);
        recyclerView = v.findViewById(R.id.rv_recents);
        textView = v.findViewById(R.id.empty_view);




        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(layoutManager);
        Log.d("TAG", "recentAdapterInView: ");

        List<RecentModel> listRecents = new ArrayList<>();
/*        try {
            listRecents.addAll(MainActivity.getAidl().getAllRecents());
            Log.e("allrecents", " " + listRecents.size());

        } catch (NullPointerException | RemoteException e) {
            e.printStackTrace();
        }*/

        listRecents =  mvpPresenter.getAllRecents();

        recentAdapter = new RecentAdapter(getContext(), listRecents);

        //to display empty call log message

        if (recentAdapter.getItemCount() == 0) {
            recyclerView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        }
        recyclerView.setAdapter(recentAdapter);

        return v;
    }




    @Override
    public void onResume() {
        super.onResume();
        updateRecentList();
        //to display empty call log message
        updateVisibility();
    }


    public void updateRecentList() {
        List<RecentModel> recentList = new ArrayList<>();

/*        try {
            recentList.addAll(MainActivity.getAidl().getAllRecents());
        } catch (NullPointerException | RemoteException e) {
            e.printStackTrace();
        }*/

        recentList = mvpPresenter.getAllRecents();

        recentAdapter = new RecentAdapter(getContext(), recentList);
        recyclerView.setAdapter(recentAdapter);
    }

    public void updateVisibility() {
        if (recentAdapter.getItemCount() == 0) {
            recyclerView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        }

    }
}