package com.example.phonehmi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import ServicePackage.ContactModel;
import ServicePackage.aidlInterface;

public class ContactFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView; //recyclerview object
    ArrayList<ContactModel> ContactHolder;
    TextView textView;
    private ContactAdapter ContactAdapter;
    private View view;


    public ContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactFragment newInstance(String param1, String param2) {
        ContactFragment fragment = new ContactFragment();
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
        // Inflate layout
         view = inflater.inflate(R.layout.fragment_contact, container, false);


        recyclerView = view.findViewById(R.id.rvView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ContactHolder = new ArrayList<>();
        textView = view.findViewById(R.id.empty_view_contacts);

        /*
        ContactModel ob1 = new ContactModel("Niha");
        ContactHolder.add(ob1);
        ContactModel ob2 = new ContactModel("Safa");
        ContactHolder.add(ob2);
        ContactModel ob3 = new ContactModel("Sreerag Quest");
        ContactHolder.add(ob3);
        ContactModel ob4= new ContactModel("Tineesha Quest");
        ContactHolder.add(ob4);
        ContactModel ob5 = new ContactModel("Sachin");
        ContactHolder.add(ob5);
        ContactModel ob6 = new ContactModel("Subin");
        ContactHolder.add(ob6);
        ContactModel ob7 = new ContactModel("Arya");
        ContactHolder.add(ob7);
        ContactModel ob8 = new ContactModel("Pappa");
        ContactHolder.add(ob8);
        ContactModel ob9 = new ContactModel("Mom");
        ContactHolder.add(ob9);
        ContactModel ob10 = new ContactModel("Dheedi");
        ContactHolder.add(ob10);
        ContactModel ob11 = new ContactModel("Subin1");
        ContactHolder.add(ob11);
        ContactModel ob12 = new ContactModel("Arya1");
        ContactHolder.add(ob12);
        ContactModel ob13 = new ContactModel("Simi");
        ContactHolder.add(ob13);
        ContactModel ob14 = new ContactModel("Mamma");
        ContactHolder.add(ob14);
        ContactModel ob15 = new ContactModel("Bhayya");
        ContactHolder.add(ob15);


         */

        try {
            ContactHolder.addAll(MainActivity.getAidl().getAllContacts());
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        ContactAdapter  = new ContactAdapter(ContactHolder, getContext());
        recyclerView.setAdapter(ContactAdapter);
        //display no contact msg
        updateVisibility();

        return view;



    }

    private void updateVisibility() {
        if(ContactAdapter.getItemCount()==0){
            recyclerView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        }
    }
}