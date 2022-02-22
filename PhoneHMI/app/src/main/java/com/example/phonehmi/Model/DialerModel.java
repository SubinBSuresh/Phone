package com.example.phonehmi.Model;

import android.os.RemoteException;

import com.example.phonehmi.MainActivity;
import com.example.phonehmi.Presenter.IDialerPresenter;

import java.util.List;

import ServicePackage.SuggestionModel;

public class DialerModel implements  IDialerModel{
    List<SuggestionModel> suggestionModelList;

    IDialerPresenter iDialerPresenter;
    public DialerModel(IDialerPresenter iDialerPresenter) {
        this.iDialerPresenter =iDialerPresenter;
    }

    public DialerModel() {
    }

    @Override
    public List<SuggestionModel> getSuggestions(String number) {
        suggestionModelList = null;
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
}
