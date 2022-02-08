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
import android.provider.ContactsContract;

import androidx.core.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

import ServicePackage.ContactModel;
import ServicePackage.FavoritesModel;
import ServicePackage.RecentsModel;
import ServicePackage.SuggestionModel;
import ServicePackage.aidlInterface;


public class MyService extends Service {
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
        public List<SuggestionModel> getSuggestions(String searchedNumber) throws RemoteException {
            DBHelper dbHelper = new DBHelper(getApplicationContext());
            return dbHelper.getContactSuggestion(searchedNumber);
        }

/*        @Override
        public List<SuggestionModel> getSuggestions() throws RemoteException {
            Uri uri = ContactsContract.Contacts.CONTENT_URI;
            SuggestionModel suggestionModel;
            List<SuggestionModel> contactModelList = new ArrayList<>();
            String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";
            Cursor cursor = getContentResolver().query(uri, null, null, null, sort);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    Uri uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                    String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =? ";
                    Cursor phoneCursor = getContentResolver().query(uriPhone, null, selection, new String[]{id}, null);
                    if (phoneCursor.moveToNext()) {
                        @SuppressLint("Range") String number = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        suggestionModel = new SuggestionModel();
                        suggestionModel.setContactName(name);
                        suggestionModel.setContactNumber(number);
                        contactModelList.add(suggestionModel);
                        phoneCursor.close();
                    }
                }
                cursor.close();
            }
            return contactModelList;
        }*/

        @Override
        public List<FavoritesModel> getAllFavorites() throws RemoteException {
            DBHelper dbHelper = new DBHelper(getApplicationContext());
            return dbHelper.getAllFavorites();
        }


        @Override
        public void deleteFavorite(int id) throws RemoteException {
            DBHelper dbHelper = new DBHelper(getApplicationContext());
            dbHelper.deleteFavoriteById(id);
        }

        //Working code for recents.
        @Override


        @SuppressLint({"Range", "Range"})
        public List<RecentsModel> fetchCallLogs() throws RemoteException {

            String str_number, str_date, str_contact_name;
            RecentsModel recentsModel;
            List<RecentsModel> recentsModelList = new ArrayList<>();
            // reading all data in descending order according to DATE
            String sortOrder = android.provider.CallLog.Calls.DATE + " DESC";

            Cursor cursor = getContentResolver().query(
                    CallLog.Calls.CONTENT_URI,
                    null,
                    null,
                    null,
                    sortOrder);

            while (cursor.moveToNext()) {
                str_number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));

                str_contact_name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
                str_date = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE));


                recentsModel = new RecentsModel();
                recentsModel.setDate(str_date);
                recentsModel.setNumber(str_number);
                recentsModelList.add(recentsModel);
                return recentsModelList;

            }


            return (List<RecentsModel>) cursor;
        }

    };

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return stubObject;
    }
}