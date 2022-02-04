package com.example.phoneservice;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.CallLog;

import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

import ServicePackage.ContactModel;
import ServicePackage.FavoritesModel;
import ServicePackage.aidlInterface;


public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return stubObject;
    }


    aidlInterface.Stub stubObject = new aidlInterface.Stub() {
        @Override
        public void callNumber(String phoneNumber) throws RemoteException {
            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber)));

        }
        //@Override
        public List<String> getList() throws RemoteException {
            List<String> list = new ArrayList<>();
            list.add("Phone");
            list.add("Contact");
            list.add("Favorite");
            list.add("Recent");
            return list;
        }


        @Override
        public List<ContactModel> getContacts() throws RemoteException {
            DBHelper dbHelper = new DBHelper(getApplicationContext());
            return dbHelper.getContacts();
        }
        @Override
        public List<FavoritesModel> getAllFavorites() throws RemoteException {
            DBHelper dbHelper = new DBHelper(getApplicationContext());
            return dbHelper.getAllFavorites();
        }


        @Override
        public void deleteFavorite(int id) throws RemoteException {
            DBHelper dbHelper= new DBHelper(getApplicationContext());
            dbHelper.deleteFavoriteById(id);
        }


        //Working code for recents.
/*        @Override
        public Cursor fetchCallLogs() throws RemoteException {
            // reading all data in descending order according to DATE
            String sortOrder = android.provider.CallLog.Calls.DATE + " DESC";

            Cursor cursors = getContentResolver().query(
                    CallLog.Calls.CONTENT_URI,
                    null,
                    null,
                    null,
                    sortOrder);
            return cursors;

        }*/
    };
}