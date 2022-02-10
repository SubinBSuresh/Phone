package com.example.phonehmi;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ServicePackage.SuggestionModel;


public class DialerFragment extends Fragment {
    @SuppressLint("StaticFieldLeak")
    public static TextView tvCallSelectedNumber, tvCallSelectedName;
    String number;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnStar, btnHash;
    ImageButton imageButtonBack, btnCall;
    RecyclerView recyclerView;
    List<SuggestionModel> suggestionModelList;
    String searchedNumber;
    ContactSuggestionAdapter contactSuggestionAdapter;

    public DialerFragment() {
    }

    @SuppressLint({"Range", "NotifyDataSetChanged"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialer, container, false);

        btn0 = view.findViewById(R.id.button0);
        btn1 = view.findViewById(R.id.button1);
        btn2 = view.findViewById(R.id.button2);
        btn3 = view.findViewById(R.id.button3);
        btn4 = view.findViewById(R.id.button4);
        btn5 = view.findViewById(R.id.button5);
        btn6 = view.findViewById(R.id.button6);
        btn7 = view.findViewById(R.id.button7);
        btn8 = view.findViewById(R.id.button8);
        btn9 = view.findViewById(R.id.button9);
        btnCall = view.findViewById(R.id.buttonCall);
        btnStar = view.findViewById(R.id.buttonStar);
        btnHash = view.findViewById(R.id.buttonHash);
        imageButtonBack = view.findViewById(R.id.imageButtonBack);
        tvCallSelectedNumber = view.findViewById(R.id.textViewPhoneNumber);
        tvCallSelectedName = view.findViewById(R.id.textViewContactName);
        recyclerView = view.findViewById(R.id.RecyclerViewSuggestion);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


        //Button 0
        btn0.setOnClickListener(v -> showPhoneNumber("0"));

        //Button 1
        btn1.setOnClickListener(v -> showPhoneNumber("1"));

        //Button 2
        btn2.setOnClickListener(v -> showPhoneNumber("2"));

        //Button 3
        btn3.setOnClickListener(v -> showPhoneNumber("3"));

        //Button 4
        btn4.setOnClickListener(v -> showPhoneNumber("4"));

        //Button 5
        btn5.setOnClickListener(v -> showPhoneNumber("5"));

        //Button 6
        btn6.setOnClickListener(v -> showPhoneNumber("6"));

        //Button 7
        btn7.setOnClickListener(v -> showPhoneNumber("7"));

        //Button 8
        btn8.setOnClickListener(v -> showPhoneNumber("8"));

        //Button 9
        btn9.setOnClickListener(v -> showPhoneNumber("9"));

        //Button Star
        btnStar.setOnClickListener(v -> showPhoneNumber("*"));

        //Button Hash
        btnHash.setOnClickListener(v -> showPhoneNumber("#"));

        //Button 0 Long Press
        btn0.setOnLongClickListener(v -> {
            showPhoneNumber("+");
            return true;
        });


        //Button Delete
        imageButtonBack.setOnClickListener(v -> {
            StringBuilder stringBuilder = new StringBuilder(tvCallSelectedNumber.getText());
            if (stringBuilder.length() > 0) {
                stringBuilder.deleteCharAt(tvCallSelectedNumber.getText().length() - 1);
            }
            tvCallSelectedNumber.setText(stringBuilder.toString());
        });

        imageButtonBack.setOnLongClickListener(view12 -> {
            showPhoneNumber("");
            return true;
        });

        //Button Call
        btnCall.setOnClickListener(view1 -> {
            String phoneNum = tvCallSelectedNumber.getText().toString();
            if (phoneNum.isEmpty()) {
                Toast.makeText(getContext(), "Cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                if (phoneNum.length() >= 10 && phoneNum.length() <= 13) {
                    try {
                        MainActivity.getAidl().callNumber(phoneNum);
                        tvCallSelectedNumber.setText("");
                        tvCallSelectedName.setText("");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getContext(), "Invalid Number", Toast.LENGTH_SHORT).show();
                }
            }
        });


        //Number Textview Change Listener
        tvCallSelectedNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchedNumber = tvCallSelectedNumber.getText().toString();
                // Check the number with database and get suggestions and populate the recycler view
                if (searchedNumber.isEmpty()) {
                    searchedNumber = "";
                    suggestionModelList.clear();
                } else {
                    try {
                        suggestionModelList = MainActivity.getAidl().getSuggestions(searchedNumber);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    contactSuggestionAdapter = new ContactSuggestionAdapter(suggestionModelList, getContext());
                    recyclerView.setAdapter(contactSuggestionAdapter);
                }
                contactSuggestionAdapter.notifyDataSetChanged();
                btnCall.setEnabled(searchedNumber.length() < 15);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        return view;
    }

    // Show Phone Number
    @SuppressLint("SetTextI18n")
    private void showPhoneNumber(String digit) {
        number = tvCallSelectedNumber.getText().toString();
        if (number.length() < 15) {
            tvCallSelectedNumber.setText(number + digit);
        }
    }
}