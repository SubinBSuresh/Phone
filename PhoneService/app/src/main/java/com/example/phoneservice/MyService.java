package com.example.phoneservice;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

import ServicePackage.ContactModel;
import ServicePackage.FavoritesModel;
import ServicePackage.RecentModel;
import ServicePackage.SuggestionModel;
import ServicePackage.aidlInterface;


public class MyService extends Service {
    aidlInterface.Stub stubObject = new aidlInterface.Stub() {
        @Override
        public void callNumber(String phoneNumber, String name) throws RemoteException {
            //MAKE ACTUAL PHONE CALL
/*           if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber)));*/




            // GO TO CALL SCREEN FROM ACTIVITY
           startActivity(new Intent(getApplicationContext(),Call_screen.class));
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
        public List<RecentModel> getAllRecents() {
            List<RecentModel> recentModelArrayList = new ArrayList<>();
            DBHelper phoneDbHandler = new DBHelper(getApplicationContext());
            return phoneDbHandler.getAllRecents();
        }

        @Override
        public List<ContactModel> getContacts() {
            DBHelper dbHelper = new DBHelper(getApplicationContext());
            return dbHelper.getContacts();
        }

        @Override
        public List<SuggestionModel> getSuggestions(String searchedNumber) throws RemoteException {

            //**********************If Using ContentProvider for Contacts - USE THIS *********************************

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
                    String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =? AND " + ContactsContract.CommonDataKinds.Phone.NUMBER + " LIKE ?";
                    Cursor phoneCursor = getContentResolver().query(uriPhone, null, selection, new String[]{id, searchedNumber + "%"}, null);
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
            //********************** If Using SQLite DB - USE THIS & DBHelper CODE*********************************
/*            DBHelper dbHelper = new DBHelper(getApplicationContext());
            return dbHelper.getContactSuggestion(searchedNumber);*/
        }
/*
        @Override
        public List<FavoritesModel> getAllFavorites() throws RemoteException {
            DBHelper dbHelper = new DBHelper(getApplicationContext());
            return dbHelper.getFAvorites();
        }


        @Override
        public void deleteFavorite(int id) throws RemoteException {
            DBHelper dbHelper = new DBHelper(getApplicationContext());
            dbHelper.removeContactFromFavorites(id);
        }

 */
        //-------------------Favorites---------------------------------------------------------------
     public List<FavoritesModel> getFavorites() throws RemoteException {
         List<FavoritesModel> favoriteList = new ArrayList<>();
         DBHelper phoneDbHandler = new DBHelper(getApplicationContext());
         return phoneDbHandler.getFAvorites();
     }

        public void addContactToFavorites(int id) throws RemoteException{
            DBHelper phoneDbHandler = new DBHelper(getApplicationContext());
            phoneDbHandler.addContactToFavorites(id);

        }
        public void removeContactFromFavorites(int id) throws RemoteException{
            DBHelper phoneDbHandler = new DBHelper(getApplicationContext());
            phoneDbHandler.removeContactFromFavorites(id);

        }
        public boolean checkContactPresentInFavoritesTable(int id) throws RemoteException{
            DBHelper phoneDbHandler = new DBHelper(getApplicationContext());
            return phoneDbHandler.checkContactPresentInContactTable(id);
        }

        //---------------------------------------------------------------------------------------


        public void addToRecent(ContactModel contact) throws RemoteException {
            DBHelper phoneDbHandler = new DBHelper(getApplicationContext());
            RecentModel recentModel = new RecentModel();
            recentModel.setName(contact.getName());
            recentModel.setNumber(contact.getNumber());
            recentModel.setDate();
            phoneDbHandler.addtoRecent(recentModel);
        }


    };

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return stubObject;
    }
}