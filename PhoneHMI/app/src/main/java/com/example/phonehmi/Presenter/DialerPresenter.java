package com.example.phonehmi.Presenter;

import com.example.phonehmi.Model.DialerModel;
import com.example.phonehmi.View.IDialerView;

import java.util.List;

import ServicePackage.SuggestionModel;

public class DialerPresenter implements IDialerPresenter {

    List<SuggestionModel> suggestionModelList;

    IDialerView iDialerView;
    DialerModel dialerModel;
    DialerPresenter dialerPresenter;


    public DialerPresenter(IDialerView iDialerView) {
        this.iDialerView = iDialerView;
        dialerModel = new DialerModel(dialerPresenter);
    }

    @Override
    public List<SuggestionModel> getSuggestions(String number) {
        suggestionModelList = null;
        suggestionModelList = dialerModel.getSuggestions(number);
        return suggestionModelList;
    }

    @Override
    public String showPhoneNumber(String digit, String number) {
        return dialerModel.showPhoneNumber(digit, number);

    }
}
