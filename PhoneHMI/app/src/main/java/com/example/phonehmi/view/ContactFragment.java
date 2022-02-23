package com.example.phonehmi.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    //SwipeRefreshLayout swipeRefreshLayoutContacts;
    List<ContactModel> contactListDatabase = new ArrayList<>();
    MVPPresenter mvpPresenter;
    private ContactAdapter contactAdapter;


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
        // swipeRefreshLayoutContacts = view.findViewById(R.id.swipeRefreshLayoutContacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        mvpPresenter = new MVPPresenter(this);


        mvpPresenter.addContactToDatabase(getContext());


        contactList = mvpPresenter.getContacts();


        contactAdapter = new ContactAdapter(contactList, getContext());
        recyclerView.setAdapter(contactAdapter);

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        updateContactList();
        //to display empty call log message
        //updateVisibility();
    }

    private void updateContactList() {

        contactAdapter = new ContactAdapter(contactList, getContext());


        contactList = mvpPresenter.getContacts();
        contactAdapter = new ContactAdapter(contactList, getContext());
        recyclerView.setAdapter(contactAdapter);
    }
}
