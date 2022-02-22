package com.example.phonehmi.Presenter;

import java.util.List;

import ServicePackage.SuggestionModel;

public interface IDialerPresenter {

    List<SuggestionModel> getSuggestions(String number);
    String  showPhoneNumber(String digit, String number);

}
