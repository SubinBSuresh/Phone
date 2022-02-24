package com.example.phonehmi.Model;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.phonehmi.MainActivity;
import com.example.phonehmi.presenter.IMVPPresenter;
import com.example.phonehmi.presenter.MVPPresenter;

import java.util.ArrayList;
import java.util.List;

import ServicePackage.ContactModel;
import ServicePackage.FavoritesModel;
import ServicePackage.RecentModel;
import ServicePackage.SuggestionModel;

public class MVPModel implements IMVPModel {
    List<SuggestionModel> suggestionModelList;

    List<ContactModel> contactModelList;

    List<FavoritesModel> favoritesModelList;

    List<RecentModel> recentModelList;

    IMVPPresenter imvpPresenter;

    public MVPModel(IMVPPresenter imvpPresenter) {
        this.imvpPresenter = imvpPresenter;
    }

    public MVPModel() {

    }

    public MVPModel(MVPPresenter dialerPresenter) {
    }

    /*************************************************************************************************************************/
    //GET SUGGESTIONS
    @Override
    public List<SuggestionModel> getSuggestions(String number) {
        suggestionModelList = null;

        //OUR OLD TRY CATCH
        //THIS METHOD IS RUN AND THE VALUES ARE RETURNED FIRSTLY TO DialerPresenter.
        //THEN FROM DialerPresenter, THE VALUES ARE THEN RETURNED TO DialerFragment, FROM WHERE IT IS SHOWN IN THE LIST
        try {
            suggestionModelList = MainActivity.getAidl().getSuggestions(number);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return suggestionModelList;
    }


    //SHOW PHONE NUMBER IN DIALER SCREEN
    @Override
    public String showPhoneNumber(String digit, String number) {

        if (number.length() < 15) {
            return number + digit;
        }
        return number;
    }


    /*************************************************************************************************************************/


    @Override
    public List<ContactModel> getContacts() {
        contactModelList = null;
        try {
            contactModelList = MainActivity.getAidl().getContacts();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return contactModelList;
    }


    @Override
    public List<FavoritesModel> getFavorites() {
        favoritesModelList = null;
        try {
            favoritesModelList = MainActivity.getAidl().getFavorites();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return favoritesModelList;
    }


    @Override
    public void removeContactFromFavorites(int id) {
        try {
            MainActivity.getAidl().removeContactFromFavorites(id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    @SuppressLint("Range")
    @Override
    public void addContactToDatabase(Context context) {
        //ADDING CONTACTS FROM CONTENT PROVIDER TO CURSOR
        contactModelList = null;
        contactModelList = new ArrayList<>();

        ContentResolver resolver = context.getContentResolver();
        @SuppressLint("Recycle")
        Cursor cursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, null);

        while (cursor.moveToNext()) {
            ContactModel contactModel = new ContactModel();
            contactModel.setName(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)));
            contactModel.setNumber(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
            contactModel.setId(cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)));
            contactModelList.add(contactModel);

        }
        Log.e("######LIST_SIZE########", "" + contactModelList.size());


        try {
            MainActivity.getAidl().addContactToDatabase(contactModelList);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkContactPresentInFavoritesTable(int id) {
        boolean flag = false;
        try {
            flag = MainActivity.getAidl().checkContactPresentInFavoritesTable(id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public void addContactToFavorites(int id) {
        try {
            MainActivity.getAidl().addContactToFavorites(id);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addToRecent(List<RecentModel> recentModelList) {
        try {
            MainActivity.getAidl().addToRecent(recentModelList);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<RecentModel> getAllRecents() {
        recentModelList = null;
        try {
            recentModelList = MainActivity.getAidl().getAllRecents();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return recentModelList;
    }

}
