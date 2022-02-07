package com.example.phonehmi;

import android.content.Context;
import android.os.RemoteException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import ServicePackage.ContactModel;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private List<ContactModel> ContactHolder;
    private Context context;
    public ToggleButton toggleButton;
    private LayoutInflater layoutInflater;


    public ContactAdapter(ArrayList<ContactModel> contactHolder, Context context) {
        this.ContactHolder = ContactHolder;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.row_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView name;
        ToggleButton toggleButton;

        name = holder.name;
        toggleButton = holder.toggleButton;

        name.setText(ContactHolder.get(position).getName());
        toggleButton.setChecked(isFavorite());
    }

    private boolean isFavorite() {
        //TODO read favorite status of the contact

        return false;
    }


    @Override
    public int getItemCount() {
        return ContactHolder.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ToggleButton toggleButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.contact_name);
            toggleButton = itemView.findViewById(R.id.btn_add_favorite);

            toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(context, android.R.drawable.star_big_off));

            itemView.setOnClickListener((View.OnClickListener) this);

//            toggleButton.setChecked(false);
//             toggleButton.setChecked(isFavorite());


            toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(context, android.R.drawable.btn_star));
            toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(context, android.R.drawable.btn_star_big_on));

                        try {
                            MainActivity.getAidl().addToFavorite(ContactHolder.get(getAdapterPosition()));
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    } else {
                        toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(context, android.R.drawable.btn_star_big_off));

                    }
                }
            });

        }
    }
}

