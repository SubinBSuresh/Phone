package com.example.phonehmi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ServicePackage.ContactModel;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    public static List<ContactModel> contactList;
    Context context;
    ContactModel contactModel;


    public ContactAdapter(List<ContactModel> contactList, Context context) {
        ContactAdapter.contactList = contactList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        contactModel = contactList.get(position);

        holder.name.setText(contactModel.getName());


        // CODE FOR SHOWING DATA IS PRESENT IN FAVORITES TABLE OR NOT
        try {

            if (!MainActivity.getAidl().checkContactPresentInFavoritesTable(contactModel.getId())) {
                holder.imageButton.setBackgroundResource(R.drawable.ic_baseline_star_unstar_24);
            } else {
                holder.imageButton.setBackgroundResource(R.drawable.ic_baseline_star_24);
            }
        } catch (Exception e) {
            Log.e("%%%%%%%%%%%%", "broken");
        }
    }


    @Override
    public int getItemCount() {
        return contactList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name;
        ImageButton imageButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.contact_name);
            imageButton = itemView.findViewById(R.id.btn_add_favorite);

            imageButton.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    contactModel = contactList.get(position);
//                    Toast.makeText(view.getContext(), "toastworking" + contactModel.getId(), Toast.LENGTH_SHORT).show();
                    view.setOnClickListener(this);
                    // CODE FOR ADDING DATA TO FAVORITE TABLE

                    try {
                        if (!MainActivity.getAidl().checkContactPresentInFavoritesTable(contactModel.getId())) {
                            imageButton.setBackgroundResource(R.drawable.ic_baseline_star_24);

//                            Log.e("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$",""+test);
                            MainActivity.getAidl().addContactToFavorites(contactModel.getId());
                            Toast.makeText(context, "Added to favourite", Toast.LENGTH_SHORT).show();

                            imageButton.setSelected(true);
                        } else {
//                            test = true;
//                            Log.e("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&",""+test);

                            MainActivity.getAidl().removeContactFromFavorites(contactModel.getId());
                            Toast.makeText(context, "Removed favourite", Toast.LENGTH_SHORT).show();

                            imageButton.setBackgroundResource(R.drawable.ic_baseline_star_unstar_24);
                            imageButton.setSelected(false);
                        }
                        notifyDataSetChanged();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
//                    Toast.makeText(view.getContext(), "toastworking"+test,Toast.LENGTH_SHORT).show();


                }
            });
        }


        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Item click", Toast.LENGTH_SHORT).show();
        }
    }
}




