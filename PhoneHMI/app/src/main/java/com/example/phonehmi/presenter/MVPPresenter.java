package com.example.phonehmi.presenter;

import com.example.phonehmi.Model.MVPModel;
import com.example.phonehmi.view.IContactView;
import com.example.phonehmi.view.IDialerView;

import java.util.List;

import ServicePackage.ContactModel;
import ServicePackage.SuggestionModel;

public class MVPPresenter implements IMVPPresenter {


    List<SuggestionModel> suggestionModelList;
    List<ContactModel> contactModelList;


    IDialerView iDialerView;
    IContactView iContactView;

    MVPModel mvpModel;
    MVPPresenter mvpPresenter;


    public MVPPresenter(IDialerView iDialerView) {
        this.iDialerView = iDialerView;
        mvpModel = new MVPModel(mvpPresenter);
    }

    public MVPPresenter(IContactView iContactView) {
        this.iContactView = iContactView;
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

    @Override
    public List<ContactModel> getContacts() {
        contactModelList = null;
        contactModelList = mvpModel.getContacts();
        return contactModelList;
    }
}
