package com.example.phonehmi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Call_end extends AppCompatActivity {

    private Object Fragment;
    String nam = DialerFragment.tvCallSelectedName.getText().toString(),num = DialerFragment.tvCallSelectedNumber.getText().toString();
    private TextView tvN,tvNu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_end);
        ImageButton imgeCall = findViewById(R.id.imgecall);

        tvN = findViewById(R.id.tvcallname);
        tvNu = findViewById(R.id.tvcallnum);

        tvN.setText(nam);
        tvNu.setText(num);
        imgeCall.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Calling_Screen.class);

            startActivity(intent);
        });


    }

    @SuppressLint("ResourceType")
    @Override
    public void onBackPressed() {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));

    }


}