package com.example.phonehmi.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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

import com.example.phonehmi.Adapter.ContactSuggestionAdapter;
import com.example.phonehmi.Presenter.DialerPresenter;
import com.example.phonehmi.R;

import java.util.List;

import ServicePackage.SuggestionModel;


public class DialerFragment extends Fragment implements IDialerView, View.OnClickListener {
    @SuppressLint("StaticFieldLeak")
    public static TextView tvCallSelectedNumber, tvCallSelectedName;
    String number;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnStar, btnHash;
    ImageButton imageButtonBack, btnCall;
    RecyclerView recyclerView;
    List<SuggestionModel> suggestionModelList;
    String searchedNumber, searchedName;
    ContactSuggestionAdapter contactSuggestionAdapter;
    DialerPresenter dialerPresenter;

    public DialerFragment() {
    }

    @SuppressLint({"Range", "NotifyDataSetChanged"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialer, container, false);

        dialerPresenter = new DialerPresenter(this);

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
        imageButtonBack.setOnClickListener(this);
        btnCall.setOnClickListener(this);

        //Button 0 Long Press
        btn0.setOnLongClickListener(v -> {
            number = dialerPresenter.showPhoneNumber("+", tvCallSelectedNumber.getText().toString());
            tvCallSelectedNumber.setText(number);
            return true;
        });


        imageButtonBack.setOnLongClickListener(view12 -> {
            tvCallSelectedName.setText("");
            tvCallSelectedNumber.setText("");
            Toast.makeText(getContext(), "Cleared Selection", Toast.LENGTH_SHORT).show();
            return true;
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
                    suggestionModelList = dialerPresenter.getSuggestions(searchedNumber);

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

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        number = tvCallSelectedNumber.getText().toString();
        switch (view.getId()) {
            case R.id.button0:
                number = dialerPresenter.showPhoneNumber("0", number);
                break;

            case R.id.button1:
                number = dialerPresenter.showPhoneNumber("1", number);
                break;

            case R.id.button2:
                number = dialerPresenter.showPhoneNumber("2", number);
                break;

            case R.id.button3:
                number = dialerPresenter.showPhoneNumber("3", number);
                break;

            case R.id.button4:
                number = dialerPresenter.showPhoneNumber("4", number);
                break;

            case R.id.button5:
                number = dialerPresenter.showPhoneNumber("5", number);
                break;

            case R.id.button6:
                number = dialerPresenter.showPhoneNumber("6", number);
                break;

            case R.id.button7:
                number = dialerPresenter.showPhoneNumber("7", number);
                break;

            case R.id.button8:
                number = dialerPresenter.showPhoneNumber("8", number);

                break;
            case R.id.button9:
                number = dialerPresenter.showPhoneNumber("9", number);
                break;

            case R.id.buttonStar:
                number = dialerPresenter.showPhoneNumber("*", number);
                break;

            case R.id.buttonHash:
                number = dialerPresenter.showPhoneNumber("#", number);
                break;
            case R.id.imageButtonBack:
                StringBuilder stringBuilder = new StringBuilder(tvCallSelectedNumber.getText());
                if (stringBuilder.length() > 0) {
                    stringBuilder.deleteCharAt(tvCallSelectedNumber.getText().length() - 1);
                }
//                tvCallSelectedNumber.setText(stringBuilder.toString());
                number = stringBuilder.toString();
                tvCallSelectedName.setText("");
                break;

            case R.id.buttonCall:
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
                        Intent intent = new Intent(getContext(), Calling_Screen.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getContext(), "Invalid Number", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            default:
                break;
        }
        tvCallSelectedNumber.setText(number);
    }
}



