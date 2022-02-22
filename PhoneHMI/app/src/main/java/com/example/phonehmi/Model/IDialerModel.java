package com.example.phonehmi.Model;

import java.util.List;

import ServicePackage.SuggestionModel;

public interface IDialerModel {

    //SIMILAR TO AIDL FILE, OUR METHODS ARE CALLED HERE. GO TO DialerModel file
    List<SuggestionModel> getSuggestions(String number);
    String  showPhoneNumber(String digit, String number);
}
