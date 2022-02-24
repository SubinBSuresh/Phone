package com.example.phonehmi.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonehmi.R;
import com.example.phonehmi.adapter.ContactAdapter;
import com.example.phonehmi.presenter.MVPPresenter;

import java.util.ArrayList;
import java.util.List;

import ServicePackage.ContactModel;


public class ContactFragment extends Fragment implements IContactView {


    RecyclerView recyclerView; //recyclerview object
    List<ContactModel> contactList;
    MVPPresenter mvpPresenter;
    private ContactAdapter contactAdapter;
    int REQUEST_PERMISSION_CODE = 123;


    public ContactFragment() {
        // Required empty public constructor
    }

    @SuppressLint({"Range", "NotifyDataSetChanged"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout
        View view = inflater.inflate(R.layout.fragment_contact, container, false);


        recyclerView = view.findViewById(R.id.rvView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        mvpPresenter = new MVPPresenter(this);
        if (checkPermission()) {
            mvpPresenter.addContactToDatabase(getContext());


            contactList = mvpPresenter.getContacts();


            contactAdapter = new ContactAdapter(contactList, getContext());
            recyclerView.setAdapter(contactAdapter);
        } else {
            requestPermission();
        }


        return view;
    }

    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_PERMISSION_CODE);

    }


    @Override
    public void onResume() {
        super.onResume();
        updateContactList();
        //to display empty call log message
        //updateVisibility();
    }

    private void updateContactList() {

        if (checkPermission()){
            contactAdapter = new ContactAdapter(contactList, getContext());
            contactList = mvpPresenter.getContacts();
            contactAdapter = new ContactAdapter(contactList, getContext());
            recyclerView.setAdapter(contactAdapter);
        }else {
            requestPermission();
        }


    }
}
