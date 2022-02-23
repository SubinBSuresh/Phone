package com.example.phonehmi.presenter;

import com.example.phonehmi.Model.MVPModel;
import com.example.phonehmi.view.IDialerView;

import java.util.List;

import ServicePackage.SuggestionModel;

public class MVPPresenter implements IMVPPresenter {


    List<SuggestionModel> suggestionModelList;

    IDialerView iDialerView;
    MVPModel dialerModel;
    MVPPresenter dialerPresenter;


    public MVPPresenter(IDialerView iDialerView) {
        this.iDialerView = iDialerView;
        dialerModel = new MVPModel(dialerPresenter);
    }

    // BOTH THE METHODS MENTIONED IN IDialerInterface IS CALLED HERE
    @Override
    public List<SuggestionModel> getSuggestions(String number) {
        suggestionModelList = null;

        //THIS METHOD IS EXECUTED IN THE DialerModel. FIRSTLY CHECK IDialerModel and THEN DialerModel
        suggestionModelList = dialerModel.getSuggestions(number);
        return suggestionModelList;
    }

    @Override
    public String showPhoneNumber(String digit, String number) {
        return dialerModel.showPhoneNumber(digit, number);

    }
}
