package com.example.phonehmi.Model;

import android.os.RemoteException;

import com.example.phonehmi.MainActivity;
import com.example.phonehmi.presenter.IDialerPresenter;
import com.example.phonehmi.presenter.DialerPresenter;
import com.example.phonehmi.presenter.IDialerPresenter;

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

    public DialerModel(DialerPresenter dialerPresenter) {
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
}
