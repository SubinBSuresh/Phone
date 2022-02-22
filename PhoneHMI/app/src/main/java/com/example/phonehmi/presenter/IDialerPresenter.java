package com.example.phonehmi.presenter;

import java.util.List;

import ServicePackage.SuggestionModel;

public interface IDialerPresenter {

    //METHODS ARE CALLED HERE LIKE IN THE AIDL FILE. GO TO DialerPresenter
    List<SuggestionModel> getSuggestions(String number);
    String  showPhoneNumber(String digit, String number);

}
