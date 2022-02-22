package com.example.phonehmi.presenter;

public class RecentPresenter implements IRecentPresenter {
    private final View view;

    public RecentPresenter(View view) {
        this.view = view;
    }

    public void recentAdapter() {
        view.recentAdapterInView();
    }

    public interface View {
        void recentAdapterInView();
    }
}
