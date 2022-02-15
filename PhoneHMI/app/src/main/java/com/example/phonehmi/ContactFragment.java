package com.example.phonehmi;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import java.util.List;

import ServicePackage.ContactModel;


public class ContactFragment extends Fragment {



    RecyclerView recyclerView; //recyclerview object
    TextView textView;
    List<ContactModel> contactList;
    private ContactAdapter contactAdapter;
    SwipeRefreshLayout swipeRefreshLayoutContacts;
    private ContactModel contactModel;



    public ContactFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout
        View view = inflater.inflate(R.layout.fragment_contact, container, false);


        recyclerView = view.findViewById(R.id.rvView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // textView = view.findViewById(R.id.empty_view_contacts);

//        swipeRefreshLayoutContacts = view.findViewById(R.id.swipeRefreshLayoutContacts);

        //ADDING CONTACTS FROM CONTENT PROVIDER TO CURSOR
        ContentResolver resolver = getContext().getContentResolver();
        Cursor cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        // dbHelper.saveContact(cursor);


        swipeRefreshLayoutContacts.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {




            @Override
            public void onRefresh() {

                swipeRefreshLayoutContacts.setRefreshing(false);
                recyclerView.setAdapter(contactAdapter);
                contactAdapter.notifyDataSetChanged();
                contactAdapter = new ContactAdapter( contactList, getContext());

            }
        });
        contactAdapter = new ContactAdapter(contactList, getContext());
        recyclerView.setAdapter(contactAdapter);
        contactAdapter.notifyDataSetChanged();

        // updateVisibility();
        return view;
    }

    private List<ContactModel> refreshContacts() {
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
