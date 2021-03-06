package com.example.phonehmi.presenter;

import android.content.Context;

import java.util.List;

import ServicePackage.ContactModel;
import ServicePackage.FavoritesModel;
import ServicePackage.RecentModel;
import ServicePackage.SuggestionModel;

public interface IMVPPresenter {

    //METHODS ARE CALLED HERE LIKE IN THE AIDL FILE. GO TO DialerPresenter
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
