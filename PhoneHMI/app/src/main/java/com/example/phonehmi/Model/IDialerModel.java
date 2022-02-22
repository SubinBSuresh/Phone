package com.example.phonehmi.Model;

import java.util.List;

import ServicePackage.SuggestionModel;

public interface IDialerModel {

    List<SuggestionModel> getSuggestions(String number);
    String  showPhoneNumber(String digit, String number);
}
