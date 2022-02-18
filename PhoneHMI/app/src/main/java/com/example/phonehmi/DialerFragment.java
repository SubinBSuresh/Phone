package com.example.phonehmi;

import android.annotation.SuppressLint;
import android.content.Intent;
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


public class DialerFragment extends Fragment implements View.OnClickListener {
    @SuppressLint("StaticFieldLeak")
    public static TextView tvCallSelectedNumber, tvCallSelectedName;
    String number;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnStar, btnHash;
    ImageButton imageButtonBack, btnCall;
    RecyclerView recyclerView;
    List<SuggestionModel> suggestionModelList;
    String searchedNumber, searchedName;
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



        /*        CODE WITH ONCLICKLISTENER FOR INDIVIDUAL BUTTONS
         *         TO UNDO CHANGES REMOVE THE "implements View.OnClickListener" AND REMOVE ITS SUPER CONSTRUCTOR
         *
         * */
/*        //Button 0
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
        btnHash.setOnClickListener(v -> showPhoneNumber("#"));*/

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnHash.setOnClickListener(this);
        btnStar.setOnClickListener(this);

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
            tvCallSelectedName.setText("");
        });

/*
        imageButtonBack.setOnLongClickListener(view12 -> {
            showPhoneNumber("");
            return false;
        });
*/

        //Button Call
        btnCall.setOnClickListener(view1 -> {
            String phoneNum = tvCallSelectedNumber.getText().toString();
            searchedName = tvCallSelectedNumber.getText().toString();

            if (phoneNum.isEmpty()) {
                Toast.makeText(getContext(), "Cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                if (phoneNum.length() >= 10 && phoneNum.length() <= 13) {

                    //ACTUAL CALL
 /*                   try {
                        MainActivity.getAidl().callNumber(phoneNum, tvCallSelectedName.getText().toString());
                        tvCallSelectedNumber.setText("");
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }*/


                    //CALL ACTIVITY SCREEN
                    Intent intent=new Intent(getContext(), Calling_Screen.class);
                    intent.putExtra("name", tvCallSelectedName.getText().toString());
                    intent.putExtra("number", tvCallSelectedNumber.getText().toString());
                    startActivity(intent);

//                    Toast.makeText(getContext(), phoneNum + tvCallSelectedName.getText().toString(), Toast.LENGTH_SHORT).show();

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
                    tvCallSelectedName.setText("");
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button0:
                showPhoneNumber("0");
                break;
            case R.id.button1:
                showPhoneNumber("1");
                break;
            case R.id.button2:
                showPhoneNumber("2");
                break;
            case R.id.button3:
                showPhoneNumber("3");
                break;
            case R.id.button4:
                showPhoneNumber("4");
                break;
            case R.id.button5:
                showPhoneNumber("5");
                break;
            case R.id.button6:
                showPhoneNumber("6");
                break;
            case R.id.button7:
                showPhoneNumber("7");
                break;
            case R.id.button8:
                showPhoneNumber("8");
                break;
            case R.id.button9:
                showPhoneNumber("9");
                break;
            case R.id.buttonStar:
                showPhoneNumber("*");
                break;
            case R.id.buttonHash:
                showPhoneNumber("#");
                break;
            default:
                break;
        }
    }


}



