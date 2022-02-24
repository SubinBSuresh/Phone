package com.example.phonehmi.Model;

import android.content.Context;

import java.util.List;

import ServicePackage.ContactModel;
import ServicePackage.FavoritesModel;
import ServicePackage.RecentModel;
import ServicePackage.SuggestionModel;

public interface IMVPModel {

    //SIMILAR TO AIDL FILE, OUR METHODS ARE CALLED HERE. GO TO DialerModel file
    List<SuggestionModel> getSuggestions(String number);

    String showPhoneNumber(String digit, String number);


    List<ContactModel> getContacts();

    //favorites reading
    List<FavoritesModel> getFavorites();

    void removeContactFromFavorites(int id);

    void addContactToDatabase(Context context);

    boolean checkContactPresentInFavoritesTable(int id);

    void addContactToFavorites(int id);

    void addToRecent(List<RecentModel> recentModelList);

    List<RecentModel> getAllRecents();
}
