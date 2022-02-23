package com.example.phonehmi.presenter;

import android.os.RemoteException;

import com.example.phonehmi.MainActivity;
import com.example.phonehmi.Model.MVPModel;
import com.example.phonehmi.view.IDialerView;
import com.example.phonehmi.view.IFavoritesView;

import java.util.List;

import ServicePackage.FavoritesModel;
import ServicePackage.SuggestionModel;

public class MVPPresenter implements IMVPPresenter {


    List<SuggestionModel> suggestionModelList;
    List<FavoritesModel> favoritesModelList;

    IDialerView iDialerView;
    IFavoritesView iFavoritesView;
    MVPModel mvpModel;
    MVPPresenter mvpPresenter;


    public MVPPresenter(IDialerView iDialerView) {
        this.iDialerView = iDialerView;
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

    public MVPPresenter(IFavoritesView iFavoritesView) {
        this.iFavoritesView = iFavoritesView;
        mvpModel = new MVPModel(mvpPresenter);
    }

    @Override
    public List<FavoritesModel> getFavorites() {
        favoritesModelList = null;
        favoritesModelList = mvpModel.getFavorites();
        return favoritesModelList;
    }
}
