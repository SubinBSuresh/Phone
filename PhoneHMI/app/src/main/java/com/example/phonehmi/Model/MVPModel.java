package com.example.phonehmi.Model;

import android.os.RemoteException;

import com.example.phonehmi.MainActivity;
import com.example.phonehmi.presenter.IMVPPresenter;
import com.example.phonehmi.presenter.MVPPresenter;

import java.util.List;

import ServicePackage.FavoritesModel;
import ServicePackage.SuggestionModel;

public class MVPModel implements IMVPModel {
    List<SuggestionModel> suggestionModelList;
    List<FavoritesModel> favoritesModelList;


    IMVPPresenter iDialerPresenter;
    public MVPModel(IMVPPresenter iDialerPresenter) {
        this.iDialerPresenter =iDialerPresenter;
    }

    public MVPModel() {
    }

    public MVPModel(MVPPresenter dialerPresenter) {
    }


    //HERE IS WHERE ACTUAL EXECUTION OF THE COMMENTS TAKES PLACE
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

    @Override
    public String showPhoneNumber(String digit, String number) {

        if (number.length() < 15) {
            return number + digit;
        }
        return number;
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
}
