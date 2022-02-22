package com.example.phonehmi.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.phonehmi.view.ContactFragment;
import com.example.phonehmi.view.DialerFragment;
import com.example.phonehmi.view.FavoritesFragment;
import com.example.phonehmi.view.RecentFragment;

public class FragmentAdapter extends FragmentStateAdapter{

    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new ContactFragment();
            case 2:
                return new FavoritesFragment();
            case 3:
                return new RecentFragment();
        }
        return new DialerFragment();
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
