package com.example.phonehmi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import ServicePackage.aidlInterface;

public class DialerFragment extends Fragment {
    String number;
    TextView tvNumber;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnCall;
    ImageButton imageButtonBack;

    public DialerFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialer, container, false);

        btn0 =  view.findViewById(R.id.button0);
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
        imageButtonBack = view.findViewById(R.id.imageButtonBack);
        tvNumber = view.findViewById(R.id.textViewPhoneNumber);


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

        //Button Delete
        imageButtonBack.setOnClickListener(v -> {
            StringBuilder stringBuilder = new StringBuilder(tvNumber.getText());
            if(stringBuilder.length() > 0){
                stringBuilder.deleteCharAt(tvNumber.getText().length() - 1);
            }
            tvNumber.setText(stringBuilder.toString());
        });


        //Button Call

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNum = tvNumber.getText().toString();
                try {
                    MainActivity.getAidl().callNumber(phoneNum);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });


        tvNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                btnCall.setEnabled(tvNumber.getText().length() < 15);

                // Check the number with database and get suggestions and populate the recycler view

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }


    // Show Phone Number
    @SuppressLint("SetTextI18n")
    private void showPhoneNumber(String digit){
        number = tvNumber.getText().toString();
        if (number.length()<15) {
            tvNumber.setText(number + digit);
        }
    }


}