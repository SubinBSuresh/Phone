package com.example.phonehmi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import ServicePackage.aidlInterface;

public class ContactFragment extends Fragment {

    EditText etName;
    EditText etNumber;
    Button btnSave;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_contact, container, false);



        return view;
    }
}