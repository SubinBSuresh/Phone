package com.example.phonehmi;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import ServicePackage.ContactModel;


public class ContactFragment extends Fragment {


    RecyclerView recyclerView; //recyclerview object
    List<ContactModel> contactList;
    //SwipeRefreshLayout swipeRefreshLayoutContacts;
    List<ContactModel> contactListDatabase = new ArrayList<>();
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

        //ADDING CONTACTS FROM CONTENT PROVIDER TO CURSOR
        ContentResolver resolver = requireContext().getContentResolver();
        @SuppressLint("Recycle") Cursor cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while (cursor.moveToNext()) {
            ContactModel contactModel = new ContactModel();
            contactModel.setName(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
            contactModel.setNumber(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
            contactModel.setId(cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)));
            contactListDatabase.add(contactModel);

        /*    Log.e("########",cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
            Log.e("########",cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
            Log.e("########",""+cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)));
*/
        }
        Log.e("######LIST_SIZE########", "" + contactListDatabase.size());


/*
        try {
            contactList = MainActivity.getAidl().getContacts();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
*/

        /*swipeRefreshLayoutContacts.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override

            public void onRefresh() {

                swipeRefreshLayoutContacts.setRefreshing(false);
                recyclerView.setAdapter(contactAdapter);
                contactAdapter.notifyDataSetChanged();
                contactAdapter = new ContactAdapter(refreshContacts(), getContext());

            }
        });*/


        contactList = new ArrayList<>();
        try {
            MainActivity.getAidl().addContactToDatabase(contactListDatabase);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            contactList = MainActivity.getAidl().getContacts();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        contactAdapter = new ContactAdapter(contactList, getContext());
        recyclerView.setAdapter(contactAdapter);

       /* contactAdapter = new ContactAdapter(refreshContacts(), getContext());
        recyclerView.setAdapter(contactAdapter);
        contactAdapter.notifyDataSetChanged();*/

        // updateVisibility();
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
      contactList = new ArrayList<>();
        try {
            MainActivity.getAidl().addContactToDatabase(contactListDatabase);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            contactList = MainActivity.getAidl().getContacts();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        contactAdapter = new ContactAdapter(contactList, getContext());
        recyclerView.setAdapter(contactAdapter);


    }

    public List<ContactModel> refreshContacts() {
        try {
            MainActivity.getAidl().addContactToDatabase(contactListDatabase);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            contactList = MainActivity.getAidl().getContacts();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return contactList;
    }


   /* private void updateVisibility() {
        if (contactAdapter.getItemCount() == 0) {
            recyclerView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        }
    }
*/

}
