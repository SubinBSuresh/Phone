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
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

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
        public void addContactToDatabase(List<ContactModel> contactListDatabase) throws RemoteException {
            DBHelper dbHelper = new DBHelper(getApplicationContext());
            dbHelper.saveContact(contactListDatabase);

        }


        @Override
        public void callNumber(String phoneNumber, String name) throws RemoteException {
            //MAKE ACTUAL PHONE CALL
          if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber)));Log.e("############################",phoneNumber+name);
            // GO TO CALL SCREEN FROM ACTIVITY
          startActivity(new Intent(getApplicationContext(),Call_screen.class));
          Intent intent = new Intent(String.valueOf(getApplicationContext()));
            startActivity(new Intent(getApplicationContext(), Call_screen.class));
       Toast.makeText(getApplicationContext(),phoneNumber+name, Toast.LENGTH_SHORT).show();
        }


        @Override
        public List<String> getList() throws RemoteException {
            List<String> list = new ArrayList<>();
            list.add("Phone");
            list.add("Contact");
            list.add("Favorite");
            list.add("Recent");
            return list;
        }


        @Override
        public List<RecentModel> getAllRecents() throws RemoteException {

            DBHelper phoneDbHandler = new DBHelper(getApplicationContext());

            return phoneDbHandler.getAllRecents();
        }


        @Override
        public List<ContactModel> getContacts() throws RemoteException {
            DBHelper dbHelper = new DBHelper(getApplicationContext());
            List<ContactModel> list = dbHelper.getContacts();
//            Log.e("####$$$##",""+list.size());

            return list;
        }

        @Override
        public boolean checkContactPresentInFavoritesTable(int id) throws RemoteException {
            DBHelper phoneDbHandler = new DBHelper(getApplicationContext());
            return phoneDbHandler.checkContactPresentInFavoritesTable(id);
        }


        @Override
        public List<SuggestionModel> getSuggestions(String searchedNumber) throws RemoteException {

            //**********************If Using ContentProvider for Contacts - USE THIS *********************************

/*            Uri uri = ContactsContract.Contacts.CONTENT_URI;
            SuggestionModel suggestionModel;
            List<SuggestionModel> contactModelList = new ArrayList<>();
            String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC ";
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
            }*/
            DBHelper dbHelper = new DBHelper(getApplicationContext());
            return dbHelper.getContactSuggestion(searchedNumber);
            //********************** If Using SQLite DB - USE THIS & DBHelper CODE*********************************
/*            DBHelper dbHelper = new DBHelper(getApplicationContext());
            return dbHelper.getContactSuggestion(searchedNumber);*/
        }


        //-------------------Favorites---------------------------------------------------------------

        @Override
        public void addContactToFavorites(int id) throws RemoteException {
            Log.e("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$","HERE IN ADD CONTACT TO FAVORITES"+id);

            DBHelper phoneDbHandler = new DBHelper(getApplicationContext());
            phoneDbHandler.addContactToFavorites(id);

        }





        @Override
        public void removeContactFromFavorites(int id) throws RemoteException {
            DBHelper phoneDbHandler = new DBHelper(getApplicationContext());
            phoneDbHandler.removeContactFromFavorites(id);
        }

        @Override
        public List<FavoritesModel> getFavorites() throws RemoteException {
            List<FavoritesModel> favoriteList = new ArrayList<>();
            DBHelper phoneDbHandler = new DBHelper(getApplicationContext());
            return phoneDbHandler.getFavorites();
        }


        //---------------------------------------------------------------------------------------


        public void addToRecent(List<RecentModel> recent) throws RemoteException {
            DBHelper phoneDbHandler = new DBHelper(getApplicationContext());


            phoneDbHandler.addtoRecent(recent);

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


    };

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return stubObject;
    }
}