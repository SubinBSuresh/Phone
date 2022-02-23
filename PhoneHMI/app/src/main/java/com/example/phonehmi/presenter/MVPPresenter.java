package com.example.phonehmi.presenter;

import android.content.Context;

import com.example.phonehmi.Model.MVPModel;
import com.example.phonehmi.view.ICallScreen;
import com.example.phonehmi.view.IContactView;
import com.example.phonehmi.view.IDialerView;
import com.example.phonehmi.view.IFavoritesView;
import com.example.phonehmi.view.IRecentView;

import java.util.List;

import ServicePackage.ContactModel;
import ServicePackage.FavoritesModel;
import ServicePackage.RecentModel;
import ServicePackage.SuggestionModel;

public class MVPPresenter implements IMVPPresenter {


    List<SuggestionModel> suggestionModelList;
    List<ContactModel> contactModelList;
    List<FavoritesModel> favoritesModelList;
    List<RecentModel> recentModelList;


    IDialerView iDialerView;
    IContactView iContactView;
    IFavoritesView iFavoritesView;
    ICallScreen iCallScreen;
    IRecentView iRecentView;






    MVPModel mvpModel;
    MVPPresenter mvpPresenter;


    public MVPPresenter(IDialerView iDialerView) {
        this.iDialerView = iDialerView;
        mvpModel = new MVPModel(mvpPresenter);

    }

    public MVPPresenter(IContactView iContactView) {
        this.iContactView = iContactView;
        mvpModel = new MVPModel(mvpPresenter);

    }

    public MVPPresenter(IFavoritesView iFavoritesView) {
        this.iFavoritesView = iFavoritesView;
        mvpModel = new MVPModel(mvpPresenter);
    }
    public MVPPresenter(ICallScreen iCallScreen) {
        this.iCallScreen = iCallScreen;
        mvpModel = new MVPModel(mvpPresenter);
    }


    public MVPPresenter(IRecentView iRecentView) {
        this.iRecentView = iRecentView;
        mvpModel = new MVPModel(mvpPresenter);
    }
    // BOTH THE METHODS MENTIONED IN IDialerInterface IS CALLED HERE
    @Override
    public List<SuggestionModel> getSuggestions(String number) {
        suggestionModelList = null;

        //THIS METHOD IS EXECUTED IN THE DialerModel. FIRSTLY CHECK IDialerModel and THEN DialerModel
        suggestionModelList = mvpModel.getSuggestions(number);
        return suggestionModelList;
    }

    @Override
    public String showPhoneNumber(String digit, String number) {
        return mvpModel.showPhoneNumber(digit, number);


    }

    @Override
    public List<FavoritesModel> getFavorites() {
        favoritesModelList = null;
        favoritesModelList = mvpModel.getFavorites();
        return favoritesModelList;
    }

    @Override
    public void removeContactFromFavorites(int id) {
        mvpModel.removeContactFromFavorites(id);
    }

    @Override
    public void addContactToDatabase(Context context) {
        mvpModel.addContactToDatabase(context);
    }

    @Override
    public boolean checkContactPresentInFavoritesTable(int id) {
        return mvpModel.checkContactPresentInFavoritesTable(id);
    }

    @Override
    public void addContactToFavorites(int id) {
        mvpModel.addContactToFavorites(id);
    }

    @Override
    public void addToRecent(List<RecentModel> recentModelList) {
        mvpModel.addToRecent(recentModelList);
    }

    @Override
    public List<RecentModel> getAllRecents() {
        recentModelList = null;
        recentModelList = mvpModel.getAllRecents();
        return recentModelList;
    }

    @Override
    public List<ContactModel> getContacts() {
        contactModelList = null;
        contactModelList = mvpModel.getContacts();
        return contactModelList;
    }
}
