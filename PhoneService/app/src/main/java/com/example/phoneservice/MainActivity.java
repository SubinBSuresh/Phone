package com.example.phoneservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;

import ServicePackage.ContactModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permissionCallPhone();
        permissionReadContacts();
        getContacts();

    }

    @SuppressLint("Range")
    private void getContacts() {
        DBHelper dbHelper = new DBHelper(this);

        @SuppressLint("Recycle") Cursor cursor = this.getContentResolver().query(ContactsContract.
                CommonDataKinds.Phone.CONTENT_URI, null, null,
                null, ContactsContract.Contacts.DISPLAY_NAME + " ASC");

        cursor.moveToFirst();
        do {
            dbHelper.addContact(new ContactModel(cursor.getString(cursor.
                    getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)),
                    cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.
                            Phone.NUMBER))));

        } while (cursor.moveToNext());
    }


        private void permissionReadContacts() {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_CONTACTS}, 1);
            }

        }

        private void permissionCallPhone() {
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE}, 1);
            }

        }
    }

