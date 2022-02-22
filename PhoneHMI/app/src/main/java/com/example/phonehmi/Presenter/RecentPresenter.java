package com.example.phonehmi.Presenter;

public class RecentPresenter {
    private final View view;

    public RecentPresenter(View view) {
        this.view = view;
    }
    public void recentAdapter(){
        view.recentAdapterInView();
    }

    public interface  View{
        void recentAdapterInView();
    }
}
